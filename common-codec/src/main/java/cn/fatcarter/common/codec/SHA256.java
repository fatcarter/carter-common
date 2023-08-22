package cn.fatcarter.common.codec;

import cn.fatcarter.common.net.ByteUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static String getString(String str) {
        return getString(str, DEFAULT_CHARSET);
    }

    public static String getString(String str, Charset charset) {
        return ByteUtils.toHex(get(str, charset));
    }

    public static byte[] get(String str) {
        return get(str, DEFAULT_CHARSET);
    }

    public static byte[] get(String str, Charset charset) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(str.getBytes(charset));
            return digest.digest();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }
}
