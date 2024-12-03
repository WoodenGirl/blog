import ObsClient from 'esdk-obs-browserjs'
import { ElMessage } from 'element-plus'

// OBS
const AccessKeyID = "XVMEKB4Q4OYRDZKLACAE"
const SecretAccessKey = "FaB0W6oLFcWbrKYg5kCoMV34Pjjq3bpB67gHK10I"
const BUCKET = "blog-aprilsxz"
const SERVER = "https://obs.cn-east-3.myhuaweicloud.com"


// 实例
const obsClient = new ObsClient({
  access_key_id: AccessKeyID,
  secret_access_key: SecretAccessKey,
  server: SERVER
});
// 上传对象
const putObject = (fileName: string, file: any) => {
  obsClient.putObject({
    Bucket: BUCKET,
    Key: fileName,
    SourceFile: file.raw
  }, function (err: string, result: any) {
    if(err){
      ElMessage.error("上传失败!");
      console.log(err)
    }else{
      console.log('Status-->' + result.CommonMsg.Status);
    }
  });
}
// 删除对象
const popObject = (fileName: string) => {
  obsClient.deleteObject({
    Bucket: BUCKET,
    Key : fileName
  }, function (err: string, result: any) {
    if(err){
      ElMessage.error("删除失败！")
      console.log('Error-->' + err);
    }else{
      console.log('Status-->' + result.CommonMsg.Status);
    }
  });
}
export { putObject, popObject }
