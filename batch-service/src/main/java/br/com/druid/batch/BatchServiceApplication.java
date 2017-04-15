package br.com.druid.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchServiceApplication {
	
	private int maxUploadSizeInMb = 300 * 1024 * 1024; // 300 MB

	public static void main(String[] args) {
		SpringApplication.run(BatchServiceApplication.class, args);
	}
}
