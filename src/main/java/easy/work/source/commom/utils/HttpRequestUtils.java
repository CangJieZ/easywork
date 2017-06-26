package easy.work.source.commom.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtils {
	private static Log logger = LogFactory.getLog(HttpRequestUtils.class);

	/**
	 * @Auther: chenjh
	 * @Description: 判断是否是ajax请求
	 * @Date:2017年5月16日下午4:37:26
	 * @return
	 * @return boolean
	 */
	public static boolean  isAjaxRequest(HttpServletRequest request) {
		if (null != request.getHeader("X-Requested-With")
				&& request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest"))
			return true;
		return false;
	}

	/**
	 * @Auther: chenjh  
	 * @Description: 以流的方式写到页面
	 * @Date:2017年5月19日下午3:14:06
	 * @param content
	 * @param response  
	 * @return void
	 */
	public static void write(String content, HttpServletResponse response) {
		try {
			// response.setContentType("application/json,text/javascript,text/html;charset=UTF-8");
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * httpPost
	 * 
	 * @param url
	 *            路径
	 * @param jsonParam
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					if (noNeedResponse) {
						return null;
					}
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		}
		return jsonResult;
	}

}
