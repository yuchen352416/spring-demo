package pro.yuchen.demo.spring_demo.utils.img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pro.yuchen.demo.spring_demo.utils.DateUtils;
import pro.yuchen.demo.spring_demo.utils.UploadDirectoryUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class RequestImageUtils {

    @Autowired
    UploadDirectoryUtils upload;

    public List<String> saveImagesByRequest(HttpServletRequest request) throws IOException {
        List<String> annexs = new ArrayList<>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipart.getFileNames();
            int flag = 0;
            while (fileNames.hasNext()) {
                String path = upload.getImagePath();
                MultipartFile file = multipart.getFile(fileNames.next());
                String fileName = DateUtils.dateToString(new Date(), DateUtils.TIMESTAMP) + flag++  + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
                File io = new File(path + File.separator + fileName);
                if (!io.exists()) {
                    File p = io.getParentFile();
                    if (!p.exists()) {
                        p.mkdirs();
                    }
                    io.createNewFile();
                }
                file.transferTo(io);
                annexs.add("/resource/image" + File.separator + fileName);
            }
        }
        return annexs;
    }


}
