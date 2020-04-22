package com.wyyuand.domain;

import java.util.Date;

public class Music {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_id
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.type
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private Short type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_new_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicNewUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_name
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_time
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private Short musicTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_singer
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicSinger;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_album
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private String musicAlbum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_music.music_update
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    private Date musicUpdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_id
     *
     * @return the value of t_music.music_id
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicId() {
        return musicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_id
     *
     * @param musicId the value for t_music.music_id
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicId(String musicId) {
        this.musicId = musicId == null ? null : musicId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.type
     *
     * @return the value of t_music.type
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.type
     *
     * @param type the value for t_music.type
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_url
     *
     * @return the value of t_music.music_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicUrl() {
        return musicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_url
     *
     * @param musicUrl the value for t_music.music_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl == null ? null : musicUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_new_url
     *
     * @return the value of t_music.music_new_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicNewUrl() {
        return musicNewUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_new_url
     *
     * @param musicNewUrl the value for t_music.music_new_url
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicNewUrl(String musicNewUrl) {
        this.musicNewUrl = musicNewUrl == null ? null : musicNewUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_name
     *
     * @return the value of t_music.music_name
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicName() {
        return musicName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_name
     *
     * @param musicName the value for t_music.music_name
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicName(String musicName) {
        this.musicName = musicName == null ? null : musicName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_time
     *
     * @return the value of t_music.music_time
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public Short getMusicTime() {
        return musicTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_time
     *
     * @param musicTime the value for t_music.music_time
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicTime(Short musicTime) {
        this.musicTime = musicTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_singer
     *
     * @return the value of t_music.music_singer
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicSinger() {
        return musicSinger;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_singer
     *
     * @param musicSinger the value for t_music.music_singer
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicSinger(String musicSinger) {
        this.musicSinger = musicSinger == null ? null : musicSinger.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_album
     *
     * @return the value of t_music.music_album
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public String getMusicAlbum() {
        return musicAlbum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_album
     *
     * @param musicAlbum the value for t_music.music_album
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum == null ? null : musicAlbum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_music.music_update
     *
     * @return the value of t_music.music_update
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public Date getMusicUpdate() {
        return musicUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_music.music_update
     *
     * @param musicUpdate the value for t_music.music_update
     *
     * @mbg.generated Mon Jul 01 10:36:07 CST 2019
     */
    public void setMusicUpdate(Date musicUpdate) {
        this.musicUpdate = musicUpdate;
    }
}