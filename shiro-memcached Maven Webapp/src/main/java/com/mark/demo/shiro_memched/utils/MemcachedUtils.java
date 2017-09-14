package com.mark.demo.shiro_memched.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.rubyeye.xmemcached.XMemcachedClient;;

/*
*hxp(hxpwangyi@126.com)
*2017年9月12日
*
*/
public class MemcachedUtils {
	private static final Logger logger = LoggerFactory.getLogger(MemcachedUtils.class);  
    private static XMemcachedClient memcachedClient;  
    
    static{
    	memcachedClient=SpringUtils.getBean(XMemcachedClient.class);
    }
  
    private MemcachedUtils()  
    {  
    }  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean set(String key, Object value)  
    {  
        return setExp(key, value, 0);  
    }  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean set(String key, Object value, int expire)  
    {  
        return setExp(key, value, expire);  
    }  
  
    /** 
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean setExp(String key, Object value, Integer expire)  
    {  
        boolean flag = false;  
        try  
        {  
            flag = memcachedClient.set(key, expire, value);
        }  
        catch (Exception e)  
        {  
            // 记录Memcached日志  
            MemcachedLog.writeLog("Memcached set方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean add(String key, Object value)  
    {  
        return addExp(key, value, 0);  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean add(String key, Object value, int expire)  
    {  
        return addExp(key, value, expire);  
    }  
  
    /** 
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean addExp(String key, Object value, int expire)  
    {  
        boolean flag = false;  
        try  
        {  
            flag = memcachedClient.add(key,expire, value);  
        }  
        catch (Exception e)  
        {  
            // 记录Memcached日志  
            MemcachedLog.writeLog("Memcached add方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @return 
     */  
    public static boolean replace(String key, Object value)  
    {  
        return replaceExp(key, value, 0);  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean replace(String key, Object value, int expire)  
    {  
        return replaceExp(key, value, expire);  
    }  
  
    /** 
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean replaceExp(String key, Object value, int expire)  
    {  
        boolean flag = false;  
        try  
        {  
            flag = memcachedClient.replace(key,expire, value);  
        }  
        catch (Exception e)  
        {  
            MemcachedLog.writeLog("Memcached replace方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * get 命令用于检索与之前添加的键值对相关的值。 
     *  
     * @param key 
     *            键 
     * @return 
     */  
    public static Object get(String key)  
    {  
        Object obj = null;  
        try  
        {  
            obj = memcachedClient.get(key);  
        }  
        catch (Exception e)  
        {  
            MemcachedLog.writeLog("Memcached get方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return obj;  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @return 
     */  
    public static boolean delete(String key)  
    {  
        return deleteExp(key, 0);  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    public static boolean delete(String key, int expire)  
    {  
        return deleteExp(key, expire);  
    }  
  
    /** 
     * 删除 memcached 中的任何现有值。 
     *  
     * @param key 
     *            键 
     * @param expire 
     *            过期时间 New Date(1000*10)：十秒后过期 
     * @return 
     */  
    private static boolean deleteExp(String key, int expire)  
    {  
        boolean flag = false;  
        try  
        {  
            flag = memcachedClient.delete(key, expire);  
        }  
        catch (Exception e)  
        {  
            MemcachedLog.writeLog("Memcached delete方法报错，key值：" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 清理缓存中的所有键/值对 
     *  
     * @return 
     */  
    public static boolean flashAll()  
    {  
        boolean flag = true;  
        try  
        {  
            memcachedClient.flushAll();  
        }  
        catch (Exception e)  
        {  
        	flag=false;
            MemcachedLog.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * 返回异常栈信息，String类型 
     *  
     * @param e 
     * @return 
     */  
    private static String exceptionWrite(Exception e)  
    {  
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        e.printStackTrace(pw);  
        pw.flush();  
        return sw.toString();  
    }  
  
    /** 
     *  
     * @ClassName: MemcachedLog 
     * @Description: Memcached日志记录 
     *  
     */  
    private static class MemcachedLog  
    {  
        private final static String MEMCACHED_LOG = "D:\\memcached.log";  
        private final static String LINUX_MEMCACHED_LOG = "/usr/local/logs/memcached.log";  
        private static FileWriter fileWriter;  
        private static BufferedWriter logWrite;  
        // 获取PID，可以找到对应的JVM进程  
        private final static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        private final static String PID = runtime.getName();  
  
        /** 
         * 初始化写入流 
         */  
        static  
        {  
            try  
            {  
                String osName = System.getProperty("os.name");  
                if (osName.indexOf("Windows") == -1)  
                {  
                    fileWriter = new FileWriter(MEMCACHED_LOG, true);  
                }  
                else  
                {  
                    fileWriter = new FileWriter(LINUX_MEMCACHED_LOG, true);  
                }  
                logWrite = new BufferedWriter(fileWriter);  
            }  
            catch (IOException e)  
            {  
                logger.error("memcached 日志初始化失败", e);  
                closeLogStream();  
            }  
        }  
  
        /** 
         * 写入日志信息 
         *  
         * @param content 
         *            日志内容 
         */  
        public static void writeLog(String content)  
        {  
            try  
            {  
                logWrite.write("[" + PID + "] " + "- ["  
                        + new SimpleDateFormat("yyyy年-MM月-dd日 hh时:mm分:ss秒").format(new Date().getTime()) + "]\r\n"  
                        + content);  
                logWrite.newLine();  
                logWrite.flush();  
            }  
            catch (IOException e)  
            {  
                logger.error("memcached 写入日志信息失败", e);  
            }  
        }  
  
        /** 
         * 关闭流 
         */  
        private static void closeLogStream()  
        {  
            try  
            {  
                fileWriter.close();  
                logWrite.close();  
            }  
            catch (IOException e)  
            {  
                logger.error("memcached 日志对象关闭失败", e);  
            }  
        }  
    }  
}
