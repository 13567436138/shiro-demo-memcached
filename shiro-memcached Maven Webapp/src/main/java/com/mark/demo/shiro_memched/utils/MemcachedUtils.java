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
*2017��9��12��
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
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean set(String key, Object value)  
    {  
        return setExp(key, value, 0);  
    }  
  
    /** 
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean set(String key, Object value, int expire)  
    {  
        return setExp(key, value, expire);  
    }  
  
    /** 
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
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
            // ��¼Memcached��־  
            MemcachedLog.writeLog("Memcached set��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean add(String key, Object value)  
    {  
        return addExp(key, value, 0);  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean add(String key, Object value, int expire)  
    {  
        return addExp(key, value, expire);  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
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
            // ��¼Memcached��־  
            MemcachedLog.writeLog("Memcached add��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean replace(String key, Object value)  
    {  
        return replaceExp(key, value, 0);  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean replace(String key, Object value, int expire)  
    {  
        return replaceExp(key, value, expire);  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
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
            MemcachedLog.writeLog("Memcached replace��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * get �������ڼ�����֮ǰ��ӵļ�ֵ����ص�ֵ�� 
     *  
     * @param key 
     *            �� 
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
            MemcachedLog.writeLog("Memcached get��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return obj;  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @return 
     */  
    public static boolean delete(String key)  
    {  
        return deleteExp(key, 0);  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean delete(String key, int expire)  
    {  
        return deleteExp(key, expire);  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
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
            MemcachedLog.writeLog("Memcached delete��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �������е����м�/ֵ�� 
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
            MemcachedLog.writeLog("Memcached flashAll��������\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �����쳣ջ��Ϣ��String���� 
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
     * @Description: Memcached��־��¼ 
     *  
     */  
    private static class MemcachedLog  
    {  
        private final static String MEMCACHED_LOG = "D:\\memcached.log";  
        private final static String LINUX_MEMCACHED_LOG = "/usr/local/logs/memcached.log";  
        private static FileWriter fileWriter;  
        private static BufferedWriter logWrite;  
        // ��ȡPID�������ҵ���Ӧ��JVM����  
        private final static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        private final static String PID = runtime.getName();  
  
        /** 
         * ��ʼ��д���� 
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
                logger.error("memcached ��־��ʼ��ʧ��", e);  
                closeLogStream();  
            }  
        }  
  
        /** 
         * д����־��Ϣ 
         *  
         * @param content 
         *            ��־���� 
         */  
        public static void writeLog(String content)  
        {  
            try  
            {  
                logWrite.write("[" + PID + "] " + "- ["  
                        + new SimpleDateFormat("yyyy��-MM��-dd�� hhʱ:mm��:ss��").format(new Date().getTime()) + "]\r\n"  
                        + content);  
                logWrite.newLine();  
                logWrite.flush();  
            }  
            catch (IOException e)  
            {  
                logger.error("memcached д����־��Ϣʧ��", e);  
            }  
        }  
  
        /** 
         * �ر��� 
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
                logger.error("memcached ��־����ر�ʧ��", e);  
            }  
        }  
    }  
}
