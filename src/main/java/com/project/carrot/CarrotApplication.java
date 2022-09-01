package com.project.carrot;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressRepository;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class CarrotApplication {

	private final AddressRepository repository;
	private final MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(CarrotApplication.class, args);
	}

	@PostConstruct
	public void initAddressData() throws IOException {
		if(repository.count() ==0){
			ClassPathResource resource = new ClassPathResource("seoul_address.csv");
			List<AddressData> list = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
					.stream().map(line -> {
						String[] split = line.split(",");
						return AddressData.builder()
								.city(split[0])
								.district(split[1])
								.town(split[2]).build();
					}).collect(Collectors.toList());
			repository.saveAll(list);
		}
	}

	@PostConstruct
	public void initTestMember(){
		Address firstAddress = Address.builder()
				.city("서울시")
				.district("강서구")
				.town("화곡동").build();
		List address =new ArrayList<>();
		address.add(firstAddress);

		Member member = Member.builder()
				.loginId("test")
				.password("cszc7348!@")
				.nickname("tester")
				.email("tester@test.com")
				.contact("010-0101-0101")
				.address(address).build();
		memberService.saveMember(member);

	}

}
