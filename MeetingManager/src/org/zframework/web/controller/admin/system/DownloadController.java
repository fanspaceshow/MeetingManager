package org.zframework.web.controller.admin.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class DownloadController {
	@RequestMapping(value = "/downloaddo", method = { RequestMethod.GET,
			RequestMethod.POST })
	public static void download(HttpServletRequest request,
			HttpServletResponse response,String downloadPath) {
		try {
			
			// 得到要下载的文件名
			String fileName = request.getParameter("filename"); // 23239283-92489-阿凡达.avi
			
			fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
			
			// 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		/*	String fileSaveRootPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload");*/
			
			// 通过文件名找出文件的所在目录
			String path = downloadPath;
			
			// 得到要下载的文件
			File file = new File(path);// + "\\" + fileName
			// 如果文件不存在
			if (!file.exists()) {
				
				// request.setAttribute("message", "您要下载的资源已被删除！！");
				// request.getRequestDispatcher("/message.jsp").forward(request,
				// response);
				return;
			}
			
			// 处理文件名
			String realname = fileName.substring(fileName.indexOf("_") + 1);
			// 设置响应头，控制浏览器下载该文件
			// response.setHeader("content-disposition", "attachment;filename="
			// + URLEncoder.encode(realname, "UTF-8"));
			// 解决下载时中文是URL编码的问题
			response.setHeader("content-disposition", "attachment;filename="
					+ new String(realname.getBytes("gb2312"), "ISO8859-1"));
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(path);// + "\\" + fileName
			// 创建输出流
			OutputStream out = response.getOutputStream();
			
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
			
		} catch (IOException o) {
			o.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
