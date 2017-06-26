package easy.work.source.module.user.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BackUserPojo {
    private Integer uid;

	private String userName;

	private String userAccount;

	private String userPwd;

	private Date addTime;

	private Date modifyTime;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount == null ? null : userAccount.trim();
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd == null ? null : userPwd.trim();
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
	
	/**
	 * 把user的属性转换成map
	 * 
	 * @Description
	 * @return
	 * 
	 */
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(this.getUid()));
		map.put("login_name", this.getUserAccount());
		map.put("user_name", this.getUserName());
		map.put("login_pwd", this.getUserPwd());
		return map;

	}
	
	
	/**
	 * 根据map创建一个user对象实例
	 * 
	 * @Description
	 * @param map
	 * @return
	 * 
	 */
	public static BackUserPojo create(Map<String, String> map) {
		BackUserPojo user = new BackUserPojo();
		user.setUid(Integer.valueOf(map.get("id")));
		user.setUserAccount(map.get("login_name"));
		user.setUserName(map.get("user_name"));
		user.setUserPwd(map.get("login_pwd"));
		return user;
	}

}