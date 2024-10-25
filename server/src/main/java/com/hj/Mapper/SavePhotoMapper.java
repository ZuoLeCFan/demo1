package com.hj.Mapper;

import com.hj.bean.bo.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SavePhotoMapper {
    void setImage(Image image);


    Image selectLastId();

    void deleteImageById(Image image);

    List<Image> getPhoto();

    Integer revisePhoto(Image image);
}
