package org.zframework.web.controller.admin.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/meet/uploadController")
public class UploadController {
	
	@RequestMapping(method = { RequestMethod.GET })
	public String index(Model model) {
		model.addAttribute("content", "hello model");
		return "admin/system/updown/index";
	}
	
	@RequestMapping(value = "/uploaddo", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String upload(HttpServletRequest request, HttpServletResponse response) {
 		   //转换请求request	
		MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
		//接收那么=uploadFile的文件
		MultipartFile multipartFile = multipartHttpservletRequest
				.getFile("uploadFile");
		String originalFileName = multipartFile.getOriginalFilename();// getOriginalFilename()

		int charAt = originalFileName.indexOf(".");
		String fileExtName = originalFileName.substring(charAt,
				originalFileName.length());
		String fileName = originalFileName.substring(0, charAt);
		
		String saveFileName = makeFileName(fileName);
		String savePath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/upload");
		
		
		String realSavePath = makePath(saveFileName, savePath);
		// 创建目录
		File file = new File(realSavePath);
		if (!file.exists()) {
			file.mkdir();
		}
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file + "/"
					+ saveFileName + fileExtName);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			
			request.getSession().setAttribute("pListenerOFF", "success");
			
		}		
		System.out.println("return success");
		return realSavePath;
	}
	
	
	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:fantianming
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}
	
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * 
	 * @Method: makePath
	 * @Description:
	 * @Anthor:fantianming
	 * 
	 * @param filename
	 *            文件名，要根据文件名生成存储目录
	 * @param savePath
	 *            文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
															// upload\3\5
		// File既可以代表文件也可以代表目录 File file = new File(dir);
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}
}
