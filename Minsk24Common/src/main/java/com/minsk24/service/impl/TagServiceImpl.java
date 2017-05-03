package com.minsk24.service.impl;

import com.minsk24.bean.Tag;
import com.minsk24.repository.TagRepository;
import com.minsk24.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Iterable<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTags(List<Tag> tagList) {
        tagRepository.delete(tagList);
    }
}
