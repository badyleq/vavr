package com.example.demo;

import org.springframework.stereotype.Component;

import io.vavr.Tuple;
import io.vavr.Tuple2;

@Component
public class VavrService {

	public Tuple2<String, Integer> test() {
		Tuple2<String, Integer> tuple = Tuple.of("xx", 2);
		return tuple;
	}
}
