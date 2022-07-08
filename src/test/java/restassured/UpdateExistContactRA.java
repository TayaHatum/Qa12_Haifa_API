package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class UpdateExistContactRA {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
    ContactDto contact= ContactDto.builder()
            .name("Dolly")
            .lastName("Dow")
            .email("dolly@mail.com")
            .phone("77766684321")
            .address("Haifa")
            .description("friend").build();
    int id ;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://contacts-telran.herokuapp.com/";
        RestAssured.basePath="api";



        id =given()
                .header("Authorization",token)
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");

    }

    @Test
    public void updateExistContactSuccess(){
        contact.setId(id);
        contact.setName("wwwww");

        given()
                .body(contact)
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .when()
                .put("contact")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("name",containsString("wwwww"));

    }
}
