package com.minsk24.service.impl;

import com.minsk24.bean.Account;
import com.minsk24.bean.Tag;
import com.minsk24.repository.TagRepository;
import com.minsk24.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Iterable<Tag> getTags() {
        Iterable<Tag> tagsWithoutUTF8 =  tagRepository.findAll();
        List<Tag> tagsWithUTF8 = new ArrayList<>();
        try {
            for (Tag tag : tagsWithoutUTF8) {
                tag.setName(new String(tag.getName().getBytes("UTF-8")));
                tagsWithUTF8.add(tag);
            }
        }
        catch (UnsupportedEncodingException e) {

        }
        return tagsWithUTF8;
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findOne(id);
    }
    
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> getPopularTags() {
        List<Integer> tagId_TagCount = tagRepository.findPopularTags();
        List<Tag> tags = new ArrayList<>();
        for (Integer tagId : tagId_TagCount) {
            Tag tag = tagRepository.findOne(tagId);
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTags(List<Tag> tagList) {
        tagRepository.delete(tagList);
    }

    @Override
    public List<Tag> interestingTagsForAccount(Account account) {
        return null;
    }
}
