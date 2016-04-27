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
import org.zframework.web.entity.system.Response;
import org.zframework.web.service.admin.system.CalendarService;
import org.zframework.web.service.admin.system.LogService;
import org.zframework.web.service.admin.system.ResponseService;

@Controller
@RequestMapping("/meet/calendarController")
public class CalendarController extends BaseController<Response>{
	@Autowired
	private CalendarService calendarservice;
	@Autowired
	private LogService logService;	
	Log log  = LogFactory.getLog("CalendarController");
	
	@RequestMapping(method={RequestMethod.GET})
	public String index(){
		
		
		JSONArray testList = new JSONArray();
	//	testList = ["6147","你好啊","new Date("+1460617200000+")","new Date("+1460620800000+")",0,0,1,0,1,"",""]
		return "admin/system/calendar/index";
  }
  
}
