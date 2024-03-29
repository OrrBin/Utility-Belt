package com.utilitybelt.test.graphics.image.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import com.utilitybelt.graphics.image.processing.ImageProcessingUtils;

public class DominantColorTest {

	private static final String IMAGE_PATH="D:\\projects\\utility-belt\\Java\\utility-belt\\resources\\Test2.png";
	private BufferedImage img;
	
	@Before
	public void setUp() throws Exception {
		img = ImageIO.read(new File(IMAGE_PATH));
		
	}
	
	@Test
	public void test() {
		int result = ImageProcessingUtils.getAbsoluteDominantColor(img, new ImageProcessingUtils.BufferedImagePixelStream(img));
		int randomResult = ImageProcessingUtils.getDominantColor(img);
		Color color = new Color(result, true);
		Color randomColor = new Color(randomResult, false);
		System.out.format("(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
		System.out.format("random result : (%d,%d,%d)", randomColor.getRed(), 
				randomColor.getGreen(), randomColor.getBlue());
	}

}
