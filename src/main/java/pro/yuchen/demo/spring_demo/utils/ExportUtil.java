package pro.yuchen.demo.spring_demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class ExportUtil {

	private final static Logger log = LoggerFactory.getLogger(ExportUtil.class);

	private ExportUtil() {}

	/**
	 * 为文件提供下载方法
	 *
	 * @param path 文件路径
	 * @param request 请求体
	 * @param response 响应体
	 */
	public static void export(String path, HttpServletRequest request, HttpServletResponse response) {
		File file = new File(path);
		if(!file.exists()){
			log.error("文件不存在");
			return;
		}
		try {
			String fileName = file.getName();
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			response.reset();
			response.setContentType("application/"+prefix+";charset=UTF-8");
			String filenamedisplay = URLEncoder.encode(fileName, "UTF-8");
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				filenamedisplay = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				filenamedisplay = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("SAFARI") > 0) {
				filenamedisplay = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// safari 浏览器
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
				filenamedisplay = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//  Chrome浏览器
			}
			long fileLength = file.length();
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filenamedisplay +"\"");
			response.setHeader("Content-Length", String.valueOf(fileLength));
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)  {
				out.write(buf, 0, len);
			}
			out.flush();
			br.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
