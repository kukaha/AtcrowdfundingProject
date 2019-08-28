package cn.kukaha.atcrowdfunding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kukaha.atcrowdfunding.bean.Role;
import cn.kukaha.atcrowdfunding.bean.User;
import cn.kukaha.atcrowdfunding.exception.LoginFailException;
import cn.kukaha.atcrowdfunding.manager.dao.UserMapper;
import cn.kukaha.atcrowdfunding.manager.service.UserService;
import cn.kukaha.atcrowdfunding.util.Constant;
import cn.kukaha.atcrowdfunding.util.MD5Util;
import cn.kukaha.atcrowdfunding.util.Page;
import cn.kukaha.atcrowdfunding.vo.Data;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	public User queryUserlogin(Map<String, Object> paramMap) {
		User userName = userMapper.queryUserNameLogin(paramMap);
		if(userName==null){
			throw new LoginFailException("该用户不存在");
		}
		User user = userMapper.queryUserlogin(paramMap);
		if(user==null){
			throw new LoginFailException("用户名或密码错误");
		}
		return user;
	}

//	public Page queryPage(Integer pageno, Integer pagesize) {
//		Page page = new Page(pageno,pagesize);
//		Integer startIndex = page.getStartIndex();
//		List<User> datas= userMapper.queryList(startIndex,pagesize);
//		page.setDatas(datas);
//		int totalsize = userMapper.queryCount();
//		page.setTotalsize(totalsize);
//		return page;
//	}

	public int saveUser(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String createtime = sdf.format(date);
		user.setCreatetime(createtime);
		user.setUserpswd(MD5Util.digest(Constant.PASSWORD));
		return userMapper.insert(user);
	}


	public Page queryPage(Map<String,Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		Integer startIndex = page.getStartIndex();
		paramMap.put("startIndex", startIndex);
		List<User> datas= userMapper.queryList(paramMap);
		page.setDatas(datas);
		int totalsize = userMapper.queryCount(paramMap);
		page.setTotalsize(totalsize);
		return page;
	}

	public User getUserById(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	public int updateUser(User user) {
		return userMapper.updateByPrimaryKey(user);
	}

	public int removeUser(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public int removeBatchUser(Integer[] ids) {
		int totalCount = 0;
		for(Integer id : ids){
			int count = userMapper.deleteByPrimaryKey(id);
			totalCount += count;
		}
		if(totalCount!=ids.length){
			throw new RuntimeException("批量删除失败");
		}
		return totalCount;
	}

//	public int removeBatchUserByVO(Data data) {
//		return userMapper.deleteBatchUserByVO(data);
//	}
	public int removeBatchUserByVO(Data data) {
		return userMapper.deleteBatchUserByVO(data.getDatas());
	}

	public List<Role> queryAllRole() {
		return userMapper.queryAllRole();
	}

	public List<Integer> queryRoleByUserId(Integer id) {
		return userMapper.queryRoleByUserId(id);
	}

	public int saveUserRoleRelationship(Integer userid,Data data) {
		return userMapper.saveUserRoleRelationship(userid,data);
	}

	public int deleteUserRoleRelationship(Integer userid,Data data) {
		return userMapper.deleteUserRoleRelationship(userid,data);
	}
	
}
