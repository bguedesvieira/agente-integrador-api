package br.com.workday.g4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class AgenteIntegradorApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgenteIntegradorApi.class);

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(AgenteIntegradorApi.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void printAllEndpoints() {

		final var mapping = this.applicationContext.getBean(RequestMappingHandlerMapping.class);
		final var methods = mapping.getHandlerMethods();
		methods.forEach((k, v) -> LOGGER.info("URL: {} - MethodSignature: {}", k, v));

	}

}
