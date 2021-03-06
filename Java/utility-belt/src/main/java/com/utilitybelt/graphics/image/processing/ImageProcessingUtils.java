package com.utilitybelt.graphics.image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.Setter;

public class ImageProcessingUtils {


	public static int getDominantColor(BufferedImage img) {
		return getAbsoluteDominantColor(img, new RandomBufferedImagePixelStream(img));

	}

	public static int getAbsoluteDominantColor(BufferedImage img, PixelStream extractor) {
		Map<Integer, Integer> sections = new HashMap<>();
		ColorIndex maxHolder = new ColorIndex();
		extractor.pixelStream().map((int pixel) -> {
			return colorGroup(pixel);
		})
		.forEach((int pixel) -> { 
			Integer count = sections.get(pixel);
			if (count == null) 
				count = 1;
			else
				count += 1;

			sections.put(pixel, count);
			if(maxHolder.getCount() < count) {
				maxHolder.setCount(count);
				maxHolder.setColor(pixel); 
			}
		});

		return maxHolder.getColor();
	}

	private static int colorGroup(int pixel) {
		return pixel;// - (pixel%10);
	}

	private static int[][] bufferedImageToPixelArray(BufferedImage img) {
		final byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		final int width = img.getWidth();
		final int height = img.getHeight();
		final boolean hasAlphaChannel = img.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				result[row][col]=bytesToPixel(pixels[pixel + 3],
						pixels[pixel + 2], pixels[pixel + 1], pixels[pixel], hasAlphaChannel);
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				result[row][col]=bytesToPixel(pixels[pixel + 2],
						pixels[pixel + 1], pixels[pixel], (byte)0, hasAlphaChannel);
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}

		return result;
	}

	private static int bytesToPixel(byte red, byte green, byte blue, byte alpha, boolean hasAlphaChannel) {
		int argb = 0;
		if (hasAlphaChannel) {
			argb += (((int) alpha & 0xff) << 24); // alpha
			argb += ((int) blue & 0xff); // blue
			argb += (((int) green & 0xff) << 8); // green
			argb += (((int) red & 0xff) << 16); // red
		} else {
			argb += -16777216; // 255 alpha
			argb += ((int) blue & 0xff); // blue
			argb += (((int) green & 0xff) << 8); // green
			argb += (((int) red & 0xff) << 16); // red
		}

		return argb;
	}

	///////////////////////////////////////////////////////////////////////////////
	// Helper classes
	//////////////////////////////////////////////////////////////////////////////
	public static class BufferedImagePixelStream implements PixelStream {

		private BufferedImage img;
		public BufferedImagePixelStream(BufferedImage img) {
			this.img = img;
		}

		@Override
		public IntStream pixelStream() {
			int[][] pixels = bufferedImageToPixelArray(img);
			return Arrays.stream(pixels).flatMapToInt(x -> Arrays.stream(x));
		}
	}

	private static class RandomBufferedImagePixelStream implements PixelStream {

		private BufferedImage img;

		public RandomBufferedImagePixelStream(BufferedImage img) {
			this.img = img;
		}

		private static int SAMPLES_FACTOR = 7;
		private static int SAMPLES_MINIMUM = 100;

		@Override
		public IntStream pixelStream() {
			final int height = Math.max(img.getHeight() / SAMPLES_FACTOR, Math.min(SAMPLES_MINIMUM, img.getHeight())) ,
					width = Math.max(img.getWidth() / SAMPLES_FACTOR, Math.min(SAMPLES_MINIMUM, img.getWidth()));
			int samplesNum = height*width;

			System.out.format("width : %d, height : %d , new width : %d , new height : %d%n", img.getWidth(), img.getHeight(), width, height);
			
			ThreadLocalRandom random = ThreadLocalRandom.current();
			int[][] result = new int[height][width];
			for (int pixel = 0, row = 0, col = 0; pixel < samplesNum; pixel++) {
				int x = random.nextInt(0,width);
				int y = random.nextInt(0,height);
				result[row][col] = img.getRGB(x, y);
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}

			return Arrays.stream(result).flatMapToInt(x -> Arrays.stream(x));
		}
	}

		@Getter @Setter
		private static class ColorIndex {
			private int count = 0;
			private int color;
		}

	}
