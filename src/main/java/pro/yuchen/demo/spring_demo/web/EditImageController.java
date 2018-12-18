package pro.yuchen.demo.spring_demo.web;


import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.yuchen.demo.spring_demo.utils.UploadDirectoryUtils;
import pro.yuchen.demo.spring_demo.utils.img.RequestImageUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class EditImageController {

    @Autowired
    UploadDirectoryUtils upload;

    @Autowired
    ResourceLoader resource;


    @RequestMapping(value = "/edit/upload/image", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        String src = RequestImageUtils.saveImagesByRequest(request).get(0);
        data.put("src", src);
        map.put("code", 0);
        map.put("msg", "添加成功");
        map.put("data", data);
        return map;
    }

    @RequestMapping(value = "/resource/image/{filename}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> resource(HttpServletRequest request, @PathVariable String filename) {
        try {
            return ResponseEntity.ok(resource.getResource("file:"
                    + Paths.get(upload.getImagePath() + File.separator + filename)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
