package com.ricardo.blog.model;

import lombok.Data;

@Data
public class ImageData {

    private String url;
    private String alt;
    private String href;

    public ImageData(String url, String alt, String href) {
        this.url = url;
        this.alt = alt;
        this.href = href;
    }
}
