package cn.fatcarter.common.codec;

import cn.fatcarter.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA算法加密密钥对
 */
@RequiredArgsConstructor
public class RSA {
    private static final String ALGORITHM = "RSA";

    /**
     * 公钥 base64 编码后的字符串
     */
    private final PublicKey publicKey;

    /**
     * 私钥 base64 编码后的字符串
     */
    private final PrivateKey privateKey;

    @lombok.SneakyThrows
    public static RSA newKeyPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new RSA(keyPair.getPublic(), keyPair.getPrivate());
    }

    public static RSA newKeyPair(String base64EncodedPublicKey, String base64EncodedPrivateKey) throws InvalidKeySpecException {
        PublicKey pubKey = null;
        if (!StringUtils.isBlank(base64EncodedPublicKey)) {
            pubKey = loadPublicKey(base64EncodedPublicKey);
        }
        PrivateKey privateKey = null;
        if (!StringUtils.isBlank(base64EncodedPrivateKey)) {
            privateKey = loadPrivateKey(base64EncodedPrivateKey);
        }
        return new RSA(pubKey, privateKey);
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    private static PublicKey loadPublicKey(String base64EncodedPublicKey) throws InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64EncodedPublicKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    private static PrivateKey loadPrivateKey(String base64EncodedPrivateKey) throws InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        byte[] privateKeyBytes = Base64.getDecoder().decode(base64EncodedPrivateKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }


    @SneakyThrows
    public String encryptUsePubKey(String plainText) {
        byte[] originalDataBytes = plainText.getBytes();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(originalDataBytes));
    }


    @SneakyThrows
    public String encryptUsePrivateKey(String plainText) {
        byte[] originalDataBytes = plainText.getBytes();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(originalDataBytes));
    }

    @SneakyThrows({NoSuchAlgorithmException.class, NoSuchPaddingException.class, InvalidKeyException.class})
    public String decryptUsePubKey(String base64) throws IllegalBlockSizeException, BadPaddingException {
        byte[] originalDataBytes = Base64.getDecoder().decode(base64.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(originalDataBytes));
    }

    @SneakyThrows({NoSuchAlgorithmException.class, NoSuchPaddingException.class, InvalidKeyException.class})
    public String decryptUsePrivateKey(String base64) throws IllegalBlockSizeException, BadPaddingException {
        byte[] originalDataBytes = Base64.getDecoder().decode(base64.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(originalDataBytes));
    }


    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String getPrivateKey() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
}
