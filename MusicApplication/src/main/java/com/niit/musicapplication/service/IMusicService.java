package com.niit.musicapplication.service;

import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.exceptions.TrackAlreadyExist;

import java.util.List;

public interface IMusicService {
    Music addMusic(Music music) throws TrackAlreadyExist;
    List<Music> getAllTrack();
}
