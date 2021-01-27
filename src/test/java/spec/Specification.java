package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;

public class Specification { //requestSpec

    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .setBasePath("api")
            .addFilter(filters().withCustomTemplates())
            .setContentType(ContentType.JSON)
            .build();

    public static RequestSpecification spec(){
        return spec;
    }
}