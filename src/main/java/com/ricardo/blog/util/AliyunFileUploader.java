package com.ricardo.blog.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.ricardo.blog.config.AliyunConfig;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliyunFileUploader {
    private static final String END_POINT = "https://oss-cn-shanghai.aliyuncs.com";
    private static  final String BUCKET_NAME = "ricblogimage";
    @Autowired
    private AliyunConfig aliyunConfig;

    // only for create
    private void init(){
        String accessKeyId = aliyunConfig.getAccessTokenId();
        String accessKeySecret = aliyunConfig.getAccessTokenSecret();
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(END_POINT, credentialsProvider);

        try {
            // 创建存储空间。
            ossClient.createBucket(BUCKET_NAME);

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 上传图片到阿里云 OSS，并返回图片的 URL
     *
     * @param inputStream 图片输入流
     * @param fileName    图片文件名
     * @return 图片的 URL
     */
    public String uploadImage(InputStream inputStream, String fileName) {
        String accessKeyId = aliyunConfig.getAccessTokenId();
        String accessKeySecret = aliyunConfig.getAccessTokenSecret();
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(END_POINT, credentialsProvider);

        try {
            // 生成唯一的文件名
            String objectKey = generateUniqueFileName(fileName);
            // 上传文件流
            ossClient.putObject(BUCKET_NAME, objectKey, inputStream);

            // 生成图片的 URL
            String imageUrl = generateImageUrl(objectKey);

            return imageUrl;

        } catch (OSSException | ClientException e) {
            // 处理异常
            e.printStackTrace();
            return null;

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public String uploadObject(MultipartFile file){
        String accessKeyId = aliyunConfig.getAccessTokenId();
        String accessKeySecret = aliyunConfig.getAccessTokenSecret();
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        OSS ossClient = new OSSClientBuilder().build(END_POINT, credentialsProvider);
        //3 获取文件信息，为了上传
        //文件输入流
        try {
            InputStream in = file.getInputStream();

            //文件名称
            String fileName = file.getOriginalFilename();
            // meta设置请求头
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            ossClient.putObject(BUCKET_NAME, fileName, in, meta);
            return "https://ricblogimage.oss-cn-shanghai.aliyuncs.com/"+fileName;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            ossClient.shutdown();
        }
    }

    // 生成唯一的文件名
    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int index = originalFileName.lastIndexOf(".");
        String suffix = index == -1 ? "" : originalFileName.substring(index);
        return uuid + suffix;
    }

    // 生成图片的 URL
    private String generateImageUrl(String objectKey) {
        return "https://" + BUCKET_NAME + "." + END_POINT + "/" + objectKey;
    }
}
