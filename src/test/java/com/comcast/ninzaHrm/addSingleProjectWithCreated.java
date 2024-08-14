package com.comcast.ninzaHrm;

import org.eclipse.jetty.io.EndPoint;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.contains.endpoints.IEndPoint;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

public class addSingleProjectWithCreated extends BaseAPIClass{
	
	String projectName="f5"+jLib.getRandomNumber();
	@Test
	public void HRM_API_01() throws Throwable {
		
		String BASEURI = fLib.getDataFromPropertiesFile("BASEUri");
		//create an object to pojo class
		
		String expSucMsg="Successfully Added";
		
		
		
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
	   String proId=resp.jsonPath().get("projectId");
	   Assert.assertEquals(expSucMsg,actMsg );
	//}		
		//To verify GUI page
	   
//	@Test
//	public void verifyGUIpage() {
	  WebDriverManager.chromiumdriver().setup();
	   WebDriver driver=new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	   driver.get("http://49.249.28.218:8091/dashboard/projects");
	   driver.findElement(By.id("username")).sendKeys("rmgyantra");
	   driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	   driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	   driver.findElement(By.xpath("//a[text()='Projects']")).click();
	  WebElement searchText = driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']"));
	  searchText  .sendKeys(projectName);
	  searchText.click();
	  
	 WebElement ProjectId = driver.findElement(By.xpath("//table/tbody/tr/td[1]"));
	 
	String textValue = ProjectId.getText();
	 assertEquals(proId, textValue);
	
		
	} 
}
