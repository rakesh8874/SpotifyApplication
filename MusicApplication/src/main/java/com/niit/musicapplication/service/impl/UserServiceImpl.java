package com.niit.musicapplication.service.impl;
import com.niit.musicapplication.domain.Music;
import com.niit.musicapplication.domain.Playlist;
import com.niit.musicapplication.domain.User;
import com.niit.musicapplication.exceptions.UserAlreadyExist;
import com.niit.musicapplication.exceptions.UserNotFound;
import com.niit.musicapplication.proxy.IUserProxy;
import com.niit.musicapplication.repository.UserRepository;
import com.niit.musicapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private IUserProxy userProxy;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, IUserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

//    method to register user
    @Override
    public User registerUser(User user) throws UserAlreadyExist {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            throw new UserAlreadyExist();
        }
        userProxy.registerUser(user);
        return userRepository.save(user);
   }

//   method to create add users playlist into list
    @Override
    public User saveUserPlaylistToList(Playlist playlist, String username) throws UserNotFound {
        if (userRepository.findById(username).isEmpty()) {

            throw new UserNotFound();
        }
        User user = userRepository.findById(username).get();
        if (user.getAllPlaylists() == null) {
            user.setAllPlaylists(Arrays.asList(playlist));
        } else {
            List<Playlist> playlists = user.getAllPlaylists();
            playlists.add(playlist);
            user.setAllPlaylists(playlists);
        }
        return userRepository.save(user);
    }

    //Method for deleting playlist from list

    @Override
    public User deleteUserPlaylistFromList(String username, String playlistName) throws Exception {
        boolean playlistNameIsPresent = false;
        if(userRepository.findById(username).isEmpty())
        {
            throw new UserNotFound();
        }
        User user = userRepository.findById(username).get();
        List<Playlist> playlists = user.getAllPlaylists();
        playlistNameIsPresent = playlists.removeIf(x->x.getPlaylistName().equals(playlistName));
        if(!playlistNameIsPresent)
        {
            throw new Exception("Might Be Playlist Not Available");
        }
        user.setAllPlaylists(playlists);
        return userRepository.save(user);
    }

//    method to get all users playlist
    @Override
    public List<Playlist> getAllUserPlaylist(String username) throws UserNotFound {
        if(userRepository.findById(username).isEmpty())
        {
            throw new UserNotFound();
        }
        List<Playlist> allPlaylists = userRepository.findById(username).get().getAllPlaylists();
        return  allPlaylists;
    }

    //method to get user by username

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    method to add song into playlist
    @Override
    public User addSongIntoPlaylist(Music music, String playlistName, String username) throws Exception {
        if (userRepository.findById(username).isEmpty()) {
            throw new UserNotFound();
        }
        User user = userRepository.findById(username).get();
        List<Playlist> allPlaylists = user.getAllPlaylists();
        for(Playlist playlist:allPlaylists){
           if(playlist.getFavoriteMusic() == null && playlist.getPlaylistName().equals(playlistName)){
               playlist.setFavoriteMusic(Arrays.asList(music));
            }else if(playlist.getPlaylistName().equals(playlistName)){
               List<Music> favMusic = playlist.getFavoriteMusic();
               favMusic.add(music);
                playlist.setFavoriteMusic(favMusic);
           }else{
               throw new Exception("Might Be Playlist Isn't Available on given name");
           }
       }
        return userRepository.save(user);
    }

//    method to get all songs from playlist
    @Override
    public List<Music> getAllSongsFromPlaylist(String playlistName, String username) throws Exception {
        List<Music> allFavMusic = new ArrayList<>();
        if(userRepository.findById(username).isEmpty())
        {
            return null;
        }
        List<Playlist> allPlaylists = userRepository.findById(username).get().getAllPlaylists();
        for(Playlist playlist:allPlaylists){
            if (playlist.getPlaylistName().equals(playlistName)) {
                allFavMusic = playlist.getFavoriteMusic();
            }else{
                throw new Exception("Might Be Playlist Isn't Available on given name");
            }
    }
        return allFavMusic;
    }

//    method to delete songs from playlist
    @Override
    public User deleteSongsFromPlaylist(String trackName, String playlistName, String username) throws Exception {
        boolean songNameIsPresent = false;
        if(userRepository.findById(username).isEmpty())
        {
            throw new UserNotFound();
        }
        User user = userRepository.findById(username).get();
        List<Playlist> playlists = user.getAllPlaylists();
        for(Playlist playlist:playlists) {
       List<Music> allSongs =playlist.getFavoriteMusic();
        songNameIsPresent = allSongs.removeIf(x->x.getTrackName().equalsIgnoreCase(trackName));
        playlist.setFavoriteMusic(allSongs);
        if(!songNameIsPresent)
        {
            throw new Exception("Might Be Song Not Available in Your Playlist");
        }
    }
        return userRepository.save(user);
    }


}

