package com.ninza.hrm.api.baseClass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutiltiy.DataBaseUtilities;
import com.ninza.hrm.api.genericutiltiy.FileUtility;
import com.ninza.hrm.api.genericutiltiy.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtilities dbLib = new DataBaseUtilities();
	
	public static  RequestSpecification specReqbObj;
	public static  ResponseSpecification respSpecObj;
	
	@BeforeSuite
	public void configBS() throws Throwable {
		dbLib.connectToDB();
		System.out.println("=============connect to db==============");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username","password"));
		//builder.addHeader("", "");
		
		builder.setBaseUri(fLib.getDataFromPropertiesFile("BASEUri"));
		specReqbObj = builder.build();
		 
		 ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
		  respSpecObj = resBuilder.build();
	
		
	}
	@AfterSuite
	public void configAS() throws SQLException {
		 dbLib.closeDb();
		 System.out.println("disconnect to db");
	}

}
