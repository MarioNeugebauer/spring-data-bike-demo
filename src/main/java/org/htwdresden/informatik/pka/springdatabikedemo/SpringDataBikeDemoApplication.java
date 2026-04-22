package org.htwdresden.informatik.pka.springdatabikedemo;

import org.htwdresden.informatik.pka.springdatabikedemo.model.Bike;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;
import org.htwdresden.informatik.pka.springdatabikedemo.repository.BikeRepository;
import org.htwdresden.informatik.pka.springdatabikedemo.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataBikeDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringDataBikeDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataBikeDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BikeRepository bikeRepository, OwnerRepository ownerRepository) {
		return (args) -> {


			Owner owner01 = new Owner("Klaus", "Klausnitzer");
			Owner owner02 = new Owner("Fred", "Feuerstein");

			Bike bike01 = new Bike("male", "1001", 26, 34);
			bike01.setOwner(owner01);
			Bike bike02 = new Bike("male", "1002", 28, 48);
			bike02.setOwner(owner01);
			Bike bike03 = new Bike("female", "1003", 26, 36);
			bike03.setOwner(owner02);

			owner01.getBikes().add(bike01);
			owner01.getBikes().add(bike02);
			owner02.getBikes().add(bike03);

			/*
			Bike bike04 = new Bike("female", "1004", 28, 56);
			Bike bike05 = new Bike("kid", "1005", 24, 28);
			Bike bike06 = new Bike("kid", "1006", 24, 30);
			*/


			ownerRepository.save(owner01);
			ownerRepository.save(owner02);

			bikeRepository.save(bike01);
			bikeRepository.save(bike02);
			bikeRepository.save(bike03);
			/*
			bikeRepository.save(bike04);
			bikeRepository.save(bike05);
			bikeRepository.save(bike06);
			 */

			log.info("Bikes found with findAll()");
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

			log.info("Owners found with findAll()");
			log.info("-------------------------");
			for(Owner owner: ownerRepository.findAll()) {
				log.info(owner.toString());
			}
			log.info("");

			log.info("Owners found with findByFirstname()");
			log.info("-------------------------");
			for(Owner owner: ownerRepository.findByFirstname("Klaus")) {
				log.info(owner.toString());
			}
			log.info("");


		};


	}

}
