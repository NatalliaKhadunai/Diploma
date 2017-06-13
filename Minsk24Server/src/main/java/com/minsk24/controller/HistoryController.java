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
public class HistoryController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private HistoryService historyService;

    private History history;
    private List<String> images = new ArrayList<>();

    @RequestMapping(value = "/v2/history/add", method = RequestMethod.POST)
    public String addHistory(@RequestBody History history) {
        if (this.history != null) history.setId(this.history.getId());
        int i = 0;
        while (history.getContent().indexOf("INSERT_IMAGE_SRC") != -1) {
            history.setContent(history.getContent().replaceFirst("INSERT_IMAGE_SRC", images.get(i)));
            i++;
        }
        historyService.addHistoryArticle(history);

        this.history = null;
        images.clear();

        return "redirect:/home";
    }

    @RequestMapping(value = "/v2/history/images", method = RequestMethod.POST)
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
                    "/var/www/DiplomaImages/history",
                    history.getId().toString() + "_" + i);
            images.add("\\\\resources\\\\images\\\\history\\\\" +
                    history.getId().toString() + "_" + i + ".jpg");
            i++;
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }

    @RequestMapping(value = "/v2/history/{id}", method = RequestMethod.GET)
    @ResponseBody
    public History getHistoryById(@PathVariable(value = "id") Integer id) {
        return historyService.getHistoryById(id);
    }
}
