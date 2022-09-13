package com.project.carrot;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RequiredArgsConstructor
@Profile("test")
@Transactional
class CarrotApplicationTests {



	@Test
	void contextLoads() {
	}


}
