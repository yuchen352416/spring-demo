package pro.yuchen.demo.spring_demo.utils.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

	private static ImageUtils instance = new ImageUtils();

	private String text = "";

	private int width = 0;

	private int height = 0;

	private Font font = new Font("楷体", 0, 10);

	private String format = "jpg";



	private ImageUtils() {}

	/**
	 * 设置输出图片的宽高
	 * @param w 宽
	 * @param h 高
	 * @return
	 */
	public static ImageUtils size(int w, int h) {
		instance.width = w;
		instance.height = h;
		return instance;
	}

	/**
	 * 设置输出图片的宽高
	 * @param w 宽
	 * @param h 高
	 * @return
	 */
	public static ImageUtils font(String name, int style, int size) {
		instance.font = new Font(name, style, size);
		return instance;
	}



	/**
	 * 设置图片中的文字
	 * @param text 图片中的文字
	 * @return
	 */
	public static ImageUtils text(String text) {
		instance.text = text;
		return instance;
	}

	public static ImageUtils outputFormat(String format) {
		instance.format = format;
		return instance;
	}

	public static void toFile(String path) {
		if (instance.height == 0 || instance.width == 0) {
			throw new RuntimeException("参数不完整...");
		}
		try {
			// 输出文件
			File file = new File(path);
			// 创建画布
			BufferedImage bi = new BufferedImage(instance.width, instance.height, BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = bi.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

			//开始绘图
			g2.setBackground(Color.WHITE);
			g2.clearRect(0, 0, instance.width, instance.height);
//			g2.setPaint(new Color(0, 0, 255));
//			g2.fillRect(0, 0, 100, 10);
//			g2.setPaint(new Color(253, 2, 0));
//			g2.fillRect(0, 10, 100, 10);
			g2.setPaint(Color.BLACK);

			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = instance.font.getStringBounds(instance.text, context);
			double x = (instance.width - bounds.getWidth()) / 2;
			double y = (instance.height - bounds.getHeight()) / 2;
			double ascent = -bounds.getY();
			double baseY = y + ascent;

			//绘制字符串
			g2.drawString(instance.text, (int) x, (int) baseY);

			ImageIO.write(bi, instance.format, file);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			instance.width = 0;
			instance.height = 0;
			instance.text = "";
		}
	}




}
