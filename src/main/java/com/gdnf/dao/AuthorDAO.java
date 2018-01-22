package com.gdnf.dao;

import com.gdnf.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 2018/1/19.
 */
public interface AuthorDAO extends JpaRepository<Author,Long> {

}
