package org.zframework.web.controller.admin.system;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.web.controller.BaseController;
import org.zframework.web.entity.system.Meetfreq;
import org.zframework.web.service.admin.system.MeetfreqService;
@Controller
@RequestMapping("/meet/meetfreqController")
public class MeetfreqController extends BaseController<Meetfreq>{
	@Autowired
	private MeetfreqService meetfreqservice;
	Log log  = LogFactory.getLog("MeetfreqController");
	
	@RequestMapping(value="/showtypelist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray showtypelist(){
		JSONArray json=new JSONArray();
		List<Meetfreq> list=meetfreqservice.getMeetfreq();
		for(Meetfreq pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getMeetfreq());
			json.add(jNode);
		}
		log.error("json"+json);		
		return json;
	}
}
