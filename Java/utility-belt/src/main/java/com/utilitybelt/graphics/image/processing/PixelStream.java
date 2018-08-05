package com.utilitybelt.graphics.image.processing;

import java.util.stream.IntStream;

@FunctionalInterface
public interface PixelStream {
	IntStream pixelStream();
}
