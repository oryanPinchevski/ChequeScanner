package chequescanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chequescanner.impl.ChequeScannerImpl;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	
	@Bean
	public ChequeScannerImpl chequeScanner() {
		return new ChequeScannerImpl(restTemplate());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
