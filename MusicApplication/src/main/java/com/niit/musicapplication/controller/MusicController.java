package com.niit.musicapplication.controller;

import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.exceptions.TrackAlreadyExist;
import com.niit.musicapplication.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musicService")
@CrossOrigin(origins = "*")
public class MusicController {

    @Autowired
    IMusicService musicService;

    @PostMapping("/add")
    public ResponseEntity<?> addTrack(@RequestBody Music music) throws TrackAlreadyExist {
        return new ResponseEntity<>(musicService.addMusic(music), HttpStatus.CREATED);
    }
    @GetMapping("/getallmusic")
    public ResponseEntity<?> getAllTrack(){
        return new ResponseEntity<>(musicService.getAllTrack(), HttpStatus.OK);
    }



}
