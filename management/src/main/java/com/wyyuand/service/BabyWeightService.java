package com.wyyuand.service;


import com.wyyuand.domain.BabyWeight;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface BabyWeightService {

    public List<BabyWeight> queryCulWeight(String userId);
    public boolean insertBabyWeight(String userId,String ACStr,String BDPStr,String FLStr);

    public PageBean<BabyWeight> queryAllByPage(int pageNum, int pageSize);
    public boolean deleteBabyWeight(String weightId);

    public boolean deleteAll(String[] myIds);

    public PageBean<BabyWeight> queryManyByPage(String keyword,int pageNum, int pageSize);


}
