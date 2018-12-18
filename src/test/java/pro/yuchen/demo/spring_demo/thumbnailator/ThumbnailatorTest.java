package pro.yuchen.demo.spring_demo.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import pro.yuchen.demo.spring_demo.utils.img.ImageUtils;
import pro.yuchen.demo.spring_demo.utils.img.SelfPositions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理程序
 */
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
				.size(1436, 2700)
				.outputFormat("png")
				.toFile("/Users/Selfimpr/Desktop/white_border.png");

		// 合成带纸条的护照首页
		Thumbnails.of("/Users/Selfimpr/Desktop/white_border.png")
				.size(1436,2700)
				.watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File("/Users/Selfimpr/Desktop/tempImage.jpg")),1.0f)
				.outputQuality(1.0f)
				.outputFormat("png")
				.toFile("/Users/Selfimpr/Desktop/temp.png");
	}

}
