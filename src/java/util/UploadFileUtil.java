package util;

import constant.SystemConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

public class UploadFileUtil {

    public static void writeOrUpdate(String path, byte[] bytes) {
        path = SystemConstant.BASE_DIR + path;
        File file = new File(StringUtils.substringBeforeLast(path, "/"));
        if (!file.exists()) {
            file.mkdir();
        }
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
