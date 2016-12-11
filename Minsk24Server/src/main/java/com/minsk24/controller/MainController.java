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

}
