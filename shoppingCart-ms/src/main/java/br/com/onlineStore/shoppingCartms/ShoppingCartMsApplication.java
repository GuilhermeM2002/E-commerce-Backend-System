package br.com.onlineStore.shoppingCartms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShoppingCartMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartMsApplication.class, args);
	}

}
