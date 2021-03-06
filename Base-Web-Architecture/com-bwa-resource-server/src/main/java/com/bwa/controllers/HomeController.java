package com.bwa.controllers;

import com.bwa.endpoint.IUserEndpoint;
import com.bwa.util.AppUtils;
import com.bwa.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private IUserEndpoint userEndpoint;

    private static final Logger LOG = Logger.getLogger(HomeController.class);


    @RequestMapping(value = "/getUserCountJsonResponse", method = { RequestMethod.GET }, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String homePageJson() {
        String response = null;
        try {
            LOG.info("Called getTotalUserCount");
            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = AppUtils.convertToJson(totalDepartments);
            LOG.info(response);
        } catch (Exception e) {
            LOG.error("Exception Occured:" + e);
        }
        return response;
    }

    @RequestMapping(value = "/getUserCount", method = { RequestMethod.GET }, produces = Constants.APPLICATION_PLAIN_TXT)
    @ResponseBody
    public String getDepartCount(HttpServletRequest request) {
        String response = null;
        try {
            LOG.info("Called getUserCount");
            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = Long.toString(totalDepartments);
            LOG.info(response);
        } catch (Exception e) {
            LOG.error("Exception Occured:" + e);
        }
        return response;
    }

    @RequestMapping(value = "/deleteUserById/{id}", method = { RequestMethod.GET }, produces = Constants.APPLICATION_PLAIN_TXT)
    @ResponseBody
    public String deleteUserById(@PathVariable String id) {
        String response = null;
        try {
            LOG.info("Called getUserCount");
            boolean isUserDeleted= userEndpoint.deleteUserById(Long.parseLong(id));

            response = isUserDeleted?"User is deleted successfully":"Unable to delete user";
            LOG.info(response);
        } catch (Exception e) {
            LOG.error("Exception Occured:" + e);
        }
        return response;
    }


    @CrossOrigin
    @RequestMapping(value = "/getMenu", method = { RequestMethod.GET }, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public Object getMenu() {
        String response = null;
        String [] menuArray={"ABC.html","ABC1.html"};
        try {
            LOG.info("Called getMene");
//            Long totalDepartments = userEndpoint.getTotalUserCount();
            response = AppUtils.convertToJson(menuArray);
            LOG.info(response);
        } catch (Exception e) {
            LOG.error("Exception Occured:" + e);
        }
        return response;
    }
}
