package com.hhh.service;

import com.hhh.dao.UserDao;
import com.hhh.dao.UserDaoImpl;
import com.hhh.domain.PageBean;
import com.hhh.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author tzw
 * @create 2020-06-20 12:02
 */
public class UserServiceImpl implements UserService{

    private static UserDao dao = new UserDaoImpl();
    private UserServiceImpl(){};
    public static UserDaoImpl getDao(){
        return (UserDaoImpl) dao;
    }

    @Override
    public List<User> findAll() {
        return getDao().findAll();
    }

    @Override
    public User login(User user) {
        return getDao().findUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void add(User user) {
        getDao().add(user);
    }

    @Override
    public void delete(String id) {
        getDao().delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return getDao().findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        getDao().update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids != null && ids.length > 0) {
            for (String id : ids) {
                getDao().delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }
        if(currentPage >= ((getDao().findTotalCount(condition) % rows)  == 0 ? getDao().findTotalCount(condition)/rows : (getDao().findTotalCount(condition)/rows) + 1)){
            currentPage = ((getDao().findTotalCount(condition) % rows)  == 0 ? getDao().findTotalCount(condition)/rows : (getDao().findTotalCount(condition)/rows) + 1);
        }
        //1创建空的pagebean
        PageBean<User> pb = new PageBean<User>();
        //2设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3调用dao查询总记录数
        int totalCount = getDao().findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4调用dao查询List集合
        int start = (currentPage - 1) * rows;
        List<User> list = getDao().findByPage(start,rows,condition);
        pb.setList(list);
        //5计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }


}

