package com.niit.musicapplication.service;

import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.domain.Playlist;
import com.niit.musicapplication.domain.User;
import com.niit.musicapplication.exceptions.UserAlreadyExist;
import com.niit.musicapplication.exceptions.UserNotFound;

import java.util.List;

public interface IUserService {
    User registerUser(User user) throws UserAlreadyExist;
    User saveUserPlaylistToList(Playlist playlist, String username) throws UserNotFound;
    User deleteUserPlaylistFromList(String username,String playlistName) throws Exception;
    List<Playlist> getAllUserPlaylist(String username) throws UserNotFound;
    User getUserByUsername(String username);
    User addSongIntoPlaylist(Music music, String playlistName, String username) throws Exception;
    List<Music> getAllSongsFromPlaylist(String playlistName, String username) throws Exception;
    User deleteSongsFromPlaylist(String trackName, String playlistName, String username) throws Exception;
}
