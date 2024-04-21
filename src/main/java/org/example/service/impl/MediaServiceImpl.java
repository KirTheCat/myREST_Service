package org.example.service.impl;

import org.example.model.Media;
import org.example.service.MediaService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MediaServiceImpl implements MediaService {
    private static final Map<Integer, Media> MEDIA_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger MEDIA_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Media media){
        final int mediaId = MEDIA_ID_HOLDER.incrementAndGet();
        media.setMediaId(mediaId);
        MEDIA_REPOSITORY_MAP.put(mediaId,media);
    }
    @Override
    public List<Media> readAll(){
        return new ArrayList<>(MEDIA_REPOSITORY_MAP.values());
    }
    @Override
    public Media read(int id) {
        return MEDIA_REPOSITORY_MAP.get(id);
    }
    @Override
    public boolean update(Media media, int  id){
        if (MEDIA_REPOSITORY_MAP.containsKey(id)){
            media.setMediaId(id);
            MEDIA_REPOSITORY_MAP.put(id,media);
            return true;
        } return false;
    }
    @Override
    public boolean delete(int id) {
        return MEDIA_REPOSITORY_MAP.remove(id) != null;
    }
}

