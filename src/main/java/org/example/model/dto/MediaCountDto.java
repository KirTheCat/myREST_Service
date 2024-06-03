package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MediaCountDto {
    private long movieCount;
    private long seriesCount;
    private long totalMediaCount;
}
