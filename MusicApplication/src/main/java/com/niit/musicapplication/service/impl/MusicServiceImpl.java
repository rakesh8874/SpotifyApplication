package com.niit.musicapplication.service.impl;

import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.exceptions.TrackAlreadyExist;
import com.niit.musicapplication.repository.MusicRepository;
import com.niit.musicapplication.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements IMusicService {

    @Autowired
    MusicRepository musicRepository;

    @Override
    public Music addMusic(Music music) throws TrackAlreadyExist {
        if(musicRepository.findById(music.getTrackId()).isPresent()){
            throw new TrackAlreadyExist();
        }
        return musicRepository.save(music);
    }

    @Override
    public List<Music> getAllTrack() {
        return musicRepository.findAll();
    }
}
