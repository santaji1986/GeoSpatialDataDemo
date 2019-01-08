package org.san.geospatial.controller;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.san.geospatial.DemoApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = DemoApplication.class) 
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ImageControllerTests {
	 @Value("${server.port}") 
	    int port;
	
	  @Before
     public void setBaseUri () {
             RestAssured.port = port;
             RestAssured.baseURI = "http://localhost"; // replace as appropriate
     }
	  
	  @Test
	     public void test() {
	            String str = "test";
				get("/test?hello="+str).then().assertThat().body("message", equalTo("Hello "+str ));
	     }

	  @Test
	     public void testImageDownload() {
	         // @formatter:off
				Response response = given().
						contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body("{\n" + 
								"    \"utmZone\": 33,\n" + 
								"    \"latitudeBand\": \"U\",\n" + 
								"    \"gridSquare\": \"UP\",\n" + 
								"    \"date\": \"2018-08-08\",\n" + 
								"    \"channelMap\": \"visible\"\n" + 
								"}")
						.when()
						.post("/generate-images");
				// @formatter:on
				
				response.then().statusCode(404);
				System.out.println("POST Response\n" + response.asString());
				// tests
	  }
}

