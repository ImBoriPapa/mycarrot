package com.project.carrot;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class CarrotApplication {

	private final AddressRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CarrotApplication.class, args);
	}

	@PostConstruct
	public void initAddressData() throws IOException {
		if(repository.count() ==0){
			ClassPathResource resource = new ClassPathResource("seoul_address.csv");
			List<Address> list = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
					.stream().map(line -> {
						String[] split = line.split(",");
						return Address.builder()
								.city(split[0])
								.district(split[1])
								.town(split[2]).build();
					}).collect(Collectors.toList());
			repository.saveAll(list);
		}
	}

}
