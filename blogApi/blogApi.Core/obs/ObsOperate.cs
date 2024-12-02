using blogApi.Core.sqlsugar;
using Furion;
using Microsoft.AspNetCore.Http;
using OBS;
using OBS.Model;
using System;
using System.IO;


namespace blogApi.Core.obs
{
    public static class ObsOperate
    {
        private static string endpoint = "https://obs.cn-east-3.myhuaweicloud.com";
        private static string AK = App.Configuration["AccessKey"];
        private static string SK = App.Configuration["SecretKey"];

        private static ObsClient client = new ObsClient(AK, SK, endpoint);

        private static string bucketName = "blog-article";

        public static string UploadImage(IFormFile image)
        {
            if (image == null || image.Length == 0) return "101";
            try
            {
                PutObjectRequest request = new PutObjectRequest()
                {
                    BucketName = bucketName,
                    ObjectKey = image.FileName,
                    InputStream = image.OpenReadStream(),
                };
                PutObjectResponse response = client.PutObject(request);
                Console.WriteLine("put object response: {0}", response.StatusCode);
                return image.FileName;
            }
            catch (ObsException ex)
            {
                Console.WriteLine("Exception errorcode: {0}, when create a bucket.", ex.ErrorCode);
                Console.WriteLine("Exception errormessage: {0}", ex.ErrorMessage);
                return "201";
            }
            
        }
        public static void deleteImage(string fileName)
        {
            try
            {
                DeleteObjectRequest request = new DeleteObjectRequest()
                {
                    BucketName = bucketName,
                    ObjectKey = fileName,
                };
                DeleteObjectResponse response = client.DeleteObject(request);
                Console.WriteLine("Delete object response: {0}", response.StatusCode);
            }
            catch (ObsException ex)
            {
                Console.WriteLine("ErrorCode: {0}", ex.ErrorCode);
                Console.WriteLine("ErrorMessage: {0}", ex.ErrorMessage);
            }

        }
    }
}
