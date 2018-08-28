package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.vavr.Function1;
import io.vavr.Tuple;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	private VavrService vavrService;
	
	@Before
	public void init() {
		vavrService = new VavrService();
	}

	@Test
	public void contextLoads() {
		assertThat(vavrService.test()).isEqualTo(Tuple.of("xx", 2));
	}
	
	@Test
	public void testComposition1() {
		Function1<Integer, Integer> plusOne = a -> a + 1;
		Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

		Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

		assertThat(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
	}
	
	@Test
	public void testComposition2() {
		Function1<Integer, Integer> plusOne = a -> a + 1;
		Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

		Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);

		assertThat(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
	}
}
