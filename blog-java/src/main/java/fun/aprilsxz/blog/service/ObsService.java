package fun.aprilsxz.blog.service;

public interface ObsService {

    boolean copyObject(String sourceObjectName,String destObjectName);

    boolean copyObjects(String[] sourceObjectNames,String[] destObjectNames);

    boolean deleteObject(String objectName);

    boolean deleteObjects(String[] objectsName);

    boolean moveObject(String sourceObjectName,String destObjectName);

    boolean moveObjects(String[] sourceObjectNames,String[] destObjectNames);
}
