package fun.aprilsxz.blog.service;

public interface ObsService {

    boolean copyObject(String sourceObjectName,String destObjectName);

    boolean copyObjects(String sourceObjectName,String destObjectName);

    boolean deleteObject(String objectName);

    boolean deleteObjects(String objectsName);
}
