package RESTAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trello {
public static String baseURL= "https://api.trello.com";
public static String id;

@Test(priority = 0)
public void createBoard()
{
	RestAssured.baseURI = baseURL;
	
	Response response =given().queryParam("name","Tanvi moolya1")
	.queryParam("key","45c5b9fdfd42a930c9b1952e808061a6")
	.queryParam("token","bc89e09155aa742f68666f08da32ea65ae9e1529768ff38f0f976590360549d0")
	.header("Content-Type","application/json")
	
	.when()
	.post("/1/boards/")
	
	.then()
	.assertThat().statusCode(200).contentType(ContentType.JSON)
	.extract().response();
	String jsonresponse = response.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	id = js.get("id");
}

@Test(priority = 1)
public void getBoard()
{
	RestAssured.baseURI = baseURL;
	
	Response response =given().queryParam("key","45c5b9fdfd42a930c9b1952e808061a6")
	.queryParam("token","bc89e09155aa742f68666f08da32ea65ae9e1529768ff38f0f976590360549d0")
	.header("Content-Type","application/json")
	
	.when()
	.get("/1/boards/MjbCynRL/")
	
	.then()
	.assertThat().statusCode(200).contentType(ContentType.JSON)
	.extract().response();
	String jsonresponse = response.asString();
	
	System.out.println(jsonresponse);
}

@Test(priority = 2)
public void deleteBoard()
{
	RestAssured.baseURI = baseURL;
	
	Response response =given().queryParam("key","45c5b9fdfd42a930c9b1952e808061a6")
	.queryParam("token","bc89e09155aa742f68666f08da32ea65ae9e1529768ff38f0f976590360549d0")
	.header("Content-Type","application/json")
	
	.when()
	.delete("/1/boards/"+id)
	
	.then()
	.assertThat().statusCode(200).contentType(ContentType.JSON)
	.extract().response();
String jsonresponse = response.asString();
	
	System.out.println(jsonresponse);
}

}
