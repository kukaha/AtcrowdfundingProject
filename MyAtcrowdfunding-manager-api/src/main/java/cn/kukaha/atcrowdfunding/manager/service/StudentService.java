package cn.kukaha.atcrowdfunding.manager.service;

public interface StudentService {

	/**
	 * 主页查询方法（全文检索）
	 * @throws Exception 
	 */
	public String search(String keyword) throws Exception;
}
