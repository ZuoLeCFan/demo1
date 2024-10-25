package com.hj.service.impl;

import com.hj.bean.bo.Image;

import java.util.Map;

public interface SaveImageServiceImpl {
    Map<String, Object> savePhotoToTable(Image image);

    String deleteImageById(Image image);

    Map<String, Object> getPhotoToTable();

    Map<String, Object> revisePhotoToTable(Image image);
}
