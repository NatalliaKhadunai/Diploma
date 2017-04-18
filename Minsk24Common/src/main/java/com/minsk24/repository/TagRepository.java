package com.minsk24.repository;

import com.minsk24.bean.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    Tag findByName(String name);
}
