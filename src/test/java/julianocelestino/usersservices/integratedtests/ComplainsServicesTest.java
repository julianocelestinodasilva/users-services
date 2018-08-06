package julianocelestino.usersservices.integratedtests;

import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import julianocelestino.usersservices.Complain;
import org.junit.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by juliano on 27/12/17.
 */

public class ComplainsServicesTest {

    private static final String URL = "http://172.23.0.2:8080/complains";
    private static final String CITY_ADDRESS_NOT_FOUND = "AddressNotFound";

    // TODO Try to change the request IP to fix it (AddressNotFound). Because my ip is 127.0.0.1, and it can't find in GeoLite2-City.mmdb

    @Test
    public void should_return_complains_from_specific_company_in_specific_city () throws Exception {
        final String expectedCompany = "Operadora";
        given().when().delete (URL).then().statusCode(204);
        given().contentType("application/json").and().body(new GsonBuilder().create()
                .toJson(new Complain("Cobrança Indevida","Operadora está fazendo uma cobrança indevida", expectedCompany))).post(URL);
        expect().statusCode(200).
                body("size()", is(1)).
                body("get(0).locale", equalTo(CITY_ADDRESS_NOT_FOUND)).
                body("get(0).company", equalTo(expectedCompany)).
                when().get(URL + "?company="+expectedCompany+"&city=" + CITY_ADDRESS_NOT_FOUND);
    }

    @Test
    public void should_ingest_a_complain() throws Exception {
        given().when().delete (URL).then().statusCode(204);
        final Complain complainToIngest = new Complain("Hamburguer queimado","Hamburguer estava queimado","Rock Burguer");
        Response response = given().contentType("application/json").and()
                .body(new GsonBuilder().create().toJson(complainToIngest)).post(URL);
        assertEquals(201,response.getStatusCode());
        final String complainURI = response.getHeader("location");
        final Complain complainIngested = given().contentType("application/json").get(complainURI).thenReturn()
                .getBody().as(Complain.class);
        complainToIngest.setLocale(CITY_ADDRESS_NOT_FOUND); // TODO Because my ip is 127.0.0.1, and it can't find in GeoLite2-City.mmdb
        assertEquals(complainToIngest,complainIngested);
    }

    @Test
    public void should_return_bad_request_for_invalid_complain() throws Exception {
        final Complain complainToIngest = new Complain("Hamburguer queimado","Hamburguer estava queimado","");
        Response response = given().contentType("application/json").and().body(new GsonBuilder().create().toJson(complainToIngest)).post(URL);
        assertEquals(400,response.getStatusCode());
    }
}
