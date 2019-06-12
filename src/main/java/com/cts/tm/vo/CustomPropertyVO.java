package com.cts.tm.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound=true, value="classpath:customMsg.properties")
public class CustomPropertyVO {
	
	@Value("${success}")
	public String success;
	
	@Value("${failure}")
	public String failure;
	
	@Value("${task.creation.successfull}")
	public String taskCreationSuccessfull;
	
	@Value("${task.creation.fail}")
	public String taskCreationFail;
	
	@Value("${error}")
	public String error;
	
	@Value("${alreadyExist}")
	public String alreadyExist;
	
	@Value("${notFound}")
	public String notFound;
	
	@Value("${active}")
	public String active;
	
	@Value("${task.search.not.found}")
	public String noResultFound;
	
	@Value("${task.deleted.successful}")
	public String taskDeletedSuccess;
	
	@Value("${task.deleted.fail}")
	public String taskDeletionFailed;

	@Value("${task.not.found}")
	public String taskNotFound;
	
	@Value("${task.updation.success}")
	public String taskUpdateSuccess;
	
	@Value("${task.updation.fail}")
	public String taskUpdateFail;
	
	@Value("${user.creation.success}")
	public String userCreationSuccess;
	
	@Value("${user.creation.already.exist}")
	public String userAlreadyExist;
	
	@Value("${user.creation.fail}")
	public String userCreationFailed;
	
	@Value("${unable.to.fetch.records}")
	public String unableToFetchRecords;
	
	@Value("${updated.successfully}")
	public String updatedSuccessfully;
	
	@Value("${failed.to.perform.operation}")
	public String failedOperationMsg;
	
	@Value("${deleted.successfully}")
	public String deletedSuccessfully;
}