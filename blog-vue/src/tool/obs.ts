import ObsClient from 'esdk-obs-browserjs'
import { ElMessage } from 'element-plus'

// OBS
const AccessKeyID = import.meta.env.VITE_ACCESS_KEY
const SecretAccessKey = import.meta.env.VITE_SECRET_KEY
const BUCKET = import.meta.env.VITE_OBS_BUCKET
const SERVER = import.meta.env.VITE_OBS_SERVER


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
    SourceFile: file
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
