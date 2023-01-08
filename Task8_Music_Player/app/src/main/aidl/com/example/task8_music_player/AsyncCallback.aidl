package com.example.task8_music_player;

import com.example.task8_music_player.AidlException;
interface AsyncCallback {
    oneway void onSuccess(in int index);
    oneway void onError(in AidlException error);
}