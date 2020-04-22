package com.wyyuand;

import com.wyyuand.domain.CircleComment;
import org.junit.Test;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentTest {


//    @ResponseBody
    @Test
    public void testComment(){

        Map map = new HashMap();
        map.put("code","1000");
        map.put("message","查看评论成功");

        Map  data = new HashMap();
        data.put("total",30);
        List list = new ArrayList();
        list.add(new CircleComment());
        list.add(new CircleComment());
        list.add(new CircleComment());
        list.add(new CircleComment());
        data.put("list",list);
        map.put("data",data);



//        JSON





    }
}
