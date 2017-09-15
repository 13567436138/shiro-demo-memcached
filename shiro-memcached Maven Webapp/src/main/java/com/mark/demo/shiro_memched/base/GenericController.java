package com.mark.demo.shiro_memched.base;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Maps;
import com.mark.demo.shiro_memched.utils.BeanValidators;


public abstract class GenericController
{
    private static final Logger logger = Logger.getLogger(GenericController.class.getSimpleName());

    @Autowired
    protected Validator validator;

    protected boolean beanValidator(Model model, Object object, Class<?> ... groups)
    {
        try
        {
            BeanValidators.validateWithException(validator, object, groups);
        }
        catch (ConstraintViolationException ex)
        {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }
    

    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?> ... groups)
    {
        try
        {
            BeanValidators.validateWithException(validator, object, groups);
        }
        catch (ConstraintViolationException ex)
        {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败");
            addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }
 
    protected void addMessage(Model model, String ... messages)
    {
        StringBuilder sb = new StringBuilder();
        for (String message : messages)
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        model.addAttribute("message", sb.toString());
    }

    protected void addMessage(RedirectAttributes redirectAttributes, String ... messages)
    {
        StringBuilder sb = new StringBuilder();
        for (String message : messages)
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }
    
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException()
    {
        return "error/400";
    }
 
    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException()
    {
        return "error/403";
    }
    
  
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        
        binder.registerCustomEditor(String.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            
            @Override
            public String getAsText()
            {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
    
   
    protected String getRootPath(HttpServletRequest request)
    {
        String path = null;
        try
        {
            path = WebUtils.getRealPath(request.getSession()
                    .getServletContext(), "/");
        }
        catch (FileNotFoundException e)
        {
            logger.error(e);
        }
        return path;
    }
    
    public String getNuLLMsgStr(String name)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append(name).append("不能为空").toString();
    }

    public String getSelMsgStr(String name)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append("请�?�择").append(name).toString();
    }

    public String getMaxMsgStr(String name, Integer count)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append(name).append("的长度必须小�?").append(count).append("个字")
                .toString();
    }

    public Map<String, String> getErrMsgMap(String name, String message)
    {
        Map<String, String> errMsg = Maps.newHashMap();
        errMsg.put("errName", name);
        errMsg.put("errMsg", message);
        return errMsg;
    }
    
}
