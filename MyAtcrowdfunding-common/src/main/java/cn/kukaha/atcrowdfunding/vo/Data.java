package cn.kukaha.atcrowdfunding.vo;

import java.util.ArrayList;
import java.util.List;

import cn.kukaha.atcrowdfunding.bean.User;

public class Data {

	private List<User> userList = new ArrayList<User>();
	private List<User> datas = new ArrayList<User>();
	private List<Integer> ids;
//	private List<MemberCart> certimgs = new ArrayList<MemberCart>();
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<User> getDatas() {
		return datas;
	}
	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
