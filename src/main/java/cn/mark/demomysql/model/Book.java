package cn.mark.demomysql.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
//@TableName("t_book")
public class Book implements Serializable{
    private Integer id;

    private String name;

    private String setb;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String name, String setb, Integer age) {
        this.id = id;
        this.name = name;
        this.setb = setb;
        this.age = age;
    }

    public Book(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetb() {
        return setb;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSetb(String setb) {

        this.setb = setb;
    }
}