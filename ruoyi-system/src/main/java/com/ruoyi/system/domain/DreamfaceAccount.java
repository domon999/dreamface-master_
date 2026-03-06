package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 dreamface_account
 * 
 * @author ruoyi
 * @date 2026-01-13
 */
public class DreamfaceAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 用户令牌 */
    @Excel(name = "用户令牌")
    private String token;

    /** 用户代理（浏览器/设备信息） */
    @Excel(name = "用户代理", readConverterExp = "浏=览器/设备信息")
    private String useragent;

    /** 用户Cookie信息 */
    @Excel(name = "用户Cookie信息")
    private String cookie;

    /** 1-使用 2-不使用 */
    @Excel(name = "1-使用 2-不使用")
    private String isUsed;

    /** 检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checktime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }

    public void setUseragent(String useragent) 
    {
        this.useragent = useragent;
    }

    public String getUseragent() 
    {
        return useragent;
    }

    public void setCookie(String cookie) 
    {
        this.cookie = cookie;
    }

    public String getCookie() 
    {
        return cookie;
    }

    public void setIsUsed(String isUsed) 
    {
        this.isUsed = isUsed;
    }

    public String getIsUsed() 
    {
        return isUsed;
    }

    public void setChecktime(Date checktime) 
    {
        this.checktime = checktime;
    }

    public Date getChecktime() 
    {
        return checktime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
            .append("useragent", getUseragent())
            .append("cookie", getCookie())
            .append("isUsed", getIsUsed())
            .append("checktime", getChecktime())
            .toString();
    }
}
