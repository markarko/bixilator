package com.biking.bixilator;

import com.biking.bixilator.requests.DataFetch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BixilatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BixilatorApplication.class, args);
		DataFetch.fetchBikeData();
	}

}
