package com.example.backend;

public class Task {
    public Integer id;
    public String name;
    public String content;


    public Task(Integer id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }


    Integer getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    String getContent(){
        return this.content;
    }
    
}
