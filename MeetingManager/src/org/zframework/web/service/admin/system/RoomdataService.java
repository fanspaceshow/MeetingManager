package org.zframework.web.service.admin.system;

import org.zframework.web.entity.system.Factorydata;
import org.zframework.web.entity.system.Roomdata;
import org.zframework.web.service.BaseService;
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

import org.zframework.web.entity.system.User;
@Service
public class RoomdataService extends BaseService<Roomdata>  {
	@Autowired
	private LogService logService;
	Log log  = LogFactory.getLog("RoomdataService");
	/**
	 * 初始化数据字典
	 * 将数据库中的数据项加载到系统内存中
	 */
	public void InitPros(){
		//获取数据库中所有的数据项
		List<Roomdata> list = list();
		ApplicationCommon.SYSCOMMONS.clear();
		if(ObjectUtil.isNotNull(list)){
			for(Roomdata roomdata : list){
				ApplicationCommon.SYSCOMMONS.put(roomdata.getName(), roomdata.getName());
			}
		}
}
	
	/**
	 * 获取项目类型的list
	 */
	public  List<Roomdata> getRoomdata(){
		//获取数据库中所有的数据项
		List<Roomdata> list = list();
		return list;
	}	
	
	/**
	 * 分页显示Roomdatacompany
	 * @param pageBean
	 * */
	public List<Roomdata> getproList(PageBean pageBean, String name, String value) {
		if(!StringUtil.isEmpty(name)){
			if("id".equals(name)){
				if(RegexUtil.isInteger(value)){
					 
					pageBean.addCriterion(Restrictions.idEq(Integer.parseInt(value)));
				}
			}else{
				
				pageBean.addCriterion(Restrictions.like(name, value+"%"));
			}
		}
		List<Roomdata> proList=this.listByPage(pageBean);
		return proList;
	}
	
	/***
	 * 执行增加
	 * @param request
	 * @param comm
	 * @param result
	 * @param user
	 * */
	public JSONObject executeAdd(HttpServletRequest request, Roomdata pro,User user) {
		Roomdata proByKey=this.getByProperties("name",pro.getName());
		if(ObjectUtil.isNotNull(proByKey)){
			logService.recordInfo("新增数据字典","失败(标识名称已经存在)", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error("标识已经存在!");
		}else{
			save(pro);
			//更新缓存
			if(ObjectUtil.isNull(ApplicationCommon.SYSCOMMONS.get(pro.getName()))){
				ApplicationCommon.SYSCOMMONS.put(pro.getName(),pro.getRoomcapacity());
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
		List<Roomdata> list=this.getByIds(ids);
		for(int i=0;i<list.size();i++){
			Roomdata pro=list.get(i);
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
	public Roomdata getPro(Integer id) {
		Roomdata Roomdata =this.getById(id);
		return Roomdata;
	}
	
	
	/**
	 * 确认编辑
	 * */
	public JSONObject executeEdit(HttpServletRequest request, Roomdata roomdata,User user) {
		log.error("into RoomdataService executeEdit");
		Roomdata oldRoomdata =this.getById(roomdata.getId());
		Roomdata RoomdataKey =this.getByProperties("name", roomdata.getName());//根据传入的Key查看是否存在
		if(ObjectUtil.isNotNull(oldRoomdata)){
			//判断是否修改
			if(ObjectUtil.equalProperty(roomdata, oldRoomdata, new String[]{"id","num","name","addr","timeperiod","dateperiod","roomcapacity","remark"})){
				return WebResult.NoChange();
			}//判断标识是否存在
			else if(RoomdataKey!=null&&RoomdataKey.getId()!=roomdata.getId()&&RoomdataKey.getName().equals(roomdata.getName())){
				logService.recordInfo("编辑数据字典","失败（重复标识）", user.getLoginName(), request.getRemoteAddr());
				return WebResult.error("标识已经存在，请重新输入");
			}else{//修改
				oldRoomdata.setName(roomdata.getName());
				oldRoomdata.setNum(roomdata.getNum());				
				oldRoomdata.setAddr(roomdata.getAddr());
				oldRoomdata.setTimeperiod(roomdata.getTimeperiod());
				oldRoomdata.setDateperiod(roomdata.getDateperiod());
				oldRoomdata.setRoomcapacity(roomdata.getRoomcapacity());
				oldRoomdata.setRemark(roomdata.getRemark());
				update(oldRoomdata);
				//更新缓存
				if(ObjectUtil.isNotNull(ApplicationCommon.SYSCOMMONS.get(roomdata.getName()))){
					ApplicationCommon.SYSCOMMONS.remove(roomdata.getName());
					ApplicationCommon.SYSCOMMONS.put(roomdata.getName(),roomdata.getName());
				}
				logService.recordInfo("编辑数据字典","成功", user.getLoginName(), request.getRemoteAddr());
				return WebResult.success();
			}
		}else{
			logService.recordInfo("编辑数据字典","失败（尝试编辑不存在的数据字典）", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error(ControllerCommon.UNAUTHORIZED_ACCESS);
		}
	} 
}