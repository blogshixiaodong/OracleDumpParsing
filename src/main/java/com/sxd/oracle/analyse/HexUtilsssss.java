package com.sxd.oracle.analyse;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;

/**
 * @author shixiaodong
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年04月20日 23:39:00
 */
public class HexUtilsssss {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException, DecoderException {
        String origin = "微网关平台";
        String gb2312 = Hex.encodeHexString(origin.getBytes("GB2312"));
        System.out.println(gb2312);
        byte[] bytes = Hex.decodeHex(gb2312);
        String ss = new String(bytes, "GB2312");
        System.out.println(ss);

    }

    public static String str2HexStr(String origin) {
        byte[] bytes = origin.getBytes();
        String hex = bytesToHexString(bytes);
        return hex;
    }

    public static String hexStr2Str(String hex) {
        byte[] bb = hexStringToBytes(hex);
        String rr = new String(bb);
        return rr;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}