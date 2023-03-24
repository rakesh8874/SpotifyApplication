package com.niit.musicapplication.repository;

import com.niit.musicapplication.domain.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends MongoRepository<Music, Integer> {
}
