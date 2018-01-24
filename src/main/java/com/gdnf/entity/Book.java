package com.gdnf.entity;

import com.gdnf.validator.BookName;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by admin on 2018/1/15.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    //@Size(min = 2 , max = 10)
    //@BookName
    @NotBlank
    private String name;

    @NotNull(message = "不能为空")
    @Min(value = 10,message = "价格不能小于10")
    private float price;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
