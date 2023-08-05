package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;
    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assets/" + folder));
        if(!dir.exists()) {
            dir.mkdirs();
        }
//        String s = System.currentTimeMillis() + file.getOriginalFilename();
//        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        String name = file.getOriginalFilename();
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
