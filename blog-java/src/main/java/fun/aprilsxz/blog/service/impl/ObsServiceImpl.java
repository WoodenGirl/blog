package fun.aprilsxz.blog.service.impl;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import fun.aprilsxz.blog.config.ObsProperties;
import fun.aprilsxz.blog.service.ObsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ObsServiceImpl implements ObsService {

    @Resource
    ObsProperties obsProperties;
    @Resource
    ObsClient obsClient;

    /**
     * 复制一个对象
     * @param sourceObjectName 源文件名
     * @param destObjectName 目标文件名
     */
    @Override
    public boolean copyObject(String sourceObjectName,String destObjectName) {
        try {
            // 简单复制
            CopyObjectResult result =
                    obsClient.copyObject(obsProperties.getBucketName(), sourceObjectName, obsProperties.getBucketName(), destObjectName);
        } catch (ObsException e) {
            System.out.println("copyObject failed");
            printFail(e);
            return false;
        }
        return true;
    }

    /**
     * 复制多个对象
     * @param sourceObjectName 源文件名，以逗号分割
     * @param destObjectName 目标文件名
     */
    @Override
    public boolean copyObjects(String sourceObjectName, String destObjectName) {
        String[] objects = sourceObjectName.split(",");
        for (String object : objects) {
            if(!copyObject(object,destObjectName + object)){
                return false;
            }
        }
        return true;
    }

    /**
     * 删除一个对象
     * @param objectName 文件名
     * @return 操作是否成功
     */
    @Override
    public boolean deleteObject(String objectName) {
        try {
            // 删除单个对象
            obsClient.deleteObject(obsProperties.getBucketName(), objectName);
        } catch (ObsException e) {
            System.out.println("deleteObject failed");
            printFail(e);
            return false;
        }
        return true;
    }

    /**
     * 删除多个对象
     * @param objectsName 文件名，以逗号分隔
     * @return 操作是否成功
     */
    @Override
    public boolean deleteObjects(String objectsName) {
        String[] objects = objectsName.split(",");
        KeyAndVersion[] keyAndVersions = new KeyAndVersion[objects.length];
        for (int i = 0; i < objects.length; i++) {
            keyAndVersions[i].setKey(objects[i]);
        }
        try {
            String bucketName = obsProperties.getBucketName();
            DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName);
            deleteRequest.setKeyAndVersions(keyAndVersions);

            DeleteObjectsResult deleteResult = obsClient.deleteObjects(deleteRequest);
            // 获取删除成功的对象
            System.out.println("DeletedObjectResults:"+ deleteResult.getDeletedObjectResults());
            // 获取删除失败的对象
            System.out.println("ErrorResults:"+ deleteResult.getErrorResults());

            System.out.println("deleteObjects successfully");
        } catch (ObsException e) {
            System.out.println("deleteObjects failed");
            printFail(e);
            Map<String, String> headers = e.getResponseHeaders();// 遍历Map的entry,打印所有报错相关头域
            if(headers != null){
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    if(header.getKey().contains("error")){
                        System.out.println(header.getKey()+":"+header.getValue());
                    }
                }
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean moveObject(String sourceObjectName, String destObjectName) {
        boolean b = copyObject(sourceObjectName, destObjectName);
        if(!b){
            return false;
        }
        return deleteObject(sourceObjectName);
    }

    private void printFail(ObsException e) {
        // 请求失败,打印http状态码
        System.out.println("HTTP Code:" + e.getResponseCode());
        // 请求失败,打印服务端错误码
        System.out.println("Error Code:" + e.getErrorCode());
        // 请求失败,打印详细错误信息
        System.out.println("Error Message:" + e.getErrorMessage());
        // 请求失败,打印请求id
        System.out.println("Request ID:" + e.getErrorRequestId());
        System.out.println("Host ID:" + e.getErrorHostId());
        e.printStackTrace();
    }

}
