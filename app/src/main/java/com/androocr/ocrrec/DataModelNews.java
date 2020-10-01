package com.androocr.ocrrec;


public class DataModelNews {

    String title;
    String description;
    String img;

    public DataModelNews(String title, String description, String img ) {
        this.title=title;
        this.description=description;
        this.img=img;

    }

    public String getName() {
        return title;
    }

    public String getVersion_number() {
        return description;
    }

    public String getFeature() {
        return img;
    }

}

