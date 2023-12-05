package com.ricardo.blog.api;

import com.ricardo.blog.model.FileResult;
import com.ricardo.blog.model.ImageData;
import com.ricardo.blog.util.GitHubFileUploader;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/static")
public class StaticApi {

    @Autowired
    private GitHubFileUploader fileUploader;
    @PostMapping("/image")
    public FileResult uploadImage(@RequestPart("file") MultipartFile file){
        try {
            // base64
            Base64 base64Encoder = new Base64();
            byte[] imageBytes = file.getBytes();
            // 上传图片
            String url = fileUploader.uploadFile(file.getOriginalFilename(), base64Encoder.encodeToString(imageBytes));
            if (url.equals("")){
                return FileResult.fail("上传失败");
            }
            return FileResult.success(new ImageData(url,file.getOriginalFilename(),url));
        } catch (IOException e) {
            return FileResult.fail("io error");
        }
    }
}
