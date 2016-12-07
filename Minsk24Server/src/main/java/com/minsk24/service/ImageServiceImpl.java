package com.minsk24.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public void saveImage(MultipartFile imageSourceFile, String destinationDirectory, String newFilename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = imageSourceFile.getInputStream();

            File newFile = new File("\\" + destinationDirectory + "\\" + newFilename);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
