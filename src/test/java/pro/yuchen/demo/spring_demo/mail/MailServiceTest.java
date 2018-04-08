package pro.yuchen.demo.spring_demo.mail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.yuchen.demo.spring_demo.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class MailServiceTest {

	@Autowired
	private MailService mailService;

	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("yuchen352416@gmail.com", "Test", "hehehehehehehheheheh");
	}

	@Test
	public void testHtmlMail() throws Exception {

		StringBuffer content = new StringBuffer();
		content.append("<html>").append("\n");
		content.append("    <body>").append("\n");
		content.append("        <h3>Hello Mail....</h3>").append("\n");
		content.append("    </body>").append("\n");
		content.append("</html>").append("\n");
		mailService.sendHtmlMail("yuchen352416@gmail.com", "HTML Test", content.toString());
	}

	@Test
	public void testAttachmentsMail() {
		StringBuffer content = new StringBuffer();
		content.append("<html>").append("\n");
		content.append("    <body>").append("\n");
		content.append("        <h3>Hello Mail....</h3>").append("\n");
		content.append("    </body>").append("\n");
		content.append("</html>").append("\n");
		String filepath = "/Users/smile/Documents/notes/Journal.md";
		mailService.sendAttachmentsMail("yuchen352416@gmail.com", "File Test", content.toString(), filepath);
	}

	@Test
	public void testInlineResourceMail() {
		String rscId = "neo006";
		String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
		String imgPath = "/Users/smile/Pictures/Wechat.jpeg";
		mailService.sendInlineResourceMail("yuchen352416@gmail.com", "File Test", content, imgPath, rscId);


	}
}

