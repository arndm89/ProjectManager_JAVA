package com.cts.tm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tm.model.User;
import com.cts.tm.repository.IUserRepository;
import com.cts.tm.service.IUserService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.UserVO;
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired CustomPropertyVO property;
	@Autowired IUserRepository iUserRepository;
	
	@Override
	public List<UserVO> getAllUsers() throws Exception {
		
		List<UserVO> list = null;
		List<User> userList = (List<User>) iUserRepository.findAll();
		
		if(userList != null && userList.size() > 0) {
			
			list = new ArrayList<>(userList.size());
			for (User i : userList) {
				UserVO vo = new UserVO(i.userId.intValue(), i.firstName, i.lastName, i.employeeId);
				list.add(vo);
			}
		}
		return list;
	}
	
	
	
	@Override
	public String createUser(UserVO userVO) throws Exception {
		String status = "failed"; 
		
		User userObj = new User();
		if(iUserRepository.findByEmployeeId(userVO.employeeId) == null) {
			userObj.firstName = userVO.firstName;
			userObj.lastName = userVO.lastName;
			userObj.employeeId = userVO.employeeId;
		}else {
			return property.alreadyExist;
		}
		status = iUserRepository.save(userObj) == null ? "failed" : "success";
		return status;
	}

	@Override
	public String updateUser(UserVO userVO) throws Exception {
		
		String status = "failed";
		User user = iUserRepository.findByEmployeeId(userVO.getEmployeeId());
		if(user != null) {
			user.firstName = userVO.getFirstName();
			user.lastName = userVO.getLastName();
			status = iUserRepository.save(user) != null ? "success":"failure";
		}else {
			status=property.notFound;
		}
		return status;
	}

	@Override
	public String deleteUser(Integer employeeId) throws Exception {
		Long rows = iUserRepository.deleteByEmployeeId(employeeId);
		return rows > 0 ? "SUCCESS" : "NOT_FOUND";
	}

	@Override
	public List<UserVO> getAllUsersForProject(String searchTxt) throws Exception {

		
		List<UserVO> list = null;
		List<User> userList = null;
		if(searchTxt.length() == 0) {
			userList = (List<User>) iUserRepository.findAll();
		}else {
			userList = iUserRepository.findByUserName(searchTxt.toUpperCase());
		}
		
		// filtering user with no project
		userList = userList.parallelStream().filter(u -> u.projectId == null || u.projectId == 0)
				.collect(Collectors.toList());
		
		if(userList != null && userList.size() > 0) {
			
			list = new ArrayList<>(userList.size());
			for (User i : userList) {
				UserVO vo = new UserVO(i.userId.intValue(), i.firstName, i.lastName, i.employeeId);
				list.add(vo);
			}
		}
		return list;
	
	}



	@Override
	public List<UserVO> getAllUsersForTask(String searchTxt) throws Exception {

		List<UserVO> list = null;
		List<User> userList = null;
		if(searchTxt.length() == 0) {
			userList = (List<User>) iUserRepository.findAll();
		}else {
			userList = iUserRepository.findByUserName(searchTxt.toUpperCase());
		}
		
		// filtering user with no project
		userList = userList.parallelStream().filter(u -> u.taskId == 0 && (u.projectId == null || u.projectId ==0))
				.collect(Collectors.toList());
		
		if(userList != null && userList.size() > 0) {
			
			list = new ArrayList<>(userList.size());
			for (User i : userList) {
				UserVO vo = new UserVO(i.userId.intValue(), i.firstName, i.lastName, i.employeeId);
				list.add(vo);
			}
		}
		return list;
	
	}
}
