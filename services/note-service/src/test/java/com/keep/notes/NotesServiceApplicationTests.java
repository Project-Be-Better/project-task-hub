package com.keep.notes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(exclude = { DataSourceAutoConfiguration.class })
class NotesServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
