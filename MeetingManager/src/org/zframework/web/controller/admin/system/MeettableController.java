package org.zframework.web.controller.admin.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.web.support.ControllerCommon;
import org.zframework.core.web.support.WebResult;
import org.zframework.orm.query.PageBean;
import org.zframework.web.controller.BaseController;
import org.zframework.web.entity.system.Meettable;
import org.zframework.web.service.admin.system.DownloadService;
import org.zframework.web.service.admin.system.LogService;
import org.zframework.web.service.admin.system.MeettableService;
import org.zframework.web.service.admin.system.UploadService;

@Controller
@RequestMapping("/meet/roomarrController")
public class MeettableController extends BaseController<Meettable>{
	@Autowired
	private MeettableService roomarrservice;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private LogService logService;
	Log log  = LogFactory.getLog("MeettableController");
	@RequestMapping(method={RequestMethod.GET})
	public String index(){
		return "admin/system/meettable/index";
  }
  
	@RequestMapping(value="/getajax",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject testAjax(){
		return WebResult.success();
	}
	
	
	/**
	 * 读取数据，在页面显示数据
	 * @param pageBean
	 * @param name
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/meetarrList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> proList(PageBean pageBean,String name,String value){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		List<Meettable> meetarrList = roomarrservice.getproList(pageBean, name, value);
		dataMap.put("rows", meetarrList);
		dataMap.put("total", pageBean.getTotalCount());
		return dataMap;
	}
	
	/**
	 * 转向增加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET})
	public String toAdd(){
		return "admin/system/meettable/add";
	}
/**	@RequestMapping(value="/test",method={RequestMethod.GET})
	public String toAdds(){
		return "admin/system/meettable/test";
	}*/
	
	/**
	 * 保存数据
	 * @param request 用于记录日志
	 * @param comm 数据字典对象
	 * @param result 
	 * @return
	 */
	@RequestMapping(value="/doAdd",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject doAdd(HttpServletRequest request,@Valid @ModelAttribute("pro")Meettable pro,BindingResult result){
		log.error("into MeetArr doAdd from log.debug!!!");
		if(result.hasErrors()){			
			logService.recordInfo("增加数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
			return WebResult.error("请按要求填写表单!");
		}else{
			return roomarrservice.executeAdd(request, pro,getCurrentUser());
		}	
	}
	
	
	@RequestMapping(value="/gettopiclist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray gettopiclist(){
		JSONArray json=new JSONArray();
		List<Meettable> list=roomarrservice.getMeetArr();
		for(Meettable pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getTopic());
			json.add(jNode);
		}			
		return json;
	}
	
	
	@RequestMapping(value="/getreserverlist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray getreserverlist(){
		JSONArray json=new JSONArray();
		List<Meettable> list=roomarrservice.getMeetArr();
		for(Meettable pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getReserver());
			json.add(jNode);
		}	
		return json;
	}
	
	
	/**
	 * 转向编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit/{id}/{condition}",method={RequestMethod.GET})
	public String toEdit(Model model,@PathVariable Integer id,@PathVariable String condition){
		Meettable meettable = roomarrservice.getPro(id);		
		if(!ObjectUtil.isNull(meettable)){
			model.addAttribute("pro",meettable);
			if ("0".equals(condition)) {
				return "admin/system/meettable/edit";
			}else{
				return "admin/system/meettable/showdata";
			}
			
		}else{
			return ControllerCommon.UNAUTHORIZED_ACCESS;
		}		
	}
	
	
	
	/***
	 * 确认编辑字典
	 * @param request
	 * @param unit 
	 * @param result
	 * @return
	 * */
	@RequestMapping(value="/doEdit",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject doEdit(HttpServletRequest request,@Valid @ModelAttribute("meettable")Meettable meettable,BindingResult result){
		log.error("into MeettableController doEdit");
		JSONObject jResult = new JSONObject();
		if(result.hasErrors()){
			jResult = WebResult.error("请按要求填写表单");
			logService.recordInfo("编辑数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
		}else{
			return roomarrservice.executeEdit(request, meettable,this.getCurrentUser());
		}
		return jResult;
	}
	
	@RequestMapping(value="/uploadindex",method={RequestMethod.GET,RequestMethod.POST})
	public String uploadindex(){
		return "admin/system/updown/index";		
	}
	
	/**
	 * meettable 附件上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploaddo",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request, HttpServletResponse response) {
		String realPath = uploadService.upload(request, response);
		JSONObject jNode = new JSONObject();
		jNode.element("path", realPath);
		System.out.println(realPath);
		return jNode;
	/*	Meettable meettable = new Meettable();
		meettable.setAttachmentpath(realPath);
		return roomarrservice.executeEdit(request, meettable, this.getCurrentUser());*/
	}
	
	public void downloadFile(){
		
	}
}
