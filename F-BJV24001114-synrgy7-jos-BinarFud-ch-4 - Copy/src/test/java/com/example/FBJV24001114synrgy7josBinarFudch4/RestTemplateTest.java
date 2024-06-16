package com.example.FBJV24001114synrgy7josBinarFudch4;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class RestTemplateTest {
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void listSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");


        ResponseEntity<Object> exchange = restTemplate.exchange("http://localhost:8080/api/products?name=goreng", HttpMethod.GET, null, Object.class);
        System.out.println("response  =" + exchange.getBody());

        // get value
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
}
