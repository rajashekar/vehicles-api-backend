package com.udacity.pricing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import com.udacity.pricing.domain.price.Price;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void vehicleByIdSucess() {
		ResponseEntity<Price> response = this.restTemplate
				.getForEntity("http://localhost:" + port + "/services/price?vehicleId=1", Price.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void vehicleByIdFail() {
		ResponseEntity<Price> response = this.restTemplate
				.getForEntity("http://localhost:" + port + "/services/price?vehicleId=20", Price.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
	}

}
