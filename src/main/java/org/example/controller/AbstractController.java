package org.example.controller;

import jakarta.annotation.PostConstruct;
import org.example.model.entity.AbstractEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.service.Service;
import java.util.List;

public abstract class AbstractController<T extends AbstractEntity> {
    protected HttpHeaders headers;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = getService().readAll();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable long id) {
        T entity = getService().read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable long id, @RequestBody T entity) {
        if (getService().read(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody T entity) {
        getService().create(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public abstract Service<T> getService();
}