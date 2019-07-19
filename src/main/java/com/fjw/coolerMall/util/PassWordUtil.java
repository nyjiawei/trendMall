package com.fjw.coolerMall.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 密码工具类
 * @author jiawei
 * 2018年7月31日下午10:12:24
 */
public class PassWordUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "!", "#", "@", "a", "b", "c", "d", "e", "f", "g", "F" };

    private Object salt;   //盐
    private String algorithm;

    public PassWordUtil(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm; //算法类型
    }
    
    /**
     * 明文加密
     * @param passWord 明文密码
     * @return 密文密码
     */
    public String encode(String passWord) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(
                    passWord).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }
    /**
     * 验证密码
     * @param encPass 密文
     * @param rawPass 明文
     * @return
     */
    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     * 
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / hexDigits.length;
        int d2 = n % hexDigits.length;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString();
        PassWordUtil encoderMd5 = new PassWordUtil("751d145e-fd79-4b35-a3f2-dc912ccd7d93", "sha-256");
        String encodedPassword = encoderMd5.encode("jiawei123");
        System.out.println("加密后密码：" + encodedPassword + "\n密码长度：" + encodedPassword.length());
        System.out.println("salt:" + salt);
        String encPass = "3!21dd210e2fbf044@@Fdc065610d1cc!a6b6@b21@1@#gcfca!5@4b1014fbF@4";
        String salt2 = "751d145e-fd79-4b35-a3f2-dc912ccd7d93";
        boolean is = encoderMd5.isPasswordValid(encPass, "jiawei123");
        System.out.println(is);
    }
    
}
