package pro.yuchen.demo.spring_demo.web;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;


@RestController
public class QrCodeController {

	@RequestMapping("/qrcode/{id}")
	public void image(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 生成二维码图片
		byte[] bytes = QrcodeUtils.createQrcode("嘿嘿....", 800, null);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
		//设置浏览器以图片格式输出
		response.setContentType("image/jpeg");
		//设置响应头控制浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		//将图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
}
