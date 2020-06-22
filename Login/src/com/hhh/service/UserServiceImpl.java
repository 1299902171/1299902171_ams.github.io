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

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void delete(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
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
        if(currentPage >= ((dao.findTotalCount(condition) % rows)  == 0 ? dao.findTotalCount(condition)/rows : (dao.findTotalCount(condition)/rows) + 1)){
            currentPage = ((dao.findTotalCount(condition) % rows)  == 0 ? dao.findTotalCount(condition)/rows : (dao.findTotalCount(condition)/rows) + 1);
        }
        //1创建空的pagebean
        PageBean<User> pb = new PageBean<User>();
        //2设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4调用dao查询List集合
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        //5计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }


}
