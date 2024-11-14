namespace blogApi.Core.entity
{
    public partial class ResultVo
    {
        public int code { set; get; }
        public string message { set; get; }
        public object data { set; get; }

        public ResultVo(int code, string message, object data)
        {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public static ResultVo success(object data)
        {
            return new ResultVo(200, "success", data);
        }
        public static ResultVo success()
        {
            return new ResultVo(200, "success", null);
        }
        public static ResultVo error(int code, string message)
        {
            return new ResultVo(code, message, null);
        }
    }
}
