package pro.yuchen.demo.spring_demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class UploadDirectoryUtils {

    @Value("${config.upload.imagePath}")
    private String imagePath;

    public synchronized String getImagePath() {
        String path = imagePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

}
