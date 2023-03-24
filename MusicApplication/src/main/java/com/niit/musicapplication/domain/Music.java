package com.niit.musicapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Music {
    @Id
    private int trackId;
    private String trackName;
    private List<Artist> trackArtist;
    private String trackGenre;
    private int trackRating;
    private double trackDuration;
    private String trackPath;
    private String playIcon;
}
