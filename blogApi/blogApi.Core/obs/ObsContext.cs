using Furion;
using OBS;
using System;


namespace blogApi.Core.obs
{
    public class ObsContext
    {
        public static ObsClient GetObsClient()
        {
            ObsConfig config = new ObsConfig();
            config.Endpoint = "https://tobs.cn-east-3.myhuaweicloud.com";
            return new ObsClient(App.Configuration["AccessKey"], App.Configuration["SecrectKey"], config);
        }

    }
}
