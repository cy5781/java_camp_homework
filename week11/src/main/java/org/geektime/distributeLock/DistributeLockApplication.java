package org.geektime.distributeLock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="org.geektime")
public class DistributeLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributeLockApplication.class, args);
	}

}
