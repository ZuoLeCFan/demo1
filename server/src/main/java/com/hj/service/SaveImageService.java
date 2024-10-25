package com.hj.service;

import com.hj.bean.bo.Image;
import com.hj.Mapper.SavePhotoMapper;
import com.hj.service.impl.SaveImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaveImageService implements SaveImageServiceImpl {
    @Autowired
    private SavePhotoMapper savePhotoMapper;

    @Override
    public Map<String, Object> savePhotoToTable(Image image) {
        Map<String, Object> map = new HashMap<>();
        try{
            //查最后一个的id
            Image image1 = savePhotoMapper.selectLastId();
            //添加
            if(image1 == null){
                image.setId(1);
                savePhotoMapper.setImage(image);
                map.put("status", "success");
                map.put("id",1);
                return map;
            }

            image.setId(image1.getId()+1);
            savePhotoMapper.setImage(image);
            map.put("status", "success");
            map.put("id",image1.getId()+1);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", "error");
            map.put("id", 0);
            return map;
        }
    }

    @Override
    public String deleteImageById(Image image) {
        try {
            savePhotoMapper.deleteImageById(image);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public Map<String, Object> getPhotoToTable() {
        try{
            Map<String, Object> map = new HashMap<>();

            List<Image> list = savePhotoMapper.getPhoto();
            map.put("list", list);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> revisePhotoToTable(Image image) {
        Map<String, Object> map = new HashMap<>();

        try{
            Integer index = savePhotoMapper.revisePhoto(image);
            System.out.println(index);
            map.put("status", true);
            map.put("message", "修改成功");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", false);
            map.put("message", "修改失败");
            return map;
        }
    }
}
