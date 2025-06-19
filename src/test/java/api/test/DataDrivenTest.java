package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndsPoints;
import api.payload.User;
import api.utilites.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class) //It will send the data
	public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph)//It will receive the data
	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setLastName(fname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndsPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2,dataProvider="UserNames",dataProviderClass =DataProviders.class)
	public void testDeleteUserByNameByName(String userName)
	{
		Response response=UserEndsPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
