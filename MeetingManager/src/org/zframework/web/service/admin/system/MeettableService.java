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
import org.zframework.web.entity.system.User;
import org.zframework.web.service.BaseService;

@Service
public class MeettableService extends BaseService<Meettable> {
	@Autowired
	private LogService logService;
	Log log = LogFactory.getLog("RoomdataService");


	/**
	 * 获取项目类型的list
	 */
	public List<Meettable> getMeetArr() {
		// 获取数据库中所有的数据项
		List<Meettable> list = list();
		return list;
	}

	/**
	 * 初始化数据字典 将数据库中的数据项加载到系统内存中
	 */
	public void InitPros() {
		// 获取数据库中所有的数据项
		List<Meettable> list = list();
		ApplicationCommon.SYSCOMMONS.clear();
		if (ObjectUtil.isNotNull(list)) {
			for (Meettable roomarr : list) {
				ApplicationCommon.SYSCOMMONS.put(roomarr.getTopic(),
						roomarr.getTopic());
			}
		}
	}

	/**
	 * 分页显示MeetArr
	 * 
	 * @param pageBean
	 * */
	public List<Meettable> getproList(PageBean pageBean, String name, String value) {
		if (!StringUtil.isEmpty(name)) {
			if ("id".equals(name)) {
				if (RegexUtil.isInteger(value)) {

					pageBean.addCriterion(Restrictions.idEq(Integer
							.parseInt(value)));
				}
			} else {

				pageBean.addCriterion(Restrictions.like(name, value + "%"));
			}
		}
		List<Meettable> proList = this.listByPage(pageBean);
		return proList;
	}

	/***
	 * 执行增加
	 * 
	 * @param request
	 * @param comm
	 * @param result
	 * @param user
	 * */
	public JSONObject executeAdd(HttpServletRequest request, Meettable meetarr,
			User user) {
		Meettable meetarrKey = this.getByProperties("topic", meetarr.getTopic());
		if (ObjectUtil.isNotNull(meetarrKey)) {
			logService.recordInfo("新增数据字典", "失败(标识名称已经存在)",
					user.getLoginName(), request.getRemoteAddr());
			return WebResult.error("标识已经存在!");
		} else {
			save(meetarr);
			meetarrKey = this.getByProperties("topic", meetarr.getTopic());
			request.getSession().setAttribute("meetarr",meetarrKey);
			// 更新缓存
			if (ObjectUtil.isNull(ApplicationCommon.SYSCOMMONS.get(meetarr
					.getTopic()))) {
				ApplicationCommon.SYSCOMMONS.put(meetarr.getTopic(),
						meetarr.getTopic());
			}
			logService.recordInfo("新增数据字典", "成功", user.getLoginName(),
					request.getRemoteAddr());			
			return WebResult.success();
		}
	}

	
	/**
	 * 根据id获取
	 * */
	public Meettable getPro(Integer id) {
		Meettable meettable =this.getById(id);
		return meettable;
	}
	
	
	/**
	 * 确认编辑
	 * */
	public JSONObject executeEdit(HttpServletRequest request, Meettable meettable,User user) {
		System.out.println("into MeettableService executeEdit");
		Meettable oldmeettable =this.getById(meettable.getId());

		if(ObjectUtil.isNotNull(oldmeettable)){
			oldmeettable.setMeetrecord(meettable.getMeetrecord());
			oldmeettable.setAttachmentpath(meettable.getAttachmentpath());
				update(oldmeettable);				
				return WebResult.success();			
		}else{
			logService.recordInfo("编辑数据字典","失败（尝试编辑不存在的数据字典）", user.getLoginName(), request.getRemoteAddr());
			return WebResult.error(ControllerCommon.UNAUTHORIZED_ACCESS);
		}
	}
}