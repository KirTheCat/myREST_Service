package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "series")
public class Series extends Media{
    @OneToMany(mappedBy = "series")
    private List<Episode> episodes;
}
