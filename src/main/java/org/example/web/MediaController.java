package org.example.web;

import org.example.model.entity.Media;
import org.example.service.Service;
import org.example.service.impl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaController extends AbstractController<Media> {
    private final MediaServiceImpl mediaService;

    @Autowired
    public MediaController(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public Service<Media> getService() {
        return mediaService;
    }

}
