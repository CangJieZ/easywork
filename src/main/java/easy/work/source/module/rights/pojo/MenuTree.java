package easy.work.source.module.rights.pojo;

import java.util.List;

/**
 * 
 * @author zhuwt
 * @Description: 菜单树型
 * @ClassName: MenuCatalogTree.java
 * @date 2017年5月21日 下午10:11:45
 */
public class MenuTree {

	private Integer mid;

	private String menuName;

	private Integer parentId;

	private String parentName;

	private List<MenuTree> subordinate;

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

	public List<MenuTree> getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(List<MenuTree> subordinate) {
		this.subordinate = subordinate;
	}
	
}
