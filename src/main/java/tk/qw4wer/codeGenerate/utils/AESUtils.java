package tk.qw4wer.codeGenerate.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtils {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    //key： 加密密钥
    private static String key = "KnSn2DEbyN0rVnd8";
    //ivParameter：AES cbc加密模式的iv向量
    private static String ivParameter = "zxz0q1JlsfSIBOF7";

    private static Cipher cipher;
    private static SecretKeySpec secretKeySpec;
    private static IvParameterSpec iv;

    static {
        byte[] raw = new byte[0];
        try {
            raw = key.getBytes();
            secretKeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            iv = new IvParameterSpec(ivParameter.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }

    public static byte[] encryptByte(byte[] bytIn) {
        byte[] bytOut = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            bytOut = cipher.doFinal(bytIn);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return bytOut;
    }

    public static byte[] encryptStr(String str) {
        return encryptByte(str.getBytes());
    }

    public static byte[] decryptByte(byte[] bytIn) throws BadPaddingException, IllegalBlockSizeException {
        byte[] bytOut = new byte[0];
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        bytOut = cipher.doFinal(bytIn);
        return bytOut;
    }


}
