package com.mark.demo.shiro_memched.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class LoggerUtils
{
    private LoggerUtils()
    {
        super();
    }
    
    
    
    /**
     * logInfo
     * @param logger Logger
     * @param string String
     */
    public static void logInfo(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isInfoEnabled())
        {
            logger.info(string);
            if (null != ste) logger.info("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logInfo
     * @param logger Logger
     * @param string String
     */
    public static void logInfo(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isInfoEnabled())
        {
            logger.info(string);
        }
    }
    
    /**
     * logError
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     */
    public static void logError(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isErrorEnabled())
        {
            logger.error(string);
            if (null != ste) logger.error("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logError
     * @param logger Logger
     * @param string String
     */
    public static void logError(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isErrorEnabled())
        {
            logger.error(string);
        }
    }
    
    /**
     * logWarn
     * @param logger Logger
     * @param string String
     */
    public static void logWarn(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isWarnEnabled())
        {
            logger.warn(string);
        }
    }
    
    /**
     * logWarn
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     */
    public static void logWarn(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isWarnEnabled())
        {
            logger.warn(string);
            if (null != ste) logger.warn("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logDebug
     * @param logger Logger
     * @param string String
     */
    public static void logDebug(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isDebugEnabled())
        {
            logger.debug(string);
        }
    }
    
    /**
     * logDebug
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     */
    public static void logDebug(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isDebugEnabled())
        {
            logger.debug(string);
            if (null != ste) logger.debug("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logTrace
     * @param logger Logger
     * @param string String
     */
    public static void logTrace(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isTraceEnabled())
        {
            logger.trace(string);
        }
    }
    
    /**
     * logTrace
     * @param logger Logger
     * @param ste StackTraceElement
     * @param string String
     */
    public static void logTrace(Logger logger, StackTraceElement ste, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isTraceEnabled())
        {
            logger.trace(string);
            if (null != ste) logger.trace("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logInfo
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logInfo(Logger logger, String string, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isInfoEnabled() && StringUtils.isNotBlank(string))
        {
            logger.info(string, object);
        }
    }
    
    /**
     * logInfo
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     * @param object Object
     */
    public static void logInfo(Logger logger, String string, StackTraceElement ste, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isInfoEnabled() && StringUtils.isNotBlank(string))
        {
            logger.info(string, object);
            if (null != ste) logger.info("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logError
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logError(Logger logger, String string, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isErrorEnabled() && StringUtils.isNotBlank(string))
        {
            logger.error(string, object);
        }
    }
    
    /**
     * logError
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     * @param object Object
     */
    public static void logError(Logger logger, String string, StackTraceElement ste, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isErrorEnabled() && StringUtils.isNotBlank(string))
        {
            logger.error(string, object);
            if (null != ste) logger.error("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logWarn
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logWarn(Logger logger, String string, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isWarnEnabled() && StringUtils.isNotBlank(string))
        {
            logger.warn(string, object);
        }
    }
    
    /**
     * logWarn并输出文件名称所在行�?
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     * @param object Object
     */
    public static void logWarn(Logger logger, String string, StackTraceElement ste, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isWarnEnabled() && StringUtils.isNotBlank(string))
        {
            logger.warn(string, object);
            if (null != ste) logger.warn("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logDebug并输出文件名称所在行�?
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logDebug(Logger logger, String string, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isDebugEnabled() && StringUtils.isNotBlank(string))
        {
            logger.debug(string, object);
        }
    }
    
    /**
     * logDebug并输出文件名称所在行�?
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     * @param object Object
     */
    public static void logDebug(Logger logger, String string, StackTraceElement ste, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isDebugEnabled() && StringUtils.isNotBlank(string))
        {
            logger.debug(string, object);
            if (null != ste) logger.debug("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
    
    /**
     * logTrace
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logTrace(Logger logger, String string, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isTraceEnabled() && StringUtils.isNotBlank(string))
        {
            logger.trace(string, object);
        }
    }
    
    /**
     * logTrace并输出文件名称，�?在行�?
     * @param logger Logger
     * @param string String
     * @param ste StackTraceElement
     * @param object Object
     */
    public static void logTrace(Logger logger, String string, StackTraceElement ste, Object ... object)
    {
        // object为占位符格式的日志输�?
        if (null != logger && logger.isTraceEnabled() && StringUtils.isNotBlank(string))
        {
            logger.trace(string, object);
            if (null != ste) logger.trace("文件名称�?" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
}