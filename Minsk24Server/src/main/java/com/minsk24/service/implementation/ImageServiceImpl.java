package com.minsk24.service.implementation;

import com.minsk24.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String saveImage(MultipartFile imageSourceFile, String destinationDirectory, String newFilename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = imageSourceFile.getInputStream();
            int extensionStartIndex = imageSourceFile.getOriginalFilename().lastIndexOf('.');
            String extension = imageSourceFile.getOriginalFilename().substring(extensionStartIndex);
            File newFile = new File(destinationDirectory + "\\" + newFilename + extension);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            inputStream.close();
            outputStream.close();
            return newFilename + extension;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
