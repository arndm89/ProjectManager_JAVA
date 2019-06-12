package com.cts.tm.vo;

public class ValidateVO {
	
	public boolean isValid;
	public String msg;
	
	ValidateVO(){}
	
	public ValidateVO(boolean isValid, String msg) {
		this.isValid = isValid;
		this.msg = msg;
	}

	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
