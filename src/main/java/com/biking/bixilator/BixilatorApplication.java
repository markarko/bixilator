package com.biking.bixilator;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpResponse;
import java.util.List;

@SpringBootApplication
public class BixilatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BixilatorApplication.class, args);
	}

}
