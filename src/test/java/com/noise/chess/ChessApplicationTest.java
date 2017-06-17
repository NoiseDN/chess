package com.noise.chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ChessApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testJsp() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
