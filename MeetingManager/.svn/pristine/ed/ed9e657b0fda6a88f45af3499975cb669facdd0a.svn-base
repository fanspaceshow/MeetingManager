package org.zframework.web.service.admin.system;

import org.zframework.web.entity.system.Roomresource;

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
public class RoomresourceService extends BaseService<Roomresource>  {
	@Autowired
	private LogService logService;
	Log log  = LogFactory.getLog("RoomresourceService");
	/**
	 * 初始化数据字典
	 * 将数据库中的数据项加载到系统内存中
	 */
	public void InitPros(){
		//获取数据库中所有的数据项
		List<Roomresource> list = list();
		ApplicationCommon.SYSCOMMONS.clear();
		if(ObjectUtil.isNotNull(list)){
			for(Roomresource roomresource : list){
				ApplicationCommon.SYSCOMMONS.put(roomresource.getName(), roomresource.getName());
			}
		}
}
	/**
	 * 分页显示Roomresourcecompany
	 * @param pageBean
	 * */
	public List<Roomresource> getproList(PageBean pageBean, String name, String value) {
		if(!StringUtil.isEmpty(name)){
			if("id".equals(name)){
				if(RegexUtil.isInteger(value)){
					 
					pageBean.addCriterion(Restrictions.idEq(Integer.parseInt(value)));
				}
			}else{
				
				pageBean.addCriterion(Restrictions.like(name, value+"%"));
			}
		}
		List<Roomresource> proList=this.listByPage(pageBean);
		return proList;
	}
	
	/***
	 * 执行增加
	 * @param request
	 * @param comm
	 * @param result
	 * @param user
	 * */
	public JSONObject executeAdd(HttpServletRequest request, Roomresource pro,User user) {
		Roomresource proByKey=this.getByProperties("name",pro.getName());
		if(ObjectUtil.isNotNull(proByKey)){
			logService.recordInfo("新增数据字典","失败(标识名称已经存在)", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error("标识已经存在!");
		}else{
			save(pro);
			//更新缓存
			if(ObjectUtil.isNull(ApplicationCommon.SYSCOMMONS.get(pro.getName()))){
				ApplicationCommon.SYSCOMMONS.put(pro.getName(),pro.getAddr());
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
		List<Roomresource> list=this.getByIds(ids);
		for(int i=0;i<list.size();i++){
			Roomresource pro=list.get(i);
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
	public Roomresource getPro(Integer id) {
		Roomresource Roomresource =this.getById(id);
		return Roomresource;
	}
	
	
	/**
	 * 确认编辑
	 * */
	public JSONObject executeEdit(HttpServletRequest request, Roomresource roomresource,User user) {
		log.error("into RoomresourceService executeEdit");
		Roomresource oldroomresource =this.getById(roomresource.getId());
		Roomresource roomresourceKey =this.getByProperties("name", roomresource.getName());//根据传入的Key查看是否存在
		if(ObjectUtil.isNotNull(oldroomresource)){
			//判断是否修改
			if(ObjectUtil.equalProperty(roomresource, oldroomresource, new String[]{"id","num","name","addr","devicetype","remark"})){
				return WebResult.NoChange();
			}//判断标识是否存在
			else if(roomresourceKey!=null&&roomresourceKey.getId()!=roomresource.getId()&&roomresourceKey.getName().equals(roomresource.getName())){
				logService.recordInfo("编辑数据字典","失败（重复标识）", user.getLoginName(), request.getRemoteAddr());
				return WebResult.error("标识已经存在，请重新输入");
			}else{//修改
				oldroomresource.setName(roomresource.getName());
				oldroomresource.setNum(roomresource.getNum());				
				oldroomresource.setAddr(roomresource.getAddr());
				oldroomresource.setDevicetype(roomresource.getDevicetype());			
				oldroomresource.setRemark(roomresource.getRemark());
				update(oldroomresource);
				//更新缓存
				if(ObjectUtil.isNotNull(ApplicationCommon.SYSCOMMONS.get(roomresource.getName()))){
					ApplicationCommon.SYSCOMMONS.remove(roomresource.getName());
					ApplicationCommon.SYSCOMMONS.put(roomresource.getName(),roomresource.getName());
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