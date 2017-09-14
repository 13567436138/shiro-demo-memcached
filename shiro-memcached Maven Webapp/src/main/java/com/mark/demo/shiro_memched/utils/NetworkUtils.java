package com.mark.demo.shiro_memched.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class NetworkUtils
{
    private static final Logger logger = Logger.getLogger(NetworkUtils.class);
    
    // 私有构�?�器，防止类的实例化
    private NetworkUtils()
    {
        super();
    }
    
    /**
     * 取得当前host地址<解决font face跨域问题，nginx下尝试失�?>
     * @param request HttpServletRequest
     * @return String 当前host地址
     */
    public static String getHost(HttpServletRequest request)
    {
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return path;
    }
    
    /**
     * 将IP地址转换为整数类�?
     * @param addr 字符串类型的IP地址
     * @return 整数
     */
    public static int ipToInt(final String addr)
    {
        int ip = 0;
        if (StringUtils.isNotBlank(addr))
        {
            final String[] addressBytes = addr.split("\\.");
            ip = 0;
            for (int i = 0; i < 4; i++)
            {
                ip <<= 8;
                ip |= Integer.parseInt(addressBytes[i]);
            }
        }
        return ip;
    }
    
    /**
     * 将整数类型的IP地址转换为字符串类型的IP地址
     * @param i 整数
     * @return IP地址
     */
    public static String intToIp(int i)
    {
        if (i == 0) return "";
        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }
    
    /**
     * 根据网卡取本机配置的IP
     * 如果是双网卡的，则取出外网IP
     * @return
     */
    public static String getNetIp()
    {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    /*
                     * System.out.println(ni.getName() + ";" +
                     * ip.getHostAddress() + ";ip.isSiteLocalAddress()=" +
                     * ip.isSiteLocalAddress() + ";ip.isLoopbackAddress()=" +
                     * ip.isLoopbackAddress());
                     */
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                    else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException e)
        {
            logger.error(e);
        }
        if (netip != null && !"".equals(netip))
        {
            return netip;
        }
        else
        {
            return localip;
        }
    }
    
    /**
    * 根据网卡取本机配置的内网IP
    * 如果是双网卡的，则取出内网IP
    * @return String 内网IP地址
    */
    public static String getLocalIp()
    {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {
                        localip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                }
            }
        }
        catch (SocketException e)
        {
            logger.error(e);
        }
        return localip;
    }
    
    /**
     * 获取客户端（访问者）IP地址
     * @param request
     * @return
     * @author 冯仁
     */
    public static int getRemortIpToInt(HttpServletRequest request)
    {
        String ip = NetworkUtils.getOriginalIpAddr(request);
        return ipToInt(ip);
    }
    
    /**
     * 获取 字符串类型的ip
     * @param request
     * @return
     * @author 郭小�?
     */
    public static String getOriginalIpAddr(HttpServletRequest request)
    {
        // 单元测试�? request 对象为空
        if (request == null) { return null; }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotBlank(ip) && ip.indexOf(",") != -1)
        {
            String[] strings = ip.split(",");
            ip = strings[0];
        }
        return ip;
    }
    
    /**
     * 获取服务器域�?
     * @return
     * @author 鲍建�?
     */
    public static String getDoMainName()
    {
        return "";
    }
}
