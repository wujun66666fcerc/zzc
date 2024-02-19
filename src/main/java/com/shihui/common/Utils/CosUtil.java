package com.shihui.common.Utils;

import com.qcloud.cos.COSClient;

import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class CosUtil {

    @Value("${tencent.appId}")
    private String appId;

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    @Value("${tencent.region}")
    private String region;

    @Value("${tencent.photoBucket}")
    private String photoBucket;

    private COSClient cosClient;

    @PostConstruct
    public void init() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        cosClient = new COSClient(cred, clientConfig);
    }
    public COSClient getCosClient() {
        return cosClient;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        //上传至存储桶的名字
        String KEY = "/user_avatar/" + file.getOriginalFilename();
        String bucket = null;
        //根据业务模块，上传至其他存储桶
        bucket = photoBucket;
        //获得文件名
        String fileName = file.getOriginalFilename();
        //将图片的具体信息传入ObjectMetadate类
        ObjectMetadata meta=new ObjectMetadata();
        //必须设置该属性
        meta.setContentLength(file.getSize());
        //设置字符编码格式
        meta.setContentEncoding("UTF-8");
        //获得文件后缀名并根据传入的图片格式设置ContentType
        if (".png".equals(fileName.lastIndexOf("."))){
            meta.setContentType("image/png");
        }else if (".jpg".equals(fileName.lastIndexOf("."))){
            meta.setContentType("image/jpeg");
        }
        //SDK构造方法,具体参造[SDKAPI](https://help.aliyun.com/document_detail/32008.htm?spm=a2c4g.11186623.2.3.65ac605fhxBPgG)
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, KEY, file.getInputStream(),meta);
        putObjectRequest.setStorageClass(StorageClass.Standard);
        COSClient client = getCosClient();
        try {
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭客户端
        client.shutdown();
        //拼接获得存储桶中可访问的地址
        return "https://"+bucket+".cos."+region+".myqcloud.com"+KEY;
    }

}
