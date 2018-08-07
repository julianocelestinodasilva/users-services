package julianocelestino.usersservices.integratedtests;

import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import julianocelestino.usersservices.User;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UserServicesTest {

    private static final String URL = "http://localhost:8080/users";

    @Test
    public void name() {

        final User user = new User("UserPost", "password"
                , "User", "Post", "userpost@test.com", "11 9 67564432");

        Response response = given().contentType("application/json").and()
                .body(new GsonBuilder().create().toJson(user)).post(URL);

        assertEquals(201,response.getStatusCode());

    }
}
