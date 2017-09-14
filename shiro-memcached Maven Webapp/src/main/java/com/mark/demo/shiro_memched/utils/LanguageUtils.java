package com.mark.demo.shiro_memched.utils;

public class LanguageUtils
{
    // 私有构�?�器
    private LanguageUtils()
    {
        super();
    }
    
    /**  
     * 半角转全�?  
     * @param input 要转换的字符�?
     * @return String 转换后的字符�?
     */
    public static String banToQuan(String input)
    {
        if (null == input)
        {
            return null;
        }
        else
        {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++)
            {
                if (c[i] == ' ')
                {
                    c[i] = '\u3000';
                }
                else if (c[i] < '\177')
                {
                    c[i] = (char) (c[i] + 65248);
                }
            }
            return new String(c);
        }
    }
    
    /**  
     * 全角转半�? 
     * @param input 要转换的字符�?  
     * @return String 转换后的字符�?
     */
    public static String quanToBan(String input)
    {
        if (null == input) return null;
        else
        {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++)
            {
                if (c[i] == '\u3000')
                {
                    c[i] = ' ';
                }
                else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
                {
                    c[i] = (char) (c[i] - 65248);
                }
            }
            String returnString = new String(c);
            return returnString;
        }
    }
}
