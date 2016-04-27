package org.zframework.web.controller.admin.system;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.web.controller.BaseController;
import org.zframework.web.entity.system.Meettype;
import org.zframework.web.service.admin.system.MeettypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
@RequestMapping("/meet/meettypeController")
public class MeettypeController extends BaseController<Meettype>{
	@Autowired
	private MeettypeService meettypeservice;
	Log log  = LogFactory.getLog("MeettypeController");
	
	@RequestMapping(value="/showtypelist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray showtypelist(){
		JSONArray json=new JSONArray();
		List<Meettype> list=meettypeservice.getMeettype();
		for(Meettype pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getMeettype());
			json.add(jNode);
		}
		log.error("json"+json);		
		return json;
	}
}
