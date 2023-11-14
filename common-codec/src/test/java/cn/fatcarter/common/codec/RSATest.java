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
     public void test1() throws InvalidKeySpecException {
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsSVyH5R52GMs/WqYl/cwK0r47Z4FaKqvXBZ9vzHHYbsGo72naTffYJVHj2i5EMngAtBftimhOw1bRiirz7WmyK56OU7qBtiBfp+U+TLFN/mGxnAgG2H3m7u4cuoHjtNF7DBMslnh8bUZIoDGlqqHQ5mQY5/g5xd9+7crVjNzb5XC+XI06S5/4jlu6KSR/D5pa+37KvaJ4L2QyAEcwtRp/i/kM9DQ7+gaSD2SyToxU98hLtPv39KvYdkMHK2vJNmS1inO7V2vzbhrQ7Fq8FnrYZT5bSA6Iqrlu2OEzq+j0IsieeamQAHtcNSTnT3XcfHPYl9qsE6jIz3lp2+M00f3MQIDAQAB";
         RSA rsa = RSA.newKeyPair(key, null);

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
