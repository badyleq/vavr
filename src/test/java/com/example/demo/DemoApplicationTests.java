package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;
import io.vavr.Tuple;

import static io.vavr.API.*;

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
		Function1<String, String> sayHello = name -> "Hello " + name;
		Function1<String, String> doGreeting = hello -> hello + ", nice to see you!";

		Function1<String, String> sayHelloAndGreet = sayHello.andThen(doGreeting);

		assertThat(sayHelloAndGreet.apply("Stefan")).isEqualTo("Hello Stefan, nice to see you!");
	}

	@Test
	public void testComposition2() {
		Function1<String, String> sayHello = name -> "Hello " + name;
		Function1<String, String> doGreeting = hello -> hello + ", nice to see you!";

		Function1<String, String> sayHelloAndGreet = sayHello.andThen(doGreeting);

		assertThat(sayHelloAndGreet.apply("Stefan")).isEqualTo("Hello Stefan, nice to see you!");
	}

	@Test
	public void testPartialApplication1() {
		Function1<Integer, Integer> plusOne = a -> a + 1;
		Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

		Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);

		assertThat(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
	}

	@Test
	public void testPartial2() {
		Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d, e) -> a + b + c + d + e;
		Function2<Integer, Integer, Integer> add6 = sum.apply(2, 3, 1);

		assertThat(add6.apply(4, 3)).isEqualTo(13);
	}

	@Test
	public void testPatternMatching1() {
		Integer req = 1;
		String s = Match(req).of(Case($(1), "one"), Case($(2), "two"), Case($(), "?"));
		System.out.println(s);
	}
}
