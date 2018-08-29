package com.example.demo;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;
import io.vavr.Tuple;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static org.assertj.core.api.Assertions.assertThat;

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

        System.out.println(sayHelloAndGreet.apply("Stefan"));
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

    @Test
    public void testPatternMatching2() {
        val arg = "-u";
        try {
            Match(arg).of(
                    Case($(isIn("-h", "--help")), o -> run(() -> System.out.println("help"))),
                    Case($(isIn("-v", "--version")), o -> run(() -> System.out.println("version"))),
                    Case($(), o -> run(() -> {
                        throw new IllegalArgumentException(arg);
                    }))
            );
        } catch (final IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo(arg);
        }
    }
}
