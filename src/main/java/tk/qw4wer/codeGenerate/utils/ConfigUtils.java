package tk.qw4wer.codeGenerate.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import tk.qw4wer.codeGenerate.pojo.Events;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.File;
import java.io.IOException;

public class ConfigUtils {

    public final static String CONFIG_SUFFIX = ".qcr";

    public static Events readConfig(String path) throws Exception {
        File file = new File(path);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            byte[] bs = AESUtils.decryptByte(bytes);
            Events events = JSONObject.parseObject(bs, Events.class);
            return events;
        } catch (IOException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }

    public static void saveConfig(String path, Events events) {
        String str = JSONObject.toJSONString(events);
        byte[] bytes = AESUtils.encryptStr(str);
        try {
            FileUtils.writeByteArrayToFile(new File(path + File.separatorChar + events.getUrl() + File.separatorChar + CONFIG_SUFFIX), bytes, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
