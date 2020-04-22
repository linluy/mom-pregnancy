package com.wyyuand.service;


import com.wyyuand.dao.BabyWeightMapper;
import com.wyyuand.dao.BabyWeightMapperExt;
import com.wyyuand.domain.BabyWeight;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BabyWeightServiceImpl implements  BabyWeightService{

    @Autowired
    BabyWeightMapperExt babyWeightMapperExt;
    @Autowired
    BabyWeightMapper babyWeightMapper;

    @Override
    public List<BabyWeight> queryCulWeight(String userId) {
        return babyWeightMapperExt.queryCulWeight(userId);
    }

    @Override
    public boolean insertBabyWeight(String userId,String ACStr,String BDPStr,String FLStr) {
        BabyWeight babyWeight = new BabyWeight();
        BigDecimal weightValueCom = new BigDecimal(0.0);


        BigDecimal AC = new BigDecimal(ACStr);
        BigDecimal BDP = new BigDecimal(BDPStr);
        BigDecimal FL = new BigDecimal(FLStr);
//        1.07*BDP*BDP*BDP+0.3*AC*AC*FL;
        BigDecimal ad1 = new BigDecimal(1.07).multiply(BDP.pow(3));
        BigDecimal ad2 = new BigDecimal(0.3).multiply(AC.pow(2).multiply(FL));
        BigDecimal weightValue = ad1.add(ad2);               //------------->实际计算出来的值

        babyWeight.setWeightData(new Date());
        babyWeight.setWeightId(StringUtil.createRandomNum(8));
        babyWeight.setUserId(userId);
        babyWeight.setAc(AC);
        babyWeight.setBdp(BDP);
        babyWeight.setFl(FL);
        babyWeight.setWeightValue(weightValue);

//        babyWeight.setUserId("32442dfsd");            //userId的值
        int count = babyWeightMapper.insert(babyWeight);

        //在此处查询数据库，和最新增加的数据进行比较，如果估计的体重小于最新一条，则返回false---->代表我此时的体重估计数据有问题
        BabyWeight babyWeightCom = babyWeightMapperExt.queryBabyWeight(userId);

        if (babyWeightCom != null) {
            weightValueCom = babyWeightCom.getWeightValue();            //--------------从数据库重查询出的最新的一条消息
            System.out.println("------------------------" + weightValueCom);
            System.out.println("------------------------" + weightValueCom);
            System.out.println("------------------------" + weightValueCom);
            System.out.println("------------------------" + weightValueCom);

            if (weightValue.compareTo(weightValueCom) == -1) {
                System.out.println("weightValue------->" + weightValue);
                System.out.println("weightValueCom------->" + weightValueCom);
                System.out.println("count1------>" + count);
                count = 0;
            } else{
                count = 1;
            }


//-1 小于
//0 等于
//1 大于

//        if(weightValue.compareTo(weightValueCom) == -1  ){
//            System.out.println("weightValue------->"+weightValue);
//            System.out.println("weightValueCom------->"+weightValueCom);
//            System.out.println("count1------>"+count);
//            return  false;
//
//        }else{
//            System.out.println("weightValue------->"+weightValue);
//            System.out.println("weightValueCom------->"+weightValueCom);
//            System.out.println("count2------>"+count);
//            return count>0;
//        }
        }else {
            System.out.println("weightValue------->" + weightValue);
            System.out.println("weightValueCom------->" + weightValueCom);
            System.out.println("count2------>" + count);
            count = 1;
        }
        return count > 0;
    }

//    删除体重


    @Override
    public boolean deleteBabyWeight(String weightId) {

        int count = babyWeightMapper.deleteByPrimaryKey(weightId);
        return count>0;
    }

    @Override
    public boolean deleteAll(String[] myIds) {
        int count = 0;
        try {
            count = babyWeightMapperExt.deleteAll(myIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count>0;
    }


    /*
     *
     *
     * */
    @Override
    public PageBean<BabyWeight> queryAllByPage(int pageNum, int pageSize) {

        //        <Knowledge> knowledgeList = knowledgeMapper.selectByExample(null);

        PageBean<BabyWeight> pageBean = new PageBean<BabyWeight>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = babyWeightMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<BabyWeight> babyWeightList = babyWeightMapperExt.findByPage(start,size);

        for (BabyWeight list : babyWeightList) {
            System.out.println("type---------->"+list.getWeightValue());
        }

        System.out.println(babyWeightList.size());
        pageBean.setLists(babyWeightList);

        return pageBean;
    }


    @Override
    public PageBean<BabyWeight> queryManyByPage(String keyword, int pageNum, int pageSize) {
        PageBean<BabyWeight> pageBean = new PageBean<BabyWeight>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = babyWeightMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<BabyWeight> lists = babyWeightMapperExt.queryManyByPage(keyword,start,size);

        for (BabyWeight list : lists) {
            System.out.println("type---------->"+list.getUserId());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

}
