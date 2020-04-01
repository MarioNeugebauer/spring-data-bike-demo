package org.htwdresden.informatik.pkademo.SpringPKAdemo;

import org.htwdresden.informatik.pkademo.SpringPKAdemo.model.Bike;
import org.htwdresden.informatik.pkademo.SpringPKAdemo.repository.BikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPkAdemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringPkAdemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringPkAdemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(BikeRepository bikeRepository) {
		return (args) -> {
			bikeRepository.save(new Bike("male", "1001", 26, 34));
			bikeRepository.save(new Bike("male", "1002", 28, 48));
			bikeRepository.save(new Bike("female", "1003", 26, 36));
			bikeRepository.save(new Bike("female", "1004", 28, 56));
			bikeRepository.save(new Bike("kid", "1005", 24, 28));
			bikeRepository.save(new Bike("kid", "1006", 24, 30));

			log.info("Bike found with findAll()");
			log.info("-------------------------");
			for (Bike bike : bikeRepository.findAll()) {
				log.info(bike.toString());
			}
			log.info("");

			log.info("Bikes found with findByType(''kid'')");
			log.info("-------------------------");
			for (Bike bike : bikeRepository.findByType("kid")) {
				log.info(bike.toString());
			}
			log.info("");

			log.info("Bikes found with findByTypeAndFrameSizeGreaterThan(...)");
			log.info("-------------------------");
			for (Bike bike : bikeRepository.findByTypeAndFrameSizeGreaterThan("kid",28)) {
				log.info(bike.toString());
			}
			log.info("");


		};


	}

}
