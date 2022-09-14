package com.project.carrot;

import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressDataRepository;
import com.project.carrot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CarrotApplication {

	private final AddressDataRepository repository;
	private final MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(CarrotApplication.class, args);
	}

	@PostConstruct
	public void initAddressData() throws IOException {
		if(repository.count() ==0){
			AtomicInteger addressCode = new AtomicInteger(1000);
			ClassPathResource resource = new ClassPathResource("seoul_address.csv");
			List<AddressData> list = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
					.stream().map(line -> {
						String[] split = line.split(",");
						return AddressData.builder()
								.city(split[0])
								.district(split[1])
								.town(split[2])
								.addressCode(addressCode.getAndIncrement())
								.build();
					}).collect(Collectors.toList());
			repository.saveAll(list);
		}
	}




}
