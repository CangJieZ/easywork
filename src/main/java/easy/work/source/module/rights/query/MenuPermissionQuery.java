package easy.work.source.module.rights.query;

import easy.work.source.commom.base.BaseQuery;

public class MenuPermissionQuery extends BaseQuery {

	private Integer id;

	// 菜单名称
	private String menuName;
	// 上级菜单名字 搜索
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
