package org.zframework.web.controller.admin.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.web.support.ControllerCommon;
import org.zframework.core.web.support.WebResult;
import org.zframework.orm.query.PageBean;
import org.zframework.web.controller.BaseController;
import org.zframework.web.entity.system.Response;
import org.zframework.web.service.admin.system.LogService;
import org.zframework.web.service.admin.system.ResponseService;

@Controller
@RequestMapping("/meet/responseController")
public class ResponseController extends BaseController<Response>{
	@Autowired
	private ResponseService responseservice;
	@Autowired
	private LogService logService;	
	Log log  = LogFactory.getLog("ResponseController");
	
	@RequestMapping(method={RequestMethod.GET})
	public String index(){
		return "admin/system/response/index";
  }
  
	/**
	 * 读取数据，在页面显示数据
	 * @param pageBean
	 * @param name
	 * @param value
	 * @return
	 */
	
	@RequestMapping(value="/CalendarList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object calendarList(PageBean pageBean,String name,String value){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		System.out.println("into controller proList");
		//List<Response> responsedataList = responseservice.getproList(pageBean, name, value);
		//dataMap.put("rows", responsedataList);
	//	dataMap.put("total", pageBean.getTotalCount());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		
		try {
			Object  [] oarr = {"6147","你好啊",sdf.parse("1460617200000").getTime(),sdf.parse("1460617200000").getTime(),0,0,1,0,1,"",""};
			return oarr;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	
	
	
	@RequestMapping(value="/responsedataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> proList(PageBean pageBean,String name,String value){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		System.out.println("into controller proList");
		List<Response> responsedataList = responseservice.getproList(pageBean, name, value);
		dataMap.put("rows", responsedataList);
		dataMap.put("total", pageBean.getTotalCount());
		return dataMap;
	}
	
	@RequestMapping(value="/responseList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray showResponseList(){
		JSONArray json=new JSONArray();
		List<Response> list=responseservice.getResponsedata();
		for(Response pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getName());
			json.add(jNode);
		}
		log.error("json"+json);		
		return json;		
	}
	
	
	
	/**
	 * 转向增加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET})
	public String toAdd(){
		return "admin/system/response/add";
	}
	
	/**
	 * 保存数据
	 * @param request 用于记录日志
	 * @param comm 数据字典对象
	 * @param result 
	 * @return
	 */
	@RequestMapping(value="/doAdd",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject doAdd(HttpServletRequest request,@Valid @ModelAttribute("pro")Response pro,BindingResult result){
		log.error("into response doAdd from log.debug!!!");
		if(result.hasErrors()){			
			logService.recordInfo("增加数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
			return WebResult.error("请按要求填写表单!");
		}else{
			return responseservice.executeAdd(request, pro,getCurrentUser());
		}	
	}
	
	
	/***
	 * 删除方法
	 * @param request
	 * @param comm 
	 * @param result
	 * @return
	 * */
	@RequestMapping(value="/doDelete",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject doDelete(HttpServletRequest request,@RequestParam Integer[] ids){
		log.error("into ResponseController doDelete");
		JSONObject jResult = new JSONObject();
		if(!isAllowAccess()){
			jResult = WebResult.NeedVerifyPassword();
		}else{
			return responseservice.executeDelete(request, ids, jResult,this.getCurrentUser());
		}
		return jResult;
	}
	
	
	
	/**
	 * 转向编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method={RequestMethod.GET})
	public String toEdit(Model model,@PathVariable Integer id){
		
		Response response = responseservice.getPro(id);
		
		if(!ObjectUtil.isNull(response)){
			model.addAttribute("pro",response);
			return "admin/system/response/edit";
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
	public JSONObject doEdit(HttpServletRequest request,@Valid @ModelAttribute("client")Response client,BindingResult result){
		log.error("into clientController doEdit");
		JSONObject jResult = new JSONObject();
		if(result.hasErrors()){
			jResult = WebResult.error("请按要求填写表单");
			logService.recordInfo("编辑数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
		}else{
			return responseservice.executeEdit(request, client,this.getCurrentUser());
		}
		return jResult;
	}
	
	
	@RequestMapping(value="/doInsert",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject getJsonObject(HttpServletRequest request){
		//JSONObject jResult = new JSONObject();		
			return responseservice.saveJsonObj(request);
		
	}
}
