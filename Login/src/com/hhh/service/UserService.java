package com.hhh.service;

import com.hhh.domain.PageBean;
import com.hhh.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author tzw
 * @create 2020-06-20 12:01
 */
public interface UserService {
    //查询
    public List<User> findAll();
    //登录
    public User login(User user);
    //添加
    public void add(User user);
    //删除
    public void delete(String id);
    //根据id查询
    public User findUserById(String id);
    //修改
    public void updateUser(User user);
    //删除选中
    public void delSelectedUser(String[] ids);
    //分页查询
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
