package com.minsk24.controller;

import com.minsk24.bean.Advertisement;
import com.minsk24.bean.Article;
import com.minsk24.service.AdvertisementService;
import com.minsk24.service.ArticleService;
import com.minsk24.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getSearchResult(@RequestParam String scope,
                                                  @RequestParam String keyword,
                                                  @RequestParam Integer page) {
        if (scope.equals("article"))
            return new ResponseEntity<>(articleService.searchByKeyword(keyword, page), HttpStatus.OK);
        else if (scope.equals("advertisement"))
            return new ResponseEntity<>(advertisementService.searchByKeyword(keyword, page), HttpStatus.OK);
        else if (scope.equals("event"))
            return new ResponseEntity<>(eventService.searchByKeyword(keyword, page), HttpStatus.OK);
        else return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/search/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getSearchResult(@RequestParam String scope,
                                             @RequestParam String keyword) {
        if (scope.equals("article"))
            return articleService.getNumberOfArticlesSearchByKeyword(keyword);
        else if (scope.equals("advertisement"))
            return advertisementService.getNumberOfAdvertisementsSearchByKeyword(keyword);
        else if (scope.equals("event"))
            return eventService.getNumberOfEventsSearchByKeyword(keyword);
        else return 0;
    }
}
