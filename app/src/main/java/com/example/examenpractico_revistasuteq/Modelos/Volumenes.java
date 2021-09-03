package com.example.examenpractico_revistasuteq.Modelos;

public class Volumenes {

    private String id,title,urlImgCover,volume,year,number;

    public  Volumenes() {
    }

    public  Volumenes(String id,String title, String urlImgCover, String volume, String year, String number) {
        this.id=id;
        this.title = title;
        this.urlImgCover = urlImgCover;
        this.volume = volume;
        this.year = year;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImgCover() {
        return urlImgCover;
    }

    public void setUrlImgCover(String urlImgCover) {
        this.urlImgCover = urlImgCover;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
