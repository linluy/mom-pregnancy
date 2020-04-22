package com.wyyuand.domain;


public class MusicChunExt extends MusicChun{

    private String singer;
    private String songName;
    private  String longUrl;

    public MusicChunExt() {
    }

    public MusicChunExt(String singer, String songName) {
        this.singer = singer;
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
