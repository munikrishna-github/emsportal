package com.ems.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextLoader {
    private static ClassPathXmlApplicationContext context = null;

    public static ClassPathXmlApplicationContext getContext() {
        if (context == null) {
            System.out.println("Loading Application Context");
            context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" }, true);
        }
        return context;
    }
    
    public static Object getBean(String id){
        if(id == null || id.trim().equals("")){
            return null;
        }
         
        if(context == null){
            getContext();
        }
        return context.getBean(id);
    }
}