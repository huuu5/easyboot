package com.hu4java.util;


import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RSAUtils {

    /**
     * encryption algorithm RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA Maximum Encrypted Plaintext Size
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA Maximum decrypted ciphertext size
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * encryption
     * @param data data
     * @param publicKey publicKey
     * @return byte
     * @throws Exception Exception
     */
    public static byte[] encrypt(byte[] data, String publicKey)
            throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // Sectional Encryption of Data
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static String encrypt(String text, String publicKey) throws Exception{
        byte[] bytes = encrypt(text.getBytes(StandardCharsets.UTF_8), publicKey);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decrypt
     * @param text text
     * @param privateKey privateKey
     * @return byte
     * @throws Exception Exception
     */
    public static byte[] decrypt(byte[] text, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = text.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // Sectional Encryption of Data
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(text, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(text, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * Decrypt
     * @param text          密文
     * @param privateKey    私钥
     * @return              明文
     * @throws Exception    异常
     */
    public static String decrypt(String text, String privateKey) throws Exception {
        byte[] bytes = decrypt(text.getBytes(StandardCharsets.UTF_8), privateKey);
        return IOUtils.toString(new ByteArrayInputStream(bytes), StandardCharsets.UTF_8.name());
    }
}
