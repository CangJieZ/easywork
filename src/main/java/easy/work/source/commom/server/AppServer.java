package easy.work.source.commom.server;

import easy.work.source.commom.utils.StringUtil;

public class AppServer {
	private String host; // 域名
	private String protocol; // 协议名
	private String port; // 端口
	private String project; // 工程名
	public static String serverPath = "";

	private String securityAppServer;
	private String basePassortServer;
	private String passortServer;
	private String ssoServer;

	public String getBasePassortServer() {
		return basePassortServer;
	}

	public void setBasePassortServer(String basePassortServer) {
		this.basePassortServer = basePassortServer;
	}

	public String getPassortServer() {
		return passortServer;
	}

	public void setPassortServer(String passortServer) {
		this.passortServer = passortServer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String bulidImageServerPath() {
		String url = "";
		if (StringUtil.isBlank(port) || port.equals("80")) {
			url = this.protocol + "://" + this.host;
		} else {
			url = this.protocol + "://" + this.host + ":" + this.port;
		}
		if (!StringUtil.isBlank(project)) {
			url = url + "/" + this.project;
		}
		serverPath = url;
		return url;

	}

	public String getSecurityAppServer() {
		return securityAppServer;
	}

	public void setSecurityAppServer(String securityAppServer) {
		this.securityAppServer = securityAppServer;
	}

	/**
	 * @return the ssoServer
	 */
	public String getSsoServer() {
		return ssoServer;
	}

	/**
	 * @param ssoServer
	 *            the ssoServer to set
	 */
	public void setSsoServer(String ssoServer) {
		this.ssoServer = ssoServer;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
}
