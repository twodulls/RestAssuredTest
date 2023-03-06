package com.test.common;

import com.test.testdata.TestData;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
public class BaseTest {

    public static String BASEURL;
    public static String APIKEY;
    public static String PROFILEID;

    @BeforeSuite
    public void beforeSuitre() throws FileNotFoundException {
        PROFILEID = System.getProperty("profileId");
        if(PROFILEID == null){
            PROFILEID = "qa";
        }

        InputStream input = null;
        switch (PROFILEID){
            case "prod":
                input = new FileInputStream(new File("src/test/resources/prod/data.yml"));
                break;
            case "beta":
                input = new FileInputStream(new File("src/test/resources/beta/data.yml"));
                break;
            case "qa":
                input = new FileInputStream(new File("src/test/resources/qa/data.yml"));
                break;
            default:
                input = new FileInputStream(new File("src/test/resources/qa/data.yml"));
                break;
        }

        Yaml yaml = new Yaml();
        TestData data = yaml.loadAs(input, TestData.class);

        BASEURL = data.getBaseUrl();
        APIKEY = data.getApiKey();

        RestAssured.baseURI = BASEURL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }
}
