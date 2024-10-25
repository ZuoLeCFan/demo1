package com.hj.bean.bo;

import lombok.Data;

@Data
public class Image {
    private int id;
    private String url;  //图片http路径
    private String name;   //图片name
    private String path;  //图片存放路径
    private String searchText;  //搜索时用的文本
}
