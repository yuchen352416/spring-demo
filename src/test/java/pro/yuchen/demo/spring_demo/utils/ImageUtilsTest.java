package pro.yuchen.demo.spring_demo.utils;

import org.junit.Test;
import pro.yuchen.demo.spring_demo.utils.img.ImageUtils;

public class ImageUtilsTest {

	@Test
	public void create_image() {
		ImageUtils
				.size(600, 70)
				.text("入境日期: 2018-10-10")
				.font("楷体", 1, 72)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test_9.png");

	}
}
