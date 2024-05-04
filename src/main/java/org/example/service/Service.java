package org.example.service;
import org.example.model.entity.AbstractEntity;

import java.util.List;


public interface Service<T extends AbstractEntity>{
    T read(Long id);
    List<T> readAll();
    void update(T entity);
    void delete(Long id);
    void create (T entity);
}
