package com.wyyuand.utils;

public class BackContent {
    public static String callBack(String Register){
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<data>");
        xml.append("<result>"+Register+"</result>");
        xml.append("</data>");

        return  xml.toString();
    }
}
