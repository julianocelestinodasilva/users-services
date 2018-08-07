package julianocelestino.usersservices.integratedtests;

import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import julianocelestino.usersservices.domain.User;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UserServicesTest {

    private static final String URL = "http://localhost:8080/users";

    @Before
    public void clearUsers() {
        given().when().delete (URL).then().statusCode(204);
    }

    @Test
    public void should_list_users_by_username() {

        final String username = "ABC_123";
        final User user = new User(username, "password"
                , "User", "My Sur Name", "userpost@test.com", "11 9 67564432");

        createUser(user);

        expect().statusCode(200).
                body("size()", is(3)).
                /*body("get(0).locale", equalTo(CITY_ADDRESS_NOT_FOUND)).
                body("get(0).company", equalTo(expectedCompany)).*/
                when().get(URL + "?username="+username);

    }

    private void createUser(User user) {
        Response response = given().contentType("application/json").and()
                .body(new GsonBuilder().create().toJson(user)).post(URL);
        assertEquals(201,response.getStatusCode());
    }
}
