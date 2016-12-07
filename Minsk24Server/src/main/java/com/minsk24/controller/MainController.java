package com.minsk24.controller;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String postFile(@RequestParam(value="id") int id,
                           @RequestParam(value="name") String name,
                           @RequestParam(value="file") MultipartFile file)
    {
        /*InputStream inputStream = null;
        OutputStream outputStream = null;
        Iterator<String> itr=request.getFileNames();
        MultipartFile file=request.getFile(itr.next());
        String fileName = file.getOriginalFilename();
        try {
            inputStream = file.getInputStream();

            File newFile = new File("D:\\" + fileName);
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
        }*/

        return "success";
    }

}
