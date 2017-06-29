package easy.work.source.module.rights.pojo;

import java.util.List;

public class PermissionTree {

	private Integer mid;

	private String menuName;

	private Integer parentId;

	private String parentName;
	
	private List<PermissionTree> subordinate;
	
	private List<Permission> permissions;
	
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<PermissionTree> getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(List<PermissionTree> subordinate) {
		this.subordinate = subordinate;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
