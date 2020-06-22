package com.hhh.dao;

import com.hhh.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author tzw
 * @create 2020-06-20 12:03
 */
public interface UserDao {
    //查询
    public List<User> findAll();
    //登录
    public User findUserByNameAndPassword(String username,String password);
    //添加
    public void add(User user);
    //删除
    public void delete(int id);
    //根据id查询
    public User findById(int id);
    //修改
    public void update(User user);
    //查询总记录数
    public int findTotalCount(Map<String, String[]> condition);
    //分页查询每页的记录
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
