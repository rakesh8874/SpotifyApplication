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
public class User {
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    List<Playlist> allPlaylists;
}
