package com.bosonit.formacion.block6pathvariableheaders;

public class User {
    Integer id;
    String content;
    public User(Integer id, String name){
        this.id = id;
        this.content = "Hello, " + name + "!";
    }

    @Override
    public String toString() {
        return "User{" +
                "id:" + id +
                ", content:'" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
