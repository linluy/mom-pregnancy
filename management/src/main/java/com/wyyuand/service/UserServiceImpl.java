package com.wyyuand.service;

import com.wyyuand.dao.UserMapper;
import com.wyyuand.dao.UserMapperExt;
import com.wyyuand.domain.User;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 具体逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserMapperExt userMapperExt;

    @Override
    public User queryById(String id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User queryByEmail(User user) throws Exception {
//        System.out.println("count1:"+count);
        User selectUser = userMapperExt.queryByEmail(user);
        return selectUser ;
    }

    @Override
    public List<User> queryAll() throws Exception {
        return userMapper.selectByExample(null);
    }

    @Override
    public boolean save(User user) throws Exception {
        int count = 0;
        user.setUserId("id"+StringUtil.createRandomNum(5));           //设置user的id格式为id+5个数字和字母的组合
        user.setUserCreattime(new Date());
        user.setUserStatus("0");
        count = userMapper.insert(user);
        return count > 0;
    }

    @Override
    public boolean delete(String id) throws Exception {
//
//        User user = userMapper.selectByPrimaryKey(id);
//        user.setUserStatus("1");
        int count = userMapper.deleteByPrimaryKey(id);
//        int count = userMapper.updateByPrimaryKey(user);
        return count>0;
    }

    @Override
    public boolean update(User user) throws Exception {

//        user.setUserId(StringUtil.createRandomNum(8));
        user.setUserCreattime(new Date());
        user.setUserStatus("0");
        //用户的密码
        int count = userMapper.updateByPrimaryKey(user);
        System.out.println("count------>"+count);
        return count>0;
    }


//    根基于邮箱更新密码
    @Override
    public boolean updatePassword(User user) throws Exception {
        int count = userMapperExt.updateByEmail(user);
        return count >0;
    }


    @Override
    public PageBean<User> queryAllByPage(int pageNum, int pageSize) {

        PageBean<User> pageBean = new PageBean<User>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = userMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<User> lists = userMapperExt.findByPage(start,size);

        for (User list : lists) {
            System.out.println("type---------->"+list.getUserName());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public PageBean<User> queryManyByPage(String keyword, int pageNum, int pageSize) {


        PageBean<User> pageBean = new PageBean<User>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = userMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<User> lists = userMapperExt.queryManyByPage(keyword,start,size);

        for (User list : lists) {
            System.out.println("type---------->"+list.getUserName());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }
}
