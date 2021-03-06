package com.mark.demo.shiro_memched.base;

import java.util.List;

/*
*hxp(hxpwangyi@126.com)
*2017�?9�?9�?
*
*/
public interface GenericService <T extends GenericEntity>{
	List<T> findList(T entity) ;
	int delete(String refrencdId) ;
	int insert(T entity) ;
	int deleteByPrimaryKey(String refrenceid);
	PaginateResult<T> findPage(Pagination page, T entity);
	
}
