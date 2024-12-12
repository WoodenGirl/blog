using System;

namespace blogApi.Core.sqlsugar
{
    public static class DbTool
    {
        public static void GenerateClass()
        {
            // 生成实体类
            DbContext.Instance.DbFirst.IsCreateAttribute()
                .Where(it => it.EndsWith("tb_dynamic"))
                .FormatFileName(it =>
                {
                    it = it.Replace("tb_", "");
                    it = string.Join("", it.Substring(0, 1).ToUpperInvariant(), it.Substring(1));
                    return it;
                })
                .FormatClassName(it =>
                {
                    it = it.Replace("tb_", "");
                    it = string.Join("", it.Substring(0, 1).ToUpperInvariant(), it.Substring(1));
                    return it;
                })
                .CreateClassFile("E:\\project\\blog\\blogApi\\blogApi.Core\\entity", "blogApi.Core.entity");
        }

    }
}
