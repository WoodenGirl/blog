using System;


namespace blogApi.Core
{
    public class Tool
    {
        public static string GenerateId(string preStr)
        {
            return preStr + '_' + Guid.NewGuid().ToString("N"); // 返回32位的连续数字字母组合字符串
        }
        
    }
}
