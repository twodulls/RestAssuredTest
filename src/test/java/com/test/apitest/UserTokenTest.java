package com.test.apitest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.common.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTokenTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenTest.class);
    private String userToken;
    private SoftAssertions softAssertions;

    @Test
    public void getTokenTest(){
        softAssertions = new SoftAssertions();

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", "TEST");
        String reqBody = gson.toJson(jsonObject);
        logger.debug("reqBody : {}", reqBody);

        Response response =
                given()
                        .header("Content-type", "Application/json")
                        .header("api-key", APIKEY)
                        .body(reqBody)
                        .when()
                        .post("/user/tokens")
                        .then()
                        .extract().response();
        JsonPath jsonPath = response.jsonPath();
        userToken = jsonPath.getString("data.token");
        softAssertions.assertThat(response.statusCode()).isEqualTo(200);
        softAssertions.assertThat(userToken).isNotNull();
        softAssertions.assertAll();
    }
}
