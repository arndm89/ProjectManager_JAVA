package com.cts.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tm.service.IUserService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.GenericResponseVO;
import com.cts.tm.vo.UserVO;
import com.cts.tm.vo.ValidateVO;

@CrossOrigin("*")
@RestController
public class AddUserController {
	@Autowired private CustomPropertyVO property;
	
	@Autowired private IUserService iUserService;
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET)
	public GenericResponseVO getAllUsers() {
		GenericResponseVO vo = new GenericResponseVO();
		List<UserVO> userList = null;
		try {
			userList = iUserService.getAllUsers();
			if(userList != null && userList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setUserList(userList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
	

	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public GenericResponseVO createUser(@RequestBody UserVO userVO) {
		String s = "";
		GenericResponseVO responseVo = new GenericResponseVO();
		try {
			ValidateVO validateVO = userVO.isNull();
			if(validateVO.isValid()) {
				s = iUserService.createUser(userVO);
				if(s.equalsIgnoreCase(property.success)) {
					responseVo.setStatus(property.success);
					responseVo.setMsg(property.userCreationSuccess);
				}else if(s.equalsIgnoreCase(property.alreadyExist)){
					responseVo.setStatus(property.failure);
					responseVo.setMsg(property.userAlreadyExist);
				}else {
					responseVo.setStatus(property.failure);
					responseVo.setMsg(property.userCreationFailed);
				}
			}else {
				responseVo.setStatus(property.failure);
				responseVo.setMsg(property.userCreationFailed);
				responseVo.setErrorMsg(validateVO.getMsg());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseVo.setStatus(property.error);
			responseVo.setMsg(property.userCreationFailed);
			responseVo.setErrorMsg(e.getLocalizedMessage());
		}
		return responseVo;
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public GenericResponseVO updateUser(@RequestBody UserVO userVO) {
		GenericResponseVO vo = new GenericResponseVO();
		try {
			String status = iUserService.updateUser(userVO);
			if(status.equalsIgnoreCase(property.success)) {
				vo.setStatus(property.success);
				vo.setMsg(property.updatedSuccessfully);
			}else if(status.equalsIgnoreCase(property.notFound)){
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}else {
				vo.setStatus(property.failure);
				vo.setErrorMsg(property.failedOperationMsg);
			}
			
		} catch (Exception e) {
			vo.setStatus(property.error);
			vo.setErrorMsg(property.failedOperationMsg);
			e.printStackTrace();
		}
		return vo;
	}
	
	@RequestMapping(value="/deleteUser/{employeeId}", method=RequestMethod.DELETE)
	public GenericResponseVO deleteUser(@PathVariable("employeeId") Integer employeeId) {
		GenericResponseVO resVo = new GenericResponseVO();
		try {
			
			String status = iUserService.deleteUser(employeeId);
			if(status.equalsIgnoreCase(property.success)) {
				resVo.setStatus(property.success);
				resVo.setMsg(property.deletedSuccessfully);
			}else {
				resVo.setStatus(property.notFound);
				resVo.setMsg(property.noResultFound);
			}
		}catch(Exception e) {
			resVo.setStatus(property.failure);
			resVo.setMsg(property.failedOperationMsg);
			e.printStackTrace();
		}
		return resVo;
	}



	@RequestMapping(value="/getAllUsersForProject", method=RequestMethod.GET)
	public GenericResponseVO getAllUsersForProject(@RequestParam("searchTxt") String searchTxt) {
		GenericResponseVO vo = new GenericResponseVO();
		List<UserVO> userList = null;
		try {
			userList = iUserService.getAllUsersForProject(searchTxt);
			if(userList != null && userList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setUserList(userList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
	@RequestMapping(value="/getAllUsersForTask", method=RequestMethod.GET)
	public GenericResponseVO getAllUsersForTask(@RequestParam("searchKey") String searchKey) {
		GenericResponseVO vo = new GenericResponseVO();
		List<UserVO> userList = null;
		try {
			userList = iUserService.getAllUsersForTask(searchKey);
			if(userList != null && userList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setUserList(userList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
}