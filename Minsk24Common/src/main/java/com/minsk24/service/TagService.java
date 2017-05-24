package com.minsk24.service;

import com.minsk24.bean.Account;
import com.minsk24.bean.Tag;

import java.util.List;

public interface TagService {
    Iterable<Tag> getTags();
    Tag getTagById(Integer id);
    Tag getTagByName(String name);
    List<Tag> getPopularTags();
    Tag addTag(Tag tag);
    void deleteTags(List<Tag> tagList);
    List<Tag> interestingTagsForAccount(Account account);
}
