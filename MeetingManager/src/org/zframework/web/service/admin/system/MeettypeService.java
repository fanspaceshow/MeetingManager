package org.zframework.web.service.admin.system;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zframework.web.entity.system.Meettype;
import org.zframework.web.service.BaseService;
@Service
public class MeettypeService extends BaseService<Meettype>{
	/**
	 * 获取项目类型的list
	 */
	public  List<Meettype> getMeettype(){
		//获取数据库中所有的数据项
		List<Meettype> list = list();
		return list;
	}
}
