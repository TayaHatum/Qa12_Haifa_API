package restassured;

import com.jayway.restassured.RestAssured;
import dto.Auth;
import dto.AuthResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTestsRA {

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://contacts-telran.herokuapp.com/";
        RestAssured.basePath="api";
    }

    @Test
    public void loginSuccess(){
        Auth auth = Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build();

        AuthResponse authResponse =given()
                .body(auth)
                .contentType("application/json")
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponse.class);
        System.out.println(authResponse.getToken());

    }
}
