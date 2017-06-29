package easy.work.source.module.rights.pojo;

import java.util.List;

public class RoleAndPremission extends Role {

	private List<Permission> permissionsList;

	public List<Permission> getPermissionsList() {
		return permissionsList;
	}

	public void setPermissionsList(List<Permission> permissionsList) {
		this.permissionsList = permissionsList;
	}
	
}
