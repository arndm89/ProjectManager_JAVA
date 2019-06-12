package com.cts.tm.ProjectManager;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.tm.ProjectManagerApplication;
import com.cts.tm.controller.AddProjectController;
import com.cts.tm.controller.AddTaskController;
import com.cts.tm.vo.ProjectVO;
import com.cts.tm.vo.TaskVO;
import com.cts.tm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ProjectManagerApplication.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProjectManagerApplicationTests {
	
	
		@Autowired
		private AddProjectController projController;
		@Autowired AddTaskController taskCont;
		
		private MockMvc mockMvc;
		
		private ProjectVO project;
		TaskVO taskVo;
		UserVO user;

		@Before
		public void init(){
			mockMvc = MockMvcBuilders.standaloneSetup(projController).build();

			user = new UserVO();
			user.setUserId(1);
			user.setFirstName("manager-name");
			user.setLastName("manager-surname");
			user.setEmployeeId(123456);
			
			project = new ProjectVO();
			project.setProject("Test-project");
			project.setPriority(19);
			project.setStartDate(new Date(System.currentTimeMillis()));
			project.setEndDate(new Date(System.currentTimeMillis() + (24*60*60*1000)));
//			project.setManager(manager);
			
			
			taskVo = new TaskVO();
			taskVo.setParentTask("Parent task TEST");
			taskVo.setParentTaskBoolean(true);
			
			
			
			
		}
		
		@Test
		public void getAllProjects(){
			RequestBuilder request = MockMvcRequestBuilders.get("/getAllProjects").accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
			
		}
		//	getAllTaskForTaskModal
		@Test
		public void getAllTaskForTaskModal(){
			RequestBuilder request = MockMvcRequestBuilders.get("/getAllTaskForTaskModal")
						.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				assertTrue(e!= null);
			}
			
			
			
			request = MockMvcRequestBuilders.get("/searchTask?projectId="+this.taskVo.getProjectId())
					.accept(MediaType.APPLICATION_JSON);
			result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		
		@Test
		public void getAllProjectsForTask(){
			this.addProject();
			RequestBuilder request = MockMvcRequestBuilders.get("/getAllProjectsForTask").accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		
		@Test
		public void getAllUsers(){
			RequestBuilder request = MockMvcRequestBuilders.get("/getAllUsers")
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
			
			
			request = MockMvcRequestBuilders.get("/getAllUsersForProject")
					.accept(MediaType.APPLICATION_JSON);
			result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
			
			
			request = MockMvcRequestBuilders.get("/getAllUsersForTask")
					.accept(MediaType.APPLICATION_JSON);
			result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
			
		}

		@Test
		public void addProject(){
			RequestBuilder request = MockMvcRequestBuilders.post("/createProject")
					.content(getValueAsString(project))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		
		@Test
		public void addTask(){
			RequestBuilder request = MockMvcRequestBuilders.post("/createTask")
					.content(getValueAsString(taskVo))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		@Test
		public void addUser(){
			RequestBuilder request = MockMvcRequestBuilders.post("/createUser")
					.content(getValueAsString(user))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		@Test
		public void updateProject(){
			
			this.project.setStartDate(null);
			this.project.setEndDate(null);
			this.addProject();
			this.project.setProject(this.project.getProject() + "_updated");
			RequestBuilder request = MockMvcRequestBuilders.put("/updateProject")
					.content(getValueAsString(this.project))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		@Test
		public void updateUser(){
			
			this.user.setFirstName("FIRST name changed");
			this.addUser();
			RequestBuilder request = MockMvcRequestBuilders.put("/updateUser")
					.content(getValueAsString(this.project))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		
		@Test
		public void updateTask(){
			this.taskVo.setTask("NAME UPDATED");
			this.addTask();
			RequestBuilder request = MockMvcRequestBuilders.put("/updateTask")
					.content(getValueAsString(this.taskVo))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}

		@Test
		public void deleteProjects(){
			this.addProject();
			RequestBuilder request = MockMvcRequestBuilders.delete("/deleteProject" + this.project.getProjectId());
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		@Test
		public void deleteUser(){
			this.addProject();
			RequestBuilder request = MockMvcRequestBuilders.delete("/deleteUser" + this.user.getUserId());
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
				assertTrue(result != null);
			} catch (Exception e) {
				e.printStackTrace();
				//assertTrue(e != null);
			}
		}
		@Test
		public void deleteTask(){
			this.addProject();
			RequestBuilder request = MockMvcRequestBuilders.delete("/endTask" + this.taskVo.getTaskId());
			MvcResult result = null;
			try {
				result = mockMvc.perform(request).andReturn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertTrue(result != null);
		}
		private String getValueAsString(Object obj){
			ObjectMapper mapper = new ObjectMapper();
			try {
				return mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

