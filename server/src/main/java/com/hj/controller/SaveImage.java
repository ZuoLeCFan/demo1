package com.hj.controller;

import com.hj.bean.bo.Image;
import com.hj.service.impl.SaveImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/SAVE")
@Slf4j
public class SaveImage {

    @Autowired
    private SaveImageServiceImpl saveImageService;

    @PostMapping("/PHOTO")
    public Map<String, String> savePhoto(@RequestParam("file") MultipartFile file){
        Map<String, String> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        //初始化当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String uploadPath =  "D:\\image\\";

        try{
            File fileDir = new File(uploadPath);
            //判断文件是否存在
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }

            file.transferTo(new File(uploadPath+sdf.format(date)+fileName));
        }catch(IOException e){
            e.printStackTrace();
            map.put("err", "Error occurred while uploading the file");
            return map;
        }
        map.put("fileName", sdf.format(date)+fileName);
        String data = "http://loaclhost:3000/"+sdf.format(date)+fileName;
        map.put("url", data);
        map.put("path", uploadPath+sdf.format(date)+fileName);
        return map;
    }

    //将数据保存到数据库中
    @PostMapping("/PHOTO-TO-TABLE")
    public Map<String, Object> saveImage(@RequestBody Image image){
        Map<String, Object> data = saveImageService.savePhotoToTable(image);
        log.info(image.getUrl());
        return data;
    }

    //根据id删除数据
    @PostMapping("DELETE-PHOTO")
    public String deleteImage(@RequestBody Image image){
        String data = saveImageService.deleteImageById(image);
        return data;
    }

    //查询数据
    @PostMapping("/GET-PHOTO")
    public Map<String, Object> selectImage(){
        Map<String, Object> map = saveImageService.getPhotoToTable();
        return map;
    }

    //修改
    @PostMapping("/REVISE")
    public Map<String, Object> reviseImage(@RequestBody Image image){
        System.out.println(image);
        return saveImageService.revisePhotoToTable(image);
    }
}
