package com.comcast.ninzaHrm;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.contains.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class addDuplicateProject extends BaseAPIClass {
	
	@Test
	public void HRM_API_04() throws Throwable {
		
		String projectName = "joly"+jLib.getRandomNumber();
		ProjectPojo pobj=new ProjectPojo("", "Created", "Megha", 0);
		String BaseUrl=fLib.getDataFromPropertiesFile("BASEUri");
		
		Response resp = given().contentType(ContentType.JSON)
		.body(pobj).when().post(BaseUrl+IEndPoint.ADDProj);
		
		
		resp.then().assertThat().contentType(ContentType.JSON)
		.assertThat().statusCode(409).log().all();
		
		
		
		
		

		

     }		
		
		
		
	}


