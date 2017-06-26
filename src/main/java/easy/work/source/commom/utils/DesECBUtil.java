package easy.work.source.commom.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class DesECBUtil {


//    private static String ENCRYPT_KEY = "860978032535177";


    /**
     * 加密数据
     * param encryptString  注意：这里的数据长度只能为8的倍数
     * param encryptKey
     * return
     * throws Exception
     */
    public static String encryptDES(String encryptString,String ENCRYPT_KEY) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(ENCRYPT_KEY), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 若采用NoPadding模式，data长度必须是8的倍数
        // Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64Utils.encode(ConvertUtil.bytesToHexString(encryptedData).getBytes());
    }


    /**
     * 自定义一个key
     *
     * @param string
     */
    public static byte[] getKey(String keyRule) {
        Key key = null;
        byte[] keyByte = keyRule.getBytes();
        // 创建一个空的八位数组,默认情况下为0  
        byte[] byteTemp = new byte[8];
        // 将用户指定的规则转换成八位数组  
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, "DES");
        return key.getEncoded();
    }


    /**
     * Description: 解密
     * Date: 2016/1/7 11:24
     * Params: 参数说明
     * Return: 返回类型
     */
    public static String decryptDES(String decryptString,String ENCRYPT_KEY) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(ENCRYPT_KEY), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 若采用NoPadding模式，data长度必须是8的倍数
        // Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(ConvertUtil.hexStringToByte(decryptString));
        return new String(decryptedData);
//        return Base64Utils.encode(decryptedData);
    }
    
    
    public static void main(String[] args) throws Exception {
    	int i = (925411*2);
    	System.out.println(i);
//    	System.out.println(32535177aa/2);
    	String key = String.valueOf(i);
    	String encryptString = encryptDES("32535177aa",key);
		System.out.println(encryptString);
//		System.out.println(decryptDES("99000cf7","1980001"));
	}
}