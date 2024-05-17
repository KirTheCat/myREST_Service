package org.example.service;
import org.example.model.entity.AbstractEntity;

import java.util.List;


public interface Service<T extends AbstractEntity>{
    T create (T entity);
    T save(T entity);
    T read(Long id);
    List<T> readAll();
    void delete(Long id);
}
