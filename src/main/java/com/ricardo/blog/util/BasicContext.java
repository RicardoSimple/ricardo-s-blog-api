package com.ricardo.blog.util;

public class BasicContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
    public static void removeCurrentId(){
        threadLocal.remove();
    }
}
