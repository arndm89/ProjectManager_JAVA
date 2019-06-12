package com.cts.tm.service;

import java.util.List;

import com.cts.tm.vo.UserVO;

public interface IUserService {
	
	public String createUser(UserVO userVO)throws Exception;

	public List<UserVO> getAllUsers()throws Exception;
	
	public List<UserVO> getAllUsersForProject(String searchTxt)throws Exception;

	public String updateUser(UserVO userVO) throws Exception;

	public String deleteUser(Integer employeeId)throws Exception;

	public List<UserVO> getAllUsersForTask(String searchTxt) throws Exception;

}
