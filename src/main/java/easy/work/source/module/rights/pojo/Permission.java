package easy.work.source.module.rights.pojo;

import java.util.Date;

public class Permission {
	private Integer pid;

	private String permissionName;

	private String permissionAckey;

	private String permissionUrl;

	private Date addTime;

	private Date modifyTime;
	
	//菜单名称
	private String menuName;
	private Integer mid;
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionAckey() {
		return permissionAckey;
	}

	public void setPermissionAckey(String permissionAckey) {
		this.permissionAckey = permissionAckey;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}