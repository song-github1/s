package com.song.PetShop.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名称：com.example.demotalentsys.util
 * 类名称：CosUploadUtil
 * 类描述：
 * 创建人：@author luohuagui
 * 创建时间：2021/3/11 11:31
 */
public class CosUploadUtil {

    // 初始化用户身份信息
    private static String SecretId = "AKIDjD7mytW7Ulx66WPNqGTRBlUyD9Cs2Hcj";
    private static String SecretKey = "4yqSs35sZ6NQHzPYlhMoyrpcCSTihU1d";
    // bucket的区域
    private static String region_name = "ap-nanjing";
    // bucket名
    private static String bucket_name = "petshop-1305547259";
    // 文件目录
    private static String fileDir = "/song/";

    /**
     * 获取COSClient
     * @return
     */
    public static COSClient getCOSClient(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region_name));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        return  cosClient;
    }

    /**
     *
     * @return
     */
    public static TransferManager getTransFerManager(){
        COSClient cosClient = getCOSClient();
        // 线程池大小，建议在客户端与 COS 网络充足（例如使用腾讯云的 CVM，同地域上传 COS）的情况下，设置成16或32即可，可较充分的利用网络资源
        // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        // 传入一个 threadpool, 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        // 设置高级接口的分块上传阈值和分块大小为5MB
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5 * 1024 * 1024);
        transferManagerConfiguration.setMinimumUploadPartSize(5 * 1024 * 1024);
        transferManager.setConfiguration(transferManagerConfiguration);
        return transferManager;
    }
     /**
     * 将本地文件上传到COS
     * @param file
     * @return
     */
    public static void SimpleUploadFileFromLocal(File file) {
        COSClient cosClient = getCOSClient();
        UploadResult uploadResult = new UploadResult();
        /*UploadResults uploadResults = new UploadResults();*/
        try {
            //对数据类型是 File 类型的文件上传，高级接口会多线程并发同时上传多个分块。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket_name, fileDir + file.getName(), file);
            TransferManager transferManager = getTransFerManager();
            // 本地文件上传
            Upload upload = transferManager.upload(putObjectRequest);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            uploadResult = upload.waitForUploadResult();
            //uploadResult将赋值给uploadResults
            /*BeanUtils.copyProperties(uploadResult,uploadResults);*/
            transferManager.shutdownNow();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        /*return uploadResults;*/
    }

    /**
     * 不需要签名的下载链接
     * @param key
     */
    public static String getUrl(String key){
        // 生成匿名的请求签名，需要重新初始化一个匿名的 cosClient
        // 初始化用户身份信息, 匿名身份不用传入 SecretId、SecretKey 等密钥信息
        COSCredentials cred = new AnonymousCOSCredentials();
        // 设置 bucket 的区域，COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region_name));
        // 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket 名需包含 appid
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket_name, key, HttpMethodName.GET);
        URL url = cosClient.generatePresignedUrl(req);
        //在得到url中插入新的字符
        StringBuilder sb = new StringBuilder(url.toString());
        String url2=sb.insert(54, "song/").toString();
        //缩放
        url2+="?imageMogr2/thumbnail/333x430!";
        System.out.println(url2.toString());
        cosClient.shutdown();
        return url2;
    }

    /**
     * 使用永久密钥生成一个带签名的下载链接
     * @param key
     * @return
     */
    public static String getDown(String key){
        // 生成 cos 客户端。
        COSClient cosClient = getCOSClient();
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket_name, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());
        cosClient.shutdown();
        return url.toString();
    }

    public static void main(String[] args){
       getDown( "/luo/Git规范.DOCX");
    }
}
