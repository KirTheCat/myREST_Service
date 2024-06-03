package org.example.service.impl;

import org.example.model.entity.Media;
import org.example.repository.MediaRepository;
import org.example.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Media read(Long id) {
        return mediaRepository.findById(id).orElse(null);
    }
    @Override
    public List<Media> readAll(){
        return mediaRepository.findAll();
    }
    @Override
    public Media save(Media entity) {
        return mediaRepository.save(entity);
    }
    @Override
    public long count() {
        return mediaRepository.count();
    }
    @Override
    public void delete(Long id) {
        mediaRepository.deleteById(id);
    }

    @Override
    public Media create(Media entity){
        return mediaRepository.save(entity);
    }

}
