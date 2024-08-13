package com.comcast.ninzaHrm;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.contains.endpoints.IEndPoint;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class addProjectWitONGoingStatus extends BaseAPIClass {
	
	
	
	@Test
	public void HRM_API_02() throws Throwable {
		
		String expSucMsg="Successfully Added";
		
		String BaseURL= fLib.getDataFromPropertiesFile("BASEUri");
		
		String projectName="f1"+jLib.getRandomNumber();
		
		ProjectPojo pbody=new ProjectPojo(projectName, "Ongoing", "Megha", 0);
		
		Response resp = given().contentType(ContentType.JSON)
				.body(pbody).when()
				.post(BaseURL+IEndPoint.ADDProj);
		
		resp.then().assertThat().statusCode(201).
		assertThat().time(Matchers.lessThan(3000L))
		.assertThat().contentType(ContentType.JSON)
		.log().all();
		
		String actMsg=resp.jsonPath().getString("msg");
		
		Assert.assertEquals(actMsg, expSucMsg);
		
				
				
		
		 
	}

}
