package org.example.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@Entity
@Table(name = "series")
public class Series extends Media{
    @OneToMany(mappedBy = "series")
    private List<Episode> episodes;
}
