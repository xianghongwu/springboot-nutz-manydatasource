package com.xhw.nutzmanydatasource.controller;


import com.xhw.nutzmanydatasource.model.mapped.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    Dao dao;

    @Resource(name = "secondaryDao")
    Dao secondaryDao;

    @GetMapping("/oneUser")
    public List oneUser() {
        List<User> users = dao.query(User.class, Cnd.NEW());
        return users;
    }
    @GetMapping("/twoUser")
    public List twoUser() {
        List<User> users = secondaryDao.query(User.class, Cnd.NEW());
        return users;
    }


}
