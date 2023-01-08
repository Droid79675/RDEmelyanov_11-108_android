// MusicAidl.aidl
package com.example.task8_music_player;

// Declare any non-default types here with import statements
import com.example.task8_music_player.AsyncCallback;
interface MusicAidl {
    boolean isPlaying();

    oneway void play(int position, AsyncCallback callback);

    void stop();

    int currentSongPosition();
}