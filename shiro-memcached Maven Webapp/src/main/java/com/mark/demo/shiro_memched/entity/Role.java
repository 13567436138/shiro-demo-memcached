package com.mark.demo.shiro_memched.entity;

import java.util.List;

/*
*hxp(hxpwangyi@126.com)
*2017�?9�?5�?
*
*/
public class Role {
	private long roleId;
	private String roleName;
	private String roleDesc;
	
	private List<Permission> permissionList;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

}
