package easy.work.source.commom.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @Description :参数处理
 * @version 1.0
 */
public class ParameterUtil {
	private static final Log log = LogFactory.getLog(ParameterUtil.class);

	/**
	 * 
	 * @Description 把name=value&name=value&name=value&name=value的字符串转换成map
	 * @param paramString
	 * @return
	 */
	public static Map<String, String> toMap(String paramString) {
		if (paramString == null)
			return null;

		String[] nameValueArr = paramString.split("\\&");
		if (nameValueArr == null || nameValueArr.length == 0)
			return null;

		Map<String, String> map = new HashMap<String, String>();
		for (String nameValue : nameValueArr) {
			if (nameValue == null)
				continue;
			String[] param = nameValue.split("=");
			if (param == null || param.length == 0)
				continue;

			map.put(param[0], param.length > 1 ? (param[1] == null ? "" : param[1]) : "");
		}

		return map;
	}

	/**
	 * 
	 * @Description
	 * @param map
	 *            把map转换成name=value&name=value&name=value&name=value各式的字符串
	 * @return
	 */
	public static String toString(Map<String, String> map) {
		if (map == null)
			return null;
		Set<Entry<String, String>> set = map.entrySet();
		if (set == null || set.size() == 0)
			return null;
		StringBuilder bu = new StringBuilder();

		int i = 0;
		for (Entry<String, String> entry : set) {
			bu.append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : entry.getValue());
			if (i + 1 < set.size()) {
				bu.append("&");
			}
			i++;
		}

		return bu.toString();
	}

	/**
	 * 
	 * @Description 把对象的属性，转换成name=value&name=value&name=value&name=value各式的字符串
	 * @param obj
	 * @return
	 * 
	 */
	public static String toString(Object target) {
		if (target == null)
			return null;
		StringBuilder bu = new StringBuilder();
		// 取定义的全部属性
		Field[] fields = target.getClass().getDeclaredFields();
		if (fields == null) {
			return null;
		}

		int i = 0;
		for (Field field : fields) {
			if (field.getAnnotation(IgnoreToString.class) != null) {
				continue;
			}
			// 属性值
			Object value = null;
			try {
				boolean original = field.isAccessible();
				field.setAccessible(true);
				value = field.get(target);
				field.setAccessible(original);
				bu.append(field.getName()).append("=").append(value);
				if (i + 1 < fields.length)
					bu.append("&");
				i++;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}

		return bu.toString();
	}
	
	/**
	 * 是否忽略转换成字符串
	 * 
	 * @Description :
	 * @version 1.0
	 * 
	 */
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@Inherited
	public static @interface IgnoreToString {
	}
	
	
	/**
	 * @Auther: chenjh   
	 * @Description: 从 HttpServletRequest 获取传过来参数
	 * @Date:2017年6月8日上午10:46:46
	 * @param request
	 * @return  
	 * @return String
	 */
	public static Map<String, String> getRequestParamAndValue(HttpServletRequest request) {
		Map<String, String> mapParam = toMap(getParam(request));
		return mapParam;

	}
	
	
	/**
	 * @Auther: chenjh  
	 * @Description: 获取参数 
	 * @Date:2017年6月8日上午10:54:22
	 * @param request
	 * @return  
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getParam(HttpServletRequest request) {

		StringBuffer value = new StringBuffer();

		Enumeration enu = request.getParameterNames();

		while (enu.hasMoreElements()) {

			String paraName = (String) enu.nextElement();

			if (!"_dc".equals(paraName) && !"node".equals(paraName)) {// _dc的参数不要

				String[] arr = request.getParameterValues(paraName);

				if (arr != null && arr.length > 1) {
					value.append(paraName+"=").append(convertObjectArrToStr(arr)).append("&");
				} else {
					value.append(paraName+"=").append(convertObjectArrToStr(arr)).append("&");
				}

			}

		}
		String ret = value.toString();
		if(ret.length()>1)
			return ret.substring(0, ret.length()-1);
		return "";

	}

	/**
	 * 
	 * 把对象列表,转化成逗号分隔的字符串
	 * 
	 * @author SHANSHAN
	 * 
	 */

	public static String convertObjectArrToStr(Object[] arr) {

		String result = "";

		if (arr != null && arr.length > 0) {

			for (int i = 0; i < arr.length; i++) {

				if (!"".equals(String.valueOf(arr[i]))) {

					result += String.valueOf(arr[i]) + ",";
				}

			}

			if (!"".equals(result)) {

				result = result.substring(0, result.length() - 1);

			}

		}

		return result;

	}

}
