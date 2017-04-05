package com.asaunin.utils;

import java.util.concurrent.ThreadLocalRandom;

public interface RandomEnum {

	static <T extends Enum<T>> Enum<T> get(Class<T> clazz) {
		final int index = ThreadLocalRandom.current().nextInt(0, clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[index];
	}

}
