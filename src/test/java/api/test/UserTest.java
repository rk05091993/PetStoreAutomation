package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndsPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());//we have used hashcode method to generate unique id number
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("**************************Creating user***********************");
		Response response=UserEndsPoints.createUser(userPayload);
		response.then().log();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************************User is Created***********************");
	}
	
	@Test(priority = 2,dependsOnMethods = {"testPostUser"})
	public void testGetUserByName()
	{
		
		logger.info("**************************Reading user info***********************");
		Response response=UserEndsPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("**************************User info is displayed***********************");
	}
	

	@Test(priority=3)
	public void testUpdateUserByName()
	{
		
		logger.info("**************************Updating user***********************");
		//Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndsPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body().statusCode(200);//Chai assertion
		//Assert.assertEquals(response.statusCode(), 200);//TestNg assertion
		logger.info("**************************user is updated***********************");
		//checking data after update
		Response responseAfterupdate=UserEndsPoints.updateUser(this.userPayload.getUsername(),userPayload);
		Assert.assertEquals(responseAfterupdate.statusCode(), 200);
		
	}

	@Test(priority = 4)
	public void testDeleteByName()
	{
		
		logger.info("**************************Deleting the user***********************");
		Response response=UserEndsPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**************************User is deleted***********************");
	}
}
