package org.example.web;

import org.example.model.Media;
import org.example.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MediaController {
    private final MediaService mediaService;
    @Autowired
    public MediaController(MediaService mediaService){
        this.mediaService = mediaService;
    }
    @PostMapping(value = "/media")
    public ResponseEntity<?> create(@RequestBody Media media) {
        mediaService.create(media);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/media")
    public ResponseEntity<List<Media>> read(){
        final List<Media> media = mediaService.readAll();

        return media != null && !media.isEmpty()
                ? new ResponseEntity<>(media, HttpStatus.OK)
                : new ResponseEntity<>(media, HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/media/{id}")
    public ResponseEntity<Media> read(@PathVariable(name = "id") int id) {
        final Media media = mediaService.read(id);

        return media != null
                ? new ResponseEntity<>(media, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/media/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Media media) {
        final boolean updated = mediaService.update(media, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/media/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = mediaService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
