package study.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class  TddApplicationTests {

	@Test
	void contextLoads() {
		File dataFile = new File("src/test/resources/datafile.txt");

	}

}
