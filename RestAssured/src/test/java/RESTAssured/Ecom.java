package RESTAssured;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Ecom {
	public static String baseURL= "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String token;
	public static String id;
	@Test(priority = 0, enabled = false)
	public void signUp()
	{
		RestAssured.baseURI = baseURL;
		
		
		String requestbody ="{\r\n"
				+ "				\"email\": \"sdfg@gmail.com\",\r\n"
				+ "				\"password\": \"sdfg2\"\r\n"
				+ "			}";
		
	
		Response response =given()
		.body(requestbody)
		.header("Content-Type","application/json")
		
		
		.when()
		.post("/user/signup")
		
		.then()
		.assertThat().statusCode(201).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		message = js.get("message");
		System.out.println(message);
		
	}
	@Test(priority = 1)
	public void login()
	{
	RestAssured.baseURI = baseURL;
		
		
		String requestbody ="{\r\n"
				+ "				\"email\": \"sdfg@gmail.com\",\r\n"
				+ "				\"password\": \"sdfg2\"\r\n"
				+ "			}";
		
	
		Response response =given()
		.body(requestbody)
		.header("Content-Type","application/json")
		
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		token = js.get("accessToken");
		System.out.println(token);
	}
	@Test(priority = 2, enabled = false)
	public void getallusers()
	{
	RestAssured.baseURI = baseURL;
		
	Response response =given()
		.header("Authorization", "Bearer"+token)
			
		.when()
		.get("/user")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		//JsonPath js = new JsonPath(jsonresponse);
		//id = js.getString("user[12]._id")
		//System.out.println(js);
	}
	@Test(priority = 1, enabled = false)
	public void delete()
	{
	RestAssured.baseURI = baseURL;
		
		
		
		
	
		Response response =given()
		.header("Content-Type","application/json")
		
		
		.when()
		.delete("/user/{{id}}")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		token = js.get("accessToken");
		System.out.println(token);
		
	}
}