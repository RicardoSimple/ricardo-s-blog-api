package com.ricardo.blog.model.exception;

public class ParamException extends Exception{
    public ParamException(){
        super("wrong empty arguments");
    }
}
