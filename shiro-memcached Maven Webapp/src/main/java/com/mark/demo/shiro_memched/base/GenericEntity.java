package com.mark.demo.shiro_memched.base;

import java.io.Serializable;


public abstract class GenericEntity implements Serializable {

    private static final long serialVersionUID = -5070399515553795258L;

    /**
     * 主键编号
     */
    protected int id;

    /**
     * 删除标识
     */
    protected Boolean delFlag = false;

    /**
     * 分页对象
     * <p>用于动�?�加入SQL分页语句的对�?</p>
     */
    protected Pagination pagination;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

   
}
