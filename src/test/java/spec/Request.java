package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;

public class Request {

    private static final RequestSpecification spec = new RequestSpecBuilder() //requestSpec
            .setBaseUri("https://reqres.in/")
            .setBasePath("api")
            .addFilter(filters().withCustomTemplates())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification spec(){
        return spec;
    }
}