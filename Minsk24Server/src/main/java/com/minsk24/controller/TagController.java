package com.minsk24.controller;

import com.minsk24.bean.Tag;
import com.minsk24.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Iterable<Tag> getTags() {
        return tagService.getTags();
    }

    @RequestMapping(value = "/tags/popular", method = RequestMethod.GET)
    public Iterable<Tag> getPopularTags() {
        return tagService.getPopularTags();
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Tag addTag(@RequestBody String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagService.addTag(tag);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.DELETE)
    public void deleteTags(@RequestBody List<Tag> tagList) {
        tagService.deleteTags(tagList);
    }
}
