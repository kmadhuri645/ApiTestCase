package com.comcast.ninzaHrm;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class addSingleProjectWithCreated extends BaseAPIClass{
	
	@Test
	public void HRM_API_01() throws Throwable {
		
		String BASEURI = fLib.getDataFromPropertiesFile("BASEUri");
		//create an object to pojo class
		
		String expSucMsg="Successfully Added";
		String projectName="ABB_9"+jLib.getRandomNumber();
		 ProjectPojo pObj = new ProjectPojo(projectName, "Created", "Megha", 10);
		
		Response resp = given()
				.contentType(ContentType.JSON).body(pObj)
				.when().post("http://49.249.28.218:8091/addProject");
		
	    resp.then().log().all();
	    
				
		
		
	
	
	
	}

}
