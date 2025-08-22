package com.artevo.artevobackend.adapter.in;

import com.artevo.artevobackend.adapter.out.ArtistRepository;
import com.artevo.artevobackend.adapter.out.DesignRepository;
import com.artevo.artevobackend.adapter.out.PrintItemRepository;
import com.artevo.artevobackend.application.domain.Artist;
import com.artevo.artevobackend.application.domain.Design;
import com.artevo.artevobackend.application.domain.PrintItem;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/printitems")
public class PrintItemController {

    private final PrintItemRepository printItemRepo;
    private final DesignRepository designRepo;
    private final ArtistRepository artistRepo;

    public PrintItemController(PrintItemRepository printItemRepo, DesignRepository designRepo, ArtistRepository artistRepo) {
        this.printItemRepo = printItemRepo;
        this.designRepo = designRepo;
        this.artistRepo = artistRepo;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPrintItem(
            @RequestParam Long artistId,
            @RequestParam String garmentType,
            @RequestParam String shirtColor,
            @RequestParam float posX,
            @RequestParam float posY,
            @RequestParam float scale,
            @RequestParam float rotation,
            @RequestParam(required = false) Long designId,
            @RequestParam(required = false) MultipartFile file
    ) {
        try {
            System.out.println("=== CREATE PRINT ITEM ===");
            System.out.println("ArtistId: " + artistId);
            System.out.println("GarmentType: " + garmentType + ", Color: " + shirtColor);
            System.out.println("Position: (" + posX + "," + posY + "), Scale: " + scale + ", Rotation: " + rotation);
            System.out.println("DesignId: " + designId + ", File: " + (file != null ? file.getOriginalFilename() : "null"));

            Optional<Artist> artistOpt = artistRepo.findById(artistId);
            if (artistOpt.isEmpty()) {
                System.out.println("Artist nicht gefunden!");
                return ResponseEntity.badRequest().body("Artist nicht gefunden");
            }

            Artist artist = artistOpt.get();
            Design design;

            if (designId == null && file != null) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) {
                    System.out.println("Uploads-Verzeichnis existiert nicht, wird erstellt.");
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                System.out.println("Speichere Datei nach: " + filePath.toAbsolutePath());
                file.transferTo(filePath);

                design = new Design();
                design.setFileName(fileName);
                design.setArtist(artist);
                design.setData(file.getBytes()); // <- DAS schreibt die Datei ins Blob-Feld
                designRepo.save(design);
                System.out.println("Neues Design gespeichert: " + design.getFileName());
            } else if (designId != null) {
                Optional<Design> designOpt = designRepo.findById(designId);
                if (designOpt.isEmpty()) {
                    System.out.println("Design nicht gefunden!");
                    return ResponseEntity.badRequest().body("Design nicht gefunden");
                }
                design = designOpt.get();
                System.out.println("Existierendes Design verwendet: " + design.getFileName());
            } else {
                System.out.println("Kein Design angegeben!");
                return ResponseEntity.badRequest().body("Kein Design angegeben");
            }

            PrintItem item = new PrintItem();
            item.setArtist(artist);
            item.setDesign(design);
            item.setGarmentType(garmentType);
            item.setShirtColor(shirtColor);
            item.setPosX(posX);
            item.setPosY(posY);
            item.setScale(scale);
            item.setRotation(rotation);
            item.setStatus("draft");

            printItemRepo.save(item);
            System.out.println("PrintItem gespeichert: ID=" + item.getId());

            return ResponseEntity.ok(item);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Fehler beim Erstellen des PrintItems: " + e.getMessage());
        }
    }

}
