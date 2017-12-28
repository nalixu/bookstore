package cn.edu.lsu.dao;

import cn.edu.lsu.bean.User;

public interface UserDAO {
  public User login(String username,String password);

public void register(User user);
}
