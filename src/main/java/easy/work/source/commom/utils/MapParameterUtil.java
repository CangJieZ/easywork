/**    
 * @author maomy  
 * @Description: 对象与map 互转工具类 
 * @Package com.cloud.common.utils   
 * @Title: BeanToMapUtil.java   
 * @date 2014-9-4 下午3:23:52   
 * @version V1.0 
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
package easy.work.source.commom.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapParameterUtil {

	
	/**
	 * @Auther: chenjh  
	 * @Description: map 转成 javabean
	 * @Date:2016年8月25日下午4:42:48
	 * @param obj
	 * @param map
	 * @return  
	 * @return Object 
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMapToBean(Object obj, Map<String,String[]> map){
		try {
			//map 转换
			Map mMap = changeMap(map);
			Class<? extends Object> clazz = obj.getClass();
			//反射获取域
			Field[] fileds = clazz.getDeclaredFields();
			for (Field field : fileds) {
				String propertyName = field.getName();
				if(mMap.containsKey(propertyName)){
					Object value = mMap.get(propertyName);
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				   Method setter = pd.getWriteMethod();//获得写方法
				   if(field.getType().getName().equals("java.lang.String")){
					   setter.invoke(obj, value);
				   }else if(field.getType().getName().equals("java.lang.Integer")){
					   String i = value.toString();
					   setter.invoke(obj, Integer.valueOf(i));
				   }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	
	/**
	 * @Auther: chenjh  
	 * @Description:  map 转换
	 * @Date:2016年8月25日下午4:52:49
	 * @param map
	 * @return  
	 * @return Map<String,String> 
	 */
	public static Map<String,String> changeMap(Map<String,String[]> map){
		Map<String,String>  map1 = new TreeMap<String, String>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key)[0];
			map1.put(key, value);
		}
		return map1;
	}

	/**
	 * @Auther: chenjh  
	 * @Description: map 遍历
	 * @Date:2016年8月26日上午9:19:19
	 * @param map
	 * @param excludeParams  排除的字段 不加签名串
	 * @return  
	 * @return String 
	 * @说明  代码版权归 杭州艮山网络科技有限公司 所有
	 */
	public static String getParametStringArray(Map<String,String[]> map,String excludeParams){
		Map<String,String> pmap = changeMap(map);
		//移除掉签名串
		pmap.remove(excludeParams);
		return getParametString(pmap);
	}
	
	
	/**
	 * @Auther: chenjh  
	 * @Description: map 遍历
	 * @Date:2016年8月26日上午9:19:19
	 * @param map
	 * @return  
	 * @return String 
	 */
	public static String getParametString(Map<String,String> map){
		StringBuffer sb = new StringBuffer();
		String[] keyArray = new String[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			keyArray[i] = key;

			i ++ ;
		}
		Arrays.sort(keyArray);
		
		for (String key : keyArray) {
			String value = (String)map.get(key);
			sb.append(key+"="+value).append("&");
		}
		String result = sb.toString().substring(0, sb.toString().lastIndexOf("&"));
		
		return result;
	}
	
}
