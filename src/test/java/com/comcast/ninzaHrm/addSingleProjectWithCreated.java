package com.comcast.ninzaHrm;

import org.eclipse.jetty.io.EndPoint;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.contains.endpoints.IEndPoint;

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
		
		 ProjectPojo pObj = new ProjectPojo(projectName, "Created", "Megha", 0);
		
		Response resp = given()
				.contentType(ContentType.JSON).body(pObj)
				.when().post(BASEURI+IEndPoint.ADDProj);
		
				//.when().post("http://49.249.28.218:8091/addProject");
		
	    resp.then().assertThat().statusCode(201)
	    .assertThat().time(Matchers.lessThan(3000L))
	    .assertThat().contentType(ContentType.JSON)
	   // .spec(respSpecObj).
	    .log().all();
	    
	   String actMsg = resp.jsonPath().get("msg");
	   Assert.assertEquals(expSucMsg,actMsg );
				
		
		
	
	
	
	}

}
