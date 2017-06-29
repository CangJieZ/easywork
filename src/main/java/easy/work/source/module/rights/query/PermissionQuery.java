package easy.work.source.module.rights.query;

import easy.work.source.commom.base.BaseQuery;

public class PermissionQuery extends BaseQuery {

	private String permissionName;// 权限名称

	private String permissionUrl;// 权限地址
	
	private String permissionAckey;
	
	private String menuName;
	
	public String getPermissionAckey() {
		return permissionAckey;
	}

	public void setPermissionAckey(String permissionAckey) {
		this.permissionAckey = permissionAckey;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

}
