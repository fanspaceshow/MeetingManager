package org.zframework.web.service.admin.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.util.RegexUtil;
import org.zframework.core.util.StringUtil;
import org.zframework.core.web.support.ControllerCommon;
import org.zframework.core.web.support.WebResult;
import org.zframework.orm.query.PageBean;
import org.zframework.web.entity.system.Meettable;
import org.zframework.web.entity.system.Response;
import org.zframework.web.entity.system.User;
import org.zframework.web.service.BaseService;

@Service
public class CalendarService extends BaseService<Response>{
	@Autowired
	private LogService logService;
	Log log  = LogFactory.getLog("CalendarService");
	
	/**
	 * 初始化数据字典
	 * 将数据库中的数据项加载到系统内存中
	 */
	public void InitPros(){
		//获取数据库中所有的数据项
		List<Response> list = list();
		ApplicationCommon.SYSCOMMONS.clear();
		if(ObjectUtil.isNotNull(list)){
			for(Response response : list){
				ApplicationCommon.SYSCOMMONS.put(response.getName(), response.getName());
			}
		}
}
		
	/**
	 * 获取项目类型的list
	 */
	public  List<Response> getResponsedata(){
		//获取数据库中所有的数据项
		List<Response> list = list();
		return list;
	}
	
	/**
	 * 分页显示Factorydatacompany
	 * @param pageBean
	 * */
	public List<Response> getproList(PageBean pageBean, String name, String value) {
		System.out.println("into service proList");
		if(!StringUtil.isEmpty(name)){
			if("id".equals(name)){
				if(RegexUtil.isInteger(value)){
					 
					pageBean.addCriterion(Restrictions.idEq(Integer.parseInt(value)));
				}
			}else{				
				pageBean.addCriterion(Restrictions.like(name, value+"%"));
			}
		}
		List<Response> proList=this.listByPage(pageBean);
		System.out.println(proList.get(0).getConfirm());
		return proList;
	}
	
	
	
	/***
	 * 执行增加
	 * @param request
	 * @param comm
	 * @param result
	 * @param user
	 * */
	public JSONObject executeAdd(HttpServletRequest request, Response pro,User user) {
		Response proByKey=this.getByProperties("id",pro.getId());
		if(ObjectUtil.isNotNull(proByKey)){
			logService.recordInfo("新增数据字典","失败(标识名称已经存在)", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error("标识已经存在!");
		}else{
			save(pro);
			//更新缓存
			if(ObjectUtil.isNull(ApplicationCommon.SYSCOMMONS.get(pro.getName()))){
				ApplicationCommon.SYSCOMMONS.put(pro.getName(),pro.getReason());
			}
			logService.recordInfo("新增数据字典","成功", user.getLoginName(), request.getRemoteAddr());
			return WebResult.success();
		}
	}
	
	
	/**
	 * 删除操作
	 * */
	public JSONObject executeDelete(HttpServletRequest request, Integer[] ids,
			JSONObject jResult,User user) {
		List<Response> list=this.getByIds(ids);
		for(int i=0;i<list.size();i++){
			Response pro=list.get(i);
				//更新缓存
				if(ObjectUtil.isNotNull(ApplicationCommon.SYSCOMMONS.get(pro.getName()))){
					ApplicationCommon.SYSCOMMONS.remove(pro.getName());
				}		
		}
		this.delete(ids);
		logService.recordInfo("删除数据库字典", "成功",user.getLoginName(), request.getRemoteAddr());
		return WebResult.success();
	}
	
	
	/**
	 * 根据id获取
	 * */
	public Response getPro(Integer id) {
		Response response =this.getById(id);
		return response;
	}
	
	
	/**
	 * 确认编辑
	 * */
	public JSONObject executeEdit(HttpServletRequest request, Response response,User user) {
		log.error("into FactorydataService executeEdit");
		Response oldresponse =this.getById(response.getId());
		Response responseKey =this.getByProperties("name", response.getName());//根据传入的Key查看是否存在
		if(ObjectUtil.isNotNull(oldresponse)){
			//判断是否修改
			if(ObjectUtil.equalProperty(response, oldresponse, new String[]{"id","name","confirm","reason","timechanged"})){
				return WebResult.NoChange();
			}//判断标识是否存在
			else if(responseKey!=null&&responseKey.getId()!=response.getId()&&responseKey.getName().equals(response.getName())){
				logService.recordInfo("编辑数据字典","失败（重复标识）", user.getLoginName(), request.getRemoteAddr());
				return WebResult.error("标识已经存在，请重新输入");
			}else{//修改				
				oldresponse.setConfirm(response.getConfirm());
				oldresponse.setReason(response.getReason());
				oldresponse.setTimechanged(response.getTimechanged());
				update(oldresponse);
				//更新缓存
				if(ObjectUtil.isNotNull(ApplicationCommon.SYSCOMMONS.get(response.getName()))){
					ApplicationCommon.SYSCOMMONS.remove(response.getName());
					ApplicationCommon.SYSCOMMONS.put(response.getName(),response.getName());
				}
				logService.recordInfo("编辑数据字典","成功", user.getLoginName(), request.getRemoteAddr());
				return WebResult.success();
			}
		}else{
			logService.recordInfo("编辑数据字典","失败（尝试编辑不存在的数据字典）", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error(ControllerCommon.UNAUTHORIZED_ACCESS);
		}
	} 
	
	//根据页面发送过来的受邀人员名单创建应答记录
	public JSONObject saveJsonObj(HttpServletRequest request) {
		System.out.println("into saveJsonObj service");
		//1.从session中取出meetarr对象
		//2.根据受邀人数new response对象并赋值		
				//3.存入reponse对象并保存
		Response res =null;
		Meettable meetArr = (Meettable) request.getSession().getAttribute("meetarr");
//		System.out.println(meetArr.toString());
		if (ObjectUtil.isNotNull(meetArr)) {
			String[] invitedName = meetArr.getInvited().split(",");
			for (int i = 0; i < invitedName.length; i++) {
				res = new Response();
				res.setMeetarrid(meetArr.getId());
				res.setTitle(meetArr.getTopic());
				res.setName(invitedName[i]);			
			    save(res);
			    res = null;
			    System.gc();
			}		
		}else {
			return WebResult.error("对象为空");
		}
		System.out.println("outof saveJsonObj service");
		return WebResult.success();
	}
	
	
	
	
}
