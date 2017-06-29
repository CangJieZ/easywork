package easy.work.source.module.rights.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import easy.work.source.commom.utils.DateTimeUtil;

public class BackUserPojo {
    private Integer uid;

    private String userName;

    private String userAccount;

    private String userPwd;

    private Date addTime;

    private Date modifyTime;

    //角色ID
    private Integer rid;
    //角色名称
    private String roleName;
    
    
    public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

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
		map.put("login_time", DateTimeUtil.getCurrDateTimeStr());
		map.put("login_pwd", this.getUserPwd());
		map.put("role_id", String.valueOf(this.getRid()));
		map.put("role_name", this.getRoleName());
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
		user.setRid(Integer.valueOf(map.get("role_id")));
		user.setRoleName(map.get("role_name"));
		return user;
	}
}