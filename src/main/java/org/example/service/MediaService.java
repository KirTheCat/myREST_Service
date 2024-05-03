package org.example.service;

import org.example.model.entity.Media;

import java.util.List;

public interface MediaService {
    void create(Media media);
    List<Media> readAll();
    Media read(int id);
    boolean update(Media media, int id);
    boolean delete(int id);

}
