package org.zframework.web.controller.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.zframework.core.web.listener.mProgressListener;

@Controller()
@SessionAttributes("status")
@RequestMapping("/meet/progressController")
public class ProgressController {

	@SuppressWarnings("null")
	@RequestMapping(value = "/upfile/progress", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject progress(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		System.out.println("progress : "+session.getId());
		mProgressListener pListener = (mProgressListener) session
				.getAttribute("pListener");
		// setMessage(pListener.getpCount());自己写的方法
		// Ajax(response);自己写的方法
		JSONObject obj = new JSONObject();
		if (pListener == null) {
			// Random rd = new Random();Math.floor(Math.random() * 10)
			obj.element("fileprogress", "0");
			return obj;
		} else{
			if (session.getAttribute("pListenerOFF") == "success"){
				obj.element("fileprogress", "success");
				return obj;
			}else {
				System.out.println("pListener : "+pListener.toString());
				System.out.println("plis "+pListener.getpCount());
				obj.element("fileprogress", pListener.getpCount());
				return obj;
			}			
		}		
	}
}
