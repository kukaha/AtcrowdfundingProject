package cn.kukaha.atcrowdfunding.util;

public class AjaxResult {

	private boolean success;
	private String message;
	private Page page;
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	
}
