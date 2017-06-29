package easy.work.source.module.rights.pojo;

import java.util.List;

/**
 * @author chenjh   
 * @Description: 页面上js需要用的菜单对象
 * @ClassName: MenuJsPojo.java   
 * @date 2017年5月18日 下午5:01:25
 */
public class MenuJs {
	
	/**
	 * 菜单ID
	 */
	private int id;
	
	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 父菜单ID
	 */
	private int parentId;
	
	/**
	 * 菜单query地址
	 */
	private String url;
	
	/**
	 * 菜单图标
	 */
	private String icon="";
	
	/**
	 * 菜单头
	 */
	private String isHeader = "0";
	
	/**
	 * 子菜单
	 */
	private List<MenuJs> childMenus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsHeader() {
		return isHeader;
	}

	public void setIsHeader(String isHeader) {
		this.isHeader = isHeader;
	}

	public List<MenuJs> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuJs> childMenus) {
		this.childMenus = childMenus;
	}
	
	
	
	
	

}
