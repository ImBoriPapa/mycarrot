package com.project.carrot.web.controller.profile.dto;

import lombok.Data;

@Data
public class ImageForm {

    private Long id;
    private String uploadName;
    private String storeName;

    public ImageForm(String uploadName, String storeName) {
        this.uploadName = uploadName;
        this.storeName = storeName;
    }
}
