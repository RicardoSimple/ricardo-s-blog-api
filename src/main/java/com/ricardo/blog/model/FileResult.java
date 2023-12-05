package com.ricardo.blog.model;

import lombok.Data;

@Data
public class FileResult<DATA> {
    private int errno;

    private String message;

    private DATA data;

    public FileResult(int errno, String message, DATA data) {
        this.errno = errno;
        this.message = message;
        this.data = data;
    }

    public static <T> FileResult<T> success(T data){
        return new FileResult<T>(0,null,data);
    }

    public static FileResult<Object> fail(String msg){
        return new FileResult<>(1,msg,null);
    }
}
