package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Series extends Media{
    private List<Episode> episodes;
}
