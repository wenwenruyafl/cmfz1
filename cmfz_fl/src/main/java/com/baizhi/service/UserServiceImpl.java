package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.dto.UserPageDto;
import com.baizhi.entity.MouthAndCount;
import com.baizhi.entity.ProvinceAndCount;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.VariableElement;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //分页
    @Override
    public UserPageDto queryUserByPage(Integer page, Integer rows) {
        UserPageDto userPageDto = new UserPageDto();
        //當前頁
        userPageDto.setPage(page);
        Integer count = userDao.selectUserCount();
        //总页数
        userPageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        userPageDto.setRecords(count);
        //当前页的数据行
        userPageDto.setRows(userDao.selectUserByPage(page, rows));
        return userPageDto;
    }
    //增加
    @Transactional
    @Override
    public Map<String, Object> addUser(User user) {
        Map<String, Object> map=new HashMap<>();
        String s=UUID.randomUUID().toString();
        user.setId(s);
        user.setRegistTime(new Date());
        User user1 = userDao.selectOne(user.getPhone());
        if(user1==null){
            userDao.insertOneUser(user);
            map.put("code",200);
            map.put("message",user);
        }else {
            map.put("error",400);
            map.put("message","该账号已存在");
        }
        return map;
    }
//删除
    @Override
    public void deleteOneUser(String id) {
        userDao.deleteUserById(id);
    }
//修改
    @Override
    public String updateOneUser(User user) {
        userDao.updateOneUser(user);
        return user.getId();
    }
//修改路径
    @Override
    public void updateFilePath(User user) {
        userDao.updateFilePath(user);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.selectAllUser();
    }
//登录
    @Override
    public Map<String, Object> queryUserLogin(String id, String password) {
        User user = userDao.selectOne(id);
        Map<String, Object> map=new HashMap<>();
        if(user==null){
            map.put("error",200);
            map.put("message","用户名不存在");
        }else if(user.getPassword().equals(password)){
            map.put("code",400);
            map.put("message",user);
        }else {
            map.put("error",300);
            map.put("message","密码不正确");
        }
        return map;
    }
//查相对应月份注册人数
    @Override
    public Map<String,Object> queryCountAndMonth() {
        Map<String,Object> map=new HashMap<>();
        List<MouthAndCount> mouthAndCounts = userDao.selectMouthAndCount();
        List<String> list=new ArrayList<>();
        List<Integer> lists=new ArrayList<>();
        for (MouthAndCount mouthAndCount :mouthAndCounts) {
            if(mouthAndCount.getMonth()!=null){
                String subs = mouthAndCount.getMonth().substring(6);
                list.add(subs);
            }

        }
        for (MouthAndCount mouthAndCount : mouthAndCounts) {
            Integer count = mouthAndCount.getCount();
            lists.add(count);
        }
        map.put("month",list);
        map.put("count",lists);
        return map;
    }
//查不同性别注册的省份
    @Override
    public Map<String , Object> queryProvinceAndCount() {
        List<ProvinceAndCount> men = userDao.selectProvinceAndCount("男");

        Map<String, Object>  map=new HashMap<>();

        map.put("men",men);

        List<ProvinceAndCount> mens = userDao.selectProvinceAndCount("女");

        map.put("mens",mens);
        return map;
    }


}
