package com.itob.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itob.app.business.BusinessImpl;
import com.itob.app.info.BusinessResponse;
import com.itob.app.utils.AppUtils;
import com.itob.app.utils.Constant;

/**
 * @author imran.khan
 * @Description Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private BusinessImpl businessImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/home", method = { RequestMethod.GET }, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String homePageJson() {
		String response = null;
		try {
			logger.info("Called HomePageForJsonResponse");
			BusinessResponse businessResponse = businessImpl.getData();
			response = AppUtils.convertToJson(businessResponse);
			logger.info(response);
		} catch (Exception e) {
			logger.error("Exception Occured:" + e);
		}
		return response;
	}

}
