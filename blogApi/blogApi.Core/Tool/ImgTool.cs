using Microsoft.AspNetCore.Http;
using OBS.Model;
using OBS;
using System;
using System.IO;
using blogApi.Core.obs;

namespace blogApi.Core.Tool
{
    public static class ImgTool
    {
        /// <summary>
        /// 上传图像
        /// </summary>
        /// <param name="image"></param>
        /// <returns>返回文件路径</returns>
        public static string UploadImage(IFormFile image)
        {
            if (image == null || image.Length == 0) return null;
            // 文件重命名
            string fileName = Guid.NewGuid().ToString() + Path.GetExtension(image.FileName);
            // 上传到桶
            try
            {
                Stream stream = image.OpenReadStream();
                PutObjectRequest request = new PutObjectRequest()
                {
                    BucketName = "bucketname",
                    ObjectKey = "objectname",
                    InputStream = stream,
                };
                PutObjectResponse response = ObsContext.GetObsClient().PutObject(request);
                Console.WriteLine("put object response: {0}", response.StatusCode);
            }
            catch (ObsException ex)
            {
                Console.WriteLine("ErrorCode: {0}", ex.ErrorCode);
                Console.WriteLine("ErrorMessage: {0}", ex.ErrorMessage);
            }
            return fileName;
          
        }

    }
}
