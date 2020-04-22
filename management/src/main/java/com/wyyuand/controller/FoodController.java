package com.wyyuand.controller;

import com.wyyuand.domain.Food;
import com.wyyuand.service.FoodService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//食物
//http://127.0.0.1:8080/manage/food/list
//http://127.0.0.1:8080/manage/food/listByPage
@Controller
@RequestMapping("/food")
public class FoodController {

    public static final String UPLOAD_DIR = "d:\\upload\\food\\";   //想放在哪个目录下

    @Autowired
    FoodService foodService;

    private static Map<String,String> imageTypePathmap = new HashMap();
    private static Map<String,String> foodNamemap = new HashMap();
    static{
        imageTypePathmap.put("1","grains\\");
        imageTypePathmap.put("2","drink\\");
        imageTypePathmap.put("3","fruit\\");
        imageTypePathmap.put("4","meat\\");
        imageTypePathmap.put("5","milk\\");
        imageTypePathmap.put("6","seafood\\");
        imageTypePathmap.put("7","snack\\");
        imageTypePathmap.put("8","vegetable\\");

        foodNamemap.put("1","五谷杂粮");
        foodNamemap.put("2","饮品饮料");
        foodNamemap.put("3","水果");
        foodNamemap.put("4","肉禽类");
        foodNamemap.put("5","奶制品");
        foodNamemap.put("6","水产海鲜");
        foodNamemap.put("7","零食小吃");
        foodNamemap.put("8","蔬菜菌类");
    }

    String imagesPath = null;
    // http://127.0.0.1:8080/manage/food/listByPage?type=1
    //分页查询   //找到每页显示的数据源  以及页数

//    admin................
    @RequestMapping("/listByPage")
    public String listByPage(String pageNumStr, String pageSizeStr, String type,Model model){
        //得到前台传来的数据
        imagesPath =  imageTypePathmap.get(type);
        String foodName =  foodNamemap.get(type);

        int pageNum = 1;          //默认页
        int pageSize = 10;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //调用service层的方法
        PageBean<Food> listPage = foodService.queryAllByPage(pageNum, pageSize,type);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Food> foodslList = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("foodslList",foodslList);
        model.addAttribute("type",type);
        model.addAttribute("foodName",foodName);
        return  "/js_eat/eat_list.jsp";              //内部跳转（带数据）
    }
/*
    删除。。。
*/

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception
    {
        boolean success = foodService.delete(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

    /*删除全部*/
    @ResponseBody
    @RequestMapping("/deleteAll")
    public  Map ids(HttpServletRequest request, HttpServletResponse response, String ids){
        String[] myIds = ids.split(",");
        boolean success  = foodService.deleteAll(myIds);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

/*
    //增加五谷杂粮()
*/
    @RequestMapping("/save")
    public String save(Food food, MultipartFile pics){

        String org = pics.getOriginalFilename();           //获取上传的文件名字
        //此时需要处理  能不能吃的问题

            if(food.getBabyJ() != null){
                String babyJ = food.getBabyJ().split("-")[0];
                food.setBaby(babyJ);
            }
            if( food.getBfpJ() != null){

                String  bfpJ= food.getBfpJ().split("-")[0];
                food.setBfpJ(bfpJ);
            }

            if( food.getPwJ() != null){

                String pwJ = food.getPwJ().split("-")[0];
                food.setPwJ(pwJ);
            }

            if(food.getPwUsuallyEat() != null){
                String pwUsuallyEat = food.getPwUsuallyEat().split("-")[0];
                food.setPwUsuallyEat(pwUsuallyEat);
            }

            if(food.getType() != null){
                String type = food.getType().split("-")[0];
                food.setType(type);
            }

        boolean success = foodService.save(food,org);
        System.out.println("ContentController.insert: " + success);
        String type = food.getType();
        System.out.println("type----------------------------------------"+type);
        return "redirect:/food/listByPage?type="+type;

    }

/*
    //获取要修改的内容(√)
*/
    @RequestMapping("/toEdit")
    public  String toEdit(Model model,String id) throws  Exception
    {
        Food food = foodService.queryById(id);       //查找到要修改的内容
        imagesPath = food.getPicture();
//        System.out.println("========================"+imagesPath);
        model.addAttribute("food",food);
        return "/js_eat/eat_edit.jsp";                 //跳转到修改页
    }
/*
       修改(√)
*/
    @RequestMapping("/saveEdit")
    public  String saveEdit(Food food, MultipartFile pics) throws  Exception
    {
        System.out.println( "----------------- "+ pics.getOriginalFilename());
        String org = null;
        System.out.println(pics);

        if(!pics.getOriginalFilename().equals("")){
            org = pics.getOriginalFilename();           //获取上传的文件名字
        }else{
            org = imagesPath;
        }
        //此时需要处理  能不能吃的问题
        if(food.getBabyJ().contains("吃")){
            String babyJ = food.getBabyJ().split("-")[0];
            food.setBaby(babyJ);
        }
        if(food.getBfpJ().contains("吃")){
            String  bfpJ= food.getBfpJ().split("-")[0];
            food.setBfpJ(bfpJ);
        }
        if(food.getPwJ().contains("吃")){
            String pwJ = food.getPwJ().split("-")[0];
            food.setPwJ(pwJ);
        }

        String type = food.getType();
        System.out.println("------------------------------>"+type);
        foodService.update(food,org);

        return "redirect:/food/listByPage?type="+type;        //重定向
    }


    @RequestMapping("/queryByKeyWord")
    public String queryByKeyWord(Model model , String type, String keyword, HttpServletRequest request, String pageNumStr, String pageSizeStr) throws  Exception{

        //得到前台传来的数据
        imagesPath =  imageTypePathmap.get(type);
        String foodName =  foodNamemap.get(type);

        int pageNum = 1;          //默认页
        int pageSize = 10;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //调用service层的方法
        PageBean<Food> listPage = foodService.queryManyByPage(keyword,pageNum, pageSize,type);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Food> foodslList = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("foodslList",foodslList);
        model.addAttribute("type",type);
        model.addAttribute("foodName",foodName);
        return  "/js_eat/eat_list.jsp";              //内部跳转（带数据）
    }


    @RequestMapping("/download")
    public void download(String id,HttpServletResponse response) throws IOException {

        Food food = foodService.queryById(id);
        String imagesPath =  imageTypePathmap.get(food.getType());
        String fileName = food.getPicture();


        FileInputStream is = null;
        //得到输出流
        System.out.println("fileName: "+ fileName);
        OutputStream output = response.getOutputStream();
        //读取文件流
        is = new FileInputStream(new File(UPLOAD_DIR+imagesPath + fileName));
        //输入缓冲流
        BufferedInputStream bis = new BufferedInputStream(is);
        //输出缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(output);
        // 缓冲字节数
        byte data[] = new byte[1024];
        int size = 0;
        size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }
        bis.close();
        bos.flush();
        bos.close();
        output.close();
    }



    //    展示个前端的信息查询user................
    @ResponseBody
    @RequestMapping("/list")
    public List queryAll(String type){
        List<Food> foodList = foodService.queryAll(type);
        /*for (Food food : foodList) {
            System.out.println(food.getBfpJ());
            System.out.println(food.getBabyJ());
            System.out.println(food.getPwJ());
        }*/
        return foodList;
    }
    //    展示个前端的信息查询user................

    //    // http://127.0.0.1:8080/manage/food/listWeekday?weekday=1
    @ResponseBody
    @RequestMapping("/listWeekday")
    public List listWeekday(String weekday){

        List<Food> foodList = foodService.queryAllByWeekday(weekday);
        for (Food food : foodList) {
            System.out.println(food.getBfpJ());
            System.out.println(food.getBabyJ());
            System.out.println(food.getPwJ());
        }
        return foodList;
    }

}
