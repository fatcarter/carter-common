package cn.fatcarter.common.codec;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.spec.InvalidKeySpecException;

public class RSATest {

    @Test
     public void test(){
         System.out.println((-9 % 5)+5 );
     }

    @Test
    public void testNewKeyPair() {
        RSA rsa = RSA.newKeyPair();
        Assert.assertNotNull(rsa);
    }

    @Test
    public void testNewKeyPairByString() throws InvalidKeySpecException {
        RSA rsa = RSA.newKeyPair();
        String privateKey = rsa.getPrivateKey();
        String publicKey = rsa.getPublicKey();
        System.out.println("privateKey: "+privateKey);
        System.out.println("publicKey: " + publicKey);
        RSA newRsa = RSA.newKeyPair(publicKey, privateKey);
        Assert.assertEquals(newRsa.getPrivateKey(), privateKey);
        Assert.assertEquals(newRsa.getPublicKey(), publicKey);
    }

    @Test
    public void testPublicEncrypt() throws IllegalBlockSizeException, BadPaddingException {
        RSA rsa = RSA.newKeyPair();
        String plainText = "你好,世界! 你好中国!";
        String cipherText = rsa.encryptUsePubKey(plainText);

        String result = rsa.decryptUsePrivateKey(cipherText);
        Assert.assertEquals(result, plainText);
    }

    @Test
    public void testPrivateEncrypt() throws IllegalBlockSizeException, BadPaddingException {
        RSA rsa = RSA.newKeyPair();
        String plainText = "你好,世界! 你好中国!";
        String cipherText = rsa.encryptUsePrivateKey(plainText);

        String result = rsa.decryptUsePubKey(cipherText);
        Assert.assertEquals(result, plainText);
    }
}
