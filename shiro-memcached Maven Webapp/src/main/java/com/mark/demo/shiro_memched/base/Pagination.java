package com.mark.demo.shiro_memched.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;


public class Pagination implements Serializable {

    private static final long serialVersionUID = -4312266410100339520L;

    // 是否有上�?�?
    private Boolean hasPreviousPage;

    // 是否有下�?�?
    private Boolean hasNextPage;

    // 每页的记录数
    private Integer pageSize = 20;

    // 当前是第几页
    private Integer currentPage = 1;

    // 记录�?始位�?
    private Integer startIndex = 0;

    // 记录的�?�数�?
    private Long totalCount;

    // 记录的�?�页�?
    private Integer totalPage;

    //排序对象
    private List<Sort> sorts;
    
    /**
     * 排序字段
     */
    @Deprecated
    private String orderBy;
    /**
     * 是否升序
     */
    @Deprecated
    private Boolean orderByAsc;
    

	/**
     * 构�?�器�?
     */
    public Pagination() {
        super();
    }

    public Pagination(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 构�?�器�?
     */
    public Pagination(Integer currentPage, Integer pageSize) {
        setCurrentPage(currentPage);
        setPageSize(pageSize);
        this.startIndex = (this.currentPage - 1) * this.pageSize;
    }

    /**
     * 构�?�器�?
     */
    public Pagination(Boolean hasPreviousPage, Boolean hasNextPage, Integer pageSize, Integer totalPage, Integer currentPage, Integer startIndex, Long totalCount) {
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
        setPageSize(pageSize);
        this.totalPage = totalPage;
        setCurrentPage(currentPage);
        this.startIndex = startIndex;
        this.totalCount = totalCount;
    }
    
    /**
     * @param currentPage
     * @param pageSize
     * @param orderBy
     * @param orderByAsc
     */
    public Pagination( Integer currentPage, Integer pageSize, String orderBy,
            Boolean orderByAsc)
    {
        super();
        setCurrentPage(currentPage);
        setPageSize(pageSize);
        this.orderBy = orderBy;
        setOrderByAsc(orderByAsc);
    }
    
    public void setOrderByAsc(Boolean orderByAsc)
    {
        if (orderByAsc == null)
        {
            this.orderByAsc = Boolean.TRUE;
        }
        else
        {
            this.orderByAsc = orderByAsc;
        }
    }
    
    /**
     * 获取多个Sort排序字符�?
     * @return
     */
    public String getSortToString()
    {
        if(CollectionUtils.isEmpty(sorts)){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Sort sort : sorts)
        {
            if(i == 0){
                sb.append(sort.toString() + "," );
            }else{
                sb.append(sort.toString(false) + ",");
            }
            i++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    /**
     * 获取单个Sort对象
     * @return
     */
    public Sort getOneSort(){
        if(CollectionUtils.isEmpty(sorts)){
            return null;
        }
        return sorts.get(0);
    }

    public void setSorts(Sort sort)
    {
        this.sorts = Lists.newArrayList(sort);
    }
    
    public void setSorts(List<Sort> sort)
    {
        this.sorts = sort;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    /**
     * 取得是否有上页的标记
     *
     * @return boolean 是否有上页的标记(true：有,false：无)
     */
    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * 设置是否有上页的标记
     *
     * @param hasPreviousPage 是否有上页的标记(true：有,false：无)
     */
    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    /**
     * 取得是否有下页的标记
     *
     * @return boolean 是否有下页的标记(true：有,false：无)
     */
    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    /**
     * 设置是否有下页的标记
     *
     * @param hasNextPage 是否有下页的标记(true：有,false：无)
     */
    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    /**
     * 取得每页显示的资料笔�?
     *
     * @return int 每页显示的资料笔�?(默认�?20)
     */
    public Integer getPageSize() {
        if (pageSize > 100) {
            return 100;
        }
        return pageSize;
    }

    /**
     * 设置每页显示的资料笔�?
     *
     * @param pageSize 每页显示的资料笔�?(默认�?20)
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize==null||pageSize < 1) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * 取得当前显示的页�?
     *
     * @return int 当前显示的页�?
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前显示的页�?
     *
     * @param currentPage 当前显示的页�?
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage==null || currentPage < 1) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
    }

    /**
     * 取得记录�?始位�?
     *
     * @return int 记录�?始位�?
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 设置记录�?始位�?
     *
     * @param startIndex 记录�?始位�?
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 取得资料总笔�?
     *
     * @return int 资料总笔�?
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置资料总笔�?
     *
     * @param totalCount 资料总笔�?
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 取得资料总页�?
     *
     * @return int 资料总页�?
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * 设置资料总页�?
     *
     * @param totalPage 资料总页�?
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    
    public Boolean getOrderByAsc()
    {
        return orderByAsc;
    }

}
