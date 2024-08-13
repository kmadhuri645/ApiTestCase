package com.ninza.hrm.api.baseClass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutiltiy.DataBaseUtilities;
import com.ninza.hrm.api.genericutiltiy.FileUtility;
import com.ninza.hrm.api.genericutiltiy.JavaUtility;

public class BaseAPIClass2 {
	
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtilities dbLib = new DataBaseUtilities();
	
	@BeforeSuite
	public void configBS() throws Throwable {
		dbLib.connectToDB();
		System.out.println("connect to db");
	}
	@AfterSuite
	public void configAS() throws SQLException {
		 dbLib.closeDb();
		 System.out.println("disconnect to db");
	}

}
