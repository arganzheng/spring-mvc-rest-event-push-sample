package com.qq.ecc.openapi.eventpush.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 推送给callbackUrl使用的签名算法，与MP平台保持一致。
 * http://mp.weixin.qq.com/wiki/index.php?title=%E9%AA%8C%E8%AF%81%E6%B6%88%E6%81%AF%E7%9C%9F%E5%AE%9E%E6%80%A7
 * 
 * </pre>
 * 
 * @author arganzheng
 * @date 2013-11-7
 */
public class SignUtils {

    public static String makeSign(List<String> params) {
        return makeSign(params, null);
    }

    public static String makeSign(List<String> params, String encoding) {
        if (encoding == null) {
            encoding = "UTF-8";
        }
        try {
            // 1: make the sign source string
            String source = makeSignSource(params);
            // 2: make the sign
            return makeSign(source, encoding);
        } catch (Exception e) {
            throw new RuntimeException("make sign error!", e);
        }
    }

    public static String makeSign(String source, String charset) {
        if (charset == null) {
            charset = "UTF-8";
        }
        byte[] hash = encryptSHA1(source, charset);
        return byte2hex(hash);
    }

    /**
     * 生成签名所需源串
     * 
     * @param params 所有请求参数
     * @return 签名所需源串
     */
    private static String makeSignSource(List<String> valuesToSign) {
        Collections.sort(valuesToSign);
        StringBuffer sb = new StringBuffer();
        for (String str : valuesToSign) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static byte[] encryptSHA1(String data, String charset) {
        byte[] bytes = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(data.getBytes(charset));
            bytes = digest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = (java.lang.Integer.toHexString(b[i] & 0XFF));
            if (stmp.length() == 1) {
                hs.append(0).append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }
}
