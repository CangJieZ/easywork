package easy.work.source.commom.utils;

import java.math.BigDecimal;

/**
 * @author chenjh   
 * @Description: 数字运算工具类 
 * @ClassName: NumUtils.java   
 * @date 2017年2月14日 下午2:54:11      
 * @说明  代码版权归
 */
public class NumUtils {
	
	
	/**
	 * @Auther: chenjh  
	 * @Description: 格式化小数
	 * @Date:2017年2月14日下午2:56:37
	 * @param inNum
	 * @return  
	 * @return float 
	 * @说明  代码版权归
	 */
	public static float formatNum(float inNum){
		BigDecimal b = new BigDecimal(inNum);  
		float f = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue(); 
		return f;
	}
	
	
	public static void main(String[] args) {
		System.out.println(formatNum(0.4174f));
	}

}
