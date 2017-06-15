package com.bwa.controllers;

import com.bwa.endpoint.IUserEndpoint;
import com.bwa.util.AppUtils;
import com.bwa.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hassnain on 08/02/2017.
 */

@Controller
public class HomeController {

    @Autowired
    private IUserEndpoint userEndpoint;

    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);


    @RequestMapping(value = "/getUserCountJsonResponse", method = { RequestMethod.GET }, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public String homePageJson() {
        String response = null;
        try {
            logger.info("Called getTotalUserCount");
            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = AppUtils.convertToJson(totalDepartments);
            logger.info(response);
        } catch (Exception e) {
            logger.error("Exception Occured:" + e);
        }
        return response;
    }

    @RequestMapping(value = "/getUserCount", method = { RequestMethod.GET }, produces = Constant.APPLICATION_PLAIN_TXT)
    @ResponseBody
    public String getDepartCount(HttpServletRequest request) {
        String response = null;
        try {
            logger.info("Called getUserCount");
            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = Long.toString(totalDepartments);
            logger.info(response);
        } catch (Exception e) {
            logger.error("Exception Occured:" + e);
        }
        return response;
    }

    @RequestMapping(value = "/deleteUserById/{id}", method = { RequestMethod.GET }, produces = Constant.APPLICATION_PLAIN_TXT)
    @ResponseBody
    public String deleteUserById(@PathVariable String id) {
        String response = null;
        try {
            logger.info("Called getUserCount");
            boolean isUserDeleted= userEndpoint.deleteUserById(Long.parseLong(id));

            response = isUserDeleted?"User is deleted successfully":"Unable to delete user";
            logger.info(response);
        } catch (Exception e) {
            logger.error("Exception Occured:" + e);
        }
        return response;
    }


    @CrossOrigin
    @RequestMapping(value = "/getMenu", method = { RequestMethod.GET }, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public Object getMenu() {
        String response = null;
        String [] menuArray={"ABC.html","ABC1.html"};
        try {
            logger.info("Called getMene");
//            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = AppUtils.convertToJson(menuArray);
            logger.info(response);
        } catch (Exception e) {
            logger.error("Exception Occured:" + e);
        }
        return response;
    }
}
