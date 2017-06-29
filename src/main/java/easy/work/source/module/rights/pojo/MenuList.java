package easy.work.source.module.rights.pojo;

import java.util.List;

public class MenuList extends Menu {
	
	//对应的权限信息
	private List<Permission> permissionModels;

	public List<Permission> getPermissionModels() {
		return permissionModels;
	}

	public void setPermissionModels(List<Permission> permissionModels) {
		this.permissionModels = permissionModels;
	}

}
