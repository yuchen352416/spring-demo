package pro.yuchen.demo.spring_demo.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import pro.yuchen.demo.spring_demo.utils.img.ImageUtils;
import pro.yuchen.demo.spring_demo.utils.img.SelfPositions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class ThumbnailatorTest {

	@Test
	public void base() throws IOException {
		// 缩放
		Thumbnails.of("/Users/smile/Desktop/Temp/test_5.jpg")
				.size(1000, 1500)
				.toFile("/Users/smile/Desktop/Temp/test_6.jpg");
	}

	@Test
	public void watermark() throws IOException {
		// 水印

		Thumbnails.of("/Users/smile/Desktop/Temp/test/home.jpg")
				.size(530,380)
				.watermark(Positions.TOP_LEFT, ImageIO.read(new File("/Users/smile/Desktop/Temp/test/paperwork.jpg")),1.0f)
				.outputQuality(1.0f)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test/test_01.png");
	}

	@Test
	public void test() throws Exception {

		// A4纸
		ImageUtils
				.size(1240, 1754)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test/a4.png");

		// 字条
		ImageUtils
				.size(530, 27)
				.text("测试图片文字翩翩体...")
				.font("楷体", 1, 18)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test/text.png");

		// 合成带纸条的护照首页
		Thumbnails.of("/Users/smile/Desktop/Temp/test/home.jpg")
				.size(530,380)
				.watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File("/Users/smile/Desktop/Temp/test/text.png")),1.0f)
				.outputQuality(1.0f)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test/test_02.png");

		// 加到A4纸上
		Thumbnails.of("/Users/smile/Desktop/Temp/test/a4.png")
				.size(2105, 1487)
				.watermark(SelfPositions.TOP_LEFT, ImageIO.read(new File("/Users/smile/Desktop/Temp/test/test_02.png")),1.0f)
				.outputQuality(1.0f)
				.outputFormat("png")
				.toFile("/Users/smile/Desktop/Temp/test/test_03.png");
	}

}
