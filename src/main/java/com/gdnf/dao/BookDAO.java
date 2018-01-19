package com.gdnf.dao;

import com.gdnf.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by admin on 2018/1/15.
 */
public interface BookDAO extends CrudRepository<Book,Long> {
    List<Book> getBooksByName(String name);
    Book getBooksById(Long id);

}
