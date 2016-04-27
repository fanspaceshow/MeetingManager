package org.zframework.web.controller.admin.system;
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

import org.zframework.web.entity.system.Roomdata;
import org.zframework.web.service.admin.system.LogService;
import org.zframework.web.service.admin.system.RoomdataService;
@Controller
@RequestMapping("/meet/roomController")
public class RoomdataController extends BaseController<Roomdata>{
	@Autowired
	private RoomdataService roomdataService;
	@Autowired
	private LogService logService;
	
	Log log  = LogFactory.getLog("RoomdataController");
	
	@RequestMapping(method={RequestMethod.GET})
	public String index(){
		return "admin/system/roomdata/index";
  }
  
	/**
	 * 读取数据，在页面显示数据
	 * @param pageBean
	 * @param name
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/roomdataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> proList(PageBean pageBean,String name,String value){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		List<Roomdata> roomdataList = roomdataService.getproList(pageBean, name, value);
		dataMap.put("rows", roomdataList);
		dataMap.put("total", pageBean.getTotalCount());
		return dataMap;
	}
	
	
	@RequestMapping(value="/roomnumList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray showRoomdataList(){
		JSONArray json=new JSONArray();
		List<Roomdata> list=roomdataService.getRoomdata();
		for(Roomdata pro:list){
			JSONObject jNode = new JSONObject();
			jNode.element("id", pro.getId());
			jNode.element("text", pro.getNum());
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
		return "admin/system/roomdata/add";
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
	public JSONObject doAdd(HttpServletRequest request,@Valid @ModelAttribute("pro")Roomdata pro,BindingResult result){
		log.error("into roomdata doAdd from log.debug!!!");
		if(result.hasErrors()){			
			logService.recordInfo("增加数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
			return WebResult.error("请按要求填写表单!");
		}else{
			return roomdataService.executeAdd(request, pro,getCurrentUser());
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
		
		JSONObject jResult = new JSONObject();
		if(!isAllowAccess()){
			jResult = WebResult.NeedVerifyPassword();
		}else{
			return roomdataService.executeDelete(request, ids, jResult,this.getCurrentUser());
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
		Roomdata roomdata = roomdataService.getPro(id);		
		if(!ObjectUtil.isNull(roomdata)){
			model.addAttribute("pro",roomdata);
			return "admin/system/roomdata/edit";
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
	public JSONObject doEdit(HttpServletRequest request,@Valid @ModelAttribute("roomdata")Roomdata roomdata,BindingResult result){
		
		JSONObject jResult = new JSONObject();
		if(result.hasErrors()){
			jResult = WebResult.error("请按要求填写表单");
			logService.recordInfo("编辑数据字典","失败（未按要求填写表单）", getCurrentUser().getLoginName(), request.getRemoteAddr());
		}else{
			return roomdataService.executeEdit(request, roomdata,this.getCurrentUser());
		}
		return jResult;
	}
}