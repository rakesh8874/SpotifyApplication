package com.niit.musicapplication.controller;
import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.domain.Playlist;
import com.niit.musicapplication.domain.User;
import com.niit.musicapplication.exceptions.UserAlreadyExist;
import com.niit.musicapplication.exceptions.UserNotFound;
import com.niit.musicapplication.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    //method to register user

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExist {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    //method to create playlist

    @PostMapping("/addUserPlaylist")
    ResponseEntity<?> addPlaylistToList(@RequestBody Playlist playlist, HttpServletRequest request) throws UserNotFound {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.saveUserPlaylistToList(playlist, username), HttpStatus.CREATED);
    }
//method to get all playlist

    @GetMapping("/getAllPlaylist")
    ResponseEntity<?> getAllPlaylistOfUser(HttpServletRequest request) throws UserNotFound {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.getAllUserPlaylist(username), HttpStatus.OK);
    }
//method to add songs into playlist
    @PostMapping("/addSongIntoPlaylist/{playlistName}")
    ResponseEntity<?> addSongIntoPlaylist(@RequestBody Music music, @PathVariable String playlistName, HttpServletRequest request) throws Exception {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.addSongIntoPlaylist(music, playlistName, username), HttpStatus.CREATED);
    }

    //method for getting all the songs of playlist
    @GetMapping("/getAllMusicOfPlaylist/{playlistName}")
    ResponseEntity<?> getMusicFromPlaylist(@PathVariable String playlistName, HttpServletRequest request) throws Exception {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.getAllSongsFromPlaylist(playlistName, username), HttpStatus.OK);
    }

    //method for deleting playlist from list

    @DeleteMapping("/deletePlaylist/{playlistName}")
    ResponseEntity<?> deletePlaylistFromList(@PathVariable String playlistName, HttpServletRequest request) throws Exception {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.deleteUserPlaylistFromList(username, playlistName), HttpStatus.OK);
    }

    //method for deleting song from list

    @DeleteMapping("/deleteSongs/{playlistName}/{songName}")
      ResponseEntity<?> deleteSongsFromList(@PathVariable String songName, HttpServletRequest request, @PathVariable String playlistName) throws Exception {
        Claims claims = (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return new ResponseEntity<>(userService.deleteSongsFromPlaylist(songName,playlistName,username), HttpStatus.OK);
    }

}
