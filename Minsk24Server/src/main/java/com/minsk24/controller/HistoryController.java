package com.minsk24.controller;

import com.minsk24.bean.History;
import com.minsk24.service.HistoryService;
import com.minsk24.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private HistoryService historyService;

    private History history;
    private List<String> images = new ArrayList<>();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHistory(@RequestBody String historyContent) {
        if (history == null) history = new History();
        int i = 0;
        while (historyContent.indexOf("INSERT_IMAGE_SRC") != -1) {
            historyContent = historyContent.replaceFirst("INSERT_IMAGE_SRC", images.get(i));
            i++;
        }
        history.setContent(historyContent);
        historyService.addHistoryArticle(history);

        history = null;
        images.clear();

        return "redirect:/home";
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    @ResponseBody
    public void saveImages(MultipartHttpServletRequest request) {
        if (history == null) {
            history = new History();
            history = historyService.addHistoryArticle(history);
        }
        Map<String, MultipartFile> map = request.getMultiFileMap().toSingleValueMap();
        map.remove("image");
        int i = 0;
        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            imageService.saveImage(entry.getValue(),
                    "Minsk24Server\\src\\main\\resources\\static\\res\\img\\history",
                    history.getId().toString() + "_" + i);
            images.add("\\\\res\\\\img\\\\history\\\\" +
                    history.getId().toString() + "_" + i + ".jpg");
            i++;
        }
    }
}
