package com.project.carrot;

import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressDataRepository;
import com.project.carrot.domain.member.dto.CreateMemberDto;
import com.project.carrot.domain.member.service.MemberService;
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

	private final AddressDataRepository repository;
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
			initTestMember();
		}
	}


	private void initTestMember() {
		for(int i=1; i<30; i++){
			Long address = Long.valueOf(i);
			CreateMemberDto createMemberDto = CreateMemberDto.builder()
					.loginId("test"+i)
					.password("cszc7348!@")
					.nickname("tester"+i)
					.email("tester"+i+"@test.com")
					.contact("010-0101-010"+i)
					.address(address).build();
			memberService.saveMember(createMemberDto);
		}
	}

}
