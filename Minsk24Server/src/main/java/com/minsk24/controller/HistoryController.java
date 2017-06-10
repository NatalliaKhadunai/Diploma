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
import java.util.Map;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private HistoryService historyService;

    private History history;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addHistory(@RequestBody String historyContent) {
        if (history == null) history = new History();
        history.setContent(historyContent);
        historyService.addHistoryArticle(history);
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    @ResponseBody
    public void saveImage(MultipartHttpServletRequest request) {
        /*if (history == null) {
            history = new History();
            history = historyService.addHistoryArticle(history);
        }
        imageService.saveImage(image,
                "Minsk24Server\\src\\main\\resources\\static\\res\\img\\history",
                history.getId().toString() + "_" +
                        Integer.toString(history.getImages().size()));
        history.addImage("\\res\\img\\history\\" +
                history.getId().toString() + "_" +
                        Integer.toString(history.getImages().size()));
        history = historyService.addHistoryArticle(history);
        return history.getImages().get(history.getImages().size() - 1);*/
        Map<String, MultipartFile> map = request.getMultiFileMap().toSingleValueMap();
        map.remove("image");
        for (Map.Entry<String, MultipartFile> entry :
                request.getMultiFileMap().toSingleValueMap().entrySet()) {
            System.out.println(entry.getValue().getOriginalFilename());
        }
    }
}
