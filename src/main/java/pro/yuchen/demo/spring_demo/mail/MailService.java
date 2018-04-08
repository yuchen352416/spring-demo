package pro.yuchen.demo.spring_demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender sender;

	@Value("${config.mail.fromMail.addr}")
	private String from;

	/**
	 * 发送文本邮
	 * @param to 要发送的邮箱地址
	 * @param subject e-mail主题
	 * @param content e-mail内容
	 */
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);

		try {
			sender.send(message);
			log.info("简单邮件已经发送。");
		} catch (Exception e) {
			log.error("发送简单邮件时发生异常！", e);
		}
	}

	/**
	 * 发送html邮件
	 * @param to 要发送的邮箱地址
	 * @param subject e-mail主题
	 * @param content e-mail内容
	 */
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = sender.createMimeMessage();
		try {
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			sender.send(message);
			log.info("html邮件发送成功");
		} catch (MessagingException e) {
			log.error("发送html邮件时发生异常！", e);
		}

	}

	/**
	 * 发送带附件的邮件
	 * @param to 要发送的邮箱地址
	 * @param subject e-mail主题
	 * @param content e-mail内容
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);

			sender.send(message);
			log.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送带附件的邮件时发生异常！", e);
		}
	}

	/**
	 * 发送正文中有静态资源（图片）的邮件
	 * @param to 要发送的邮箱地址
	 * @param subject e-mail主题
	 * @param content e-mail内容
	 * @param rscPath 静态图片路径
	 * @param rscId 静态图片编号
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			sender.send(message);
			log.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}
}
