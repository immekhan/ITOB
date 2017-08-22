package com.bwa.controllers;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.ILoginLogic;
import com.bwa.business.IUtilityLogic;
import com.bwa.controllers.response.ResponseObject;
import com.bwa.controllers.response.bean.Menu;
import com.bwa.controllers.response.bean.Status;
import com.bwa.exceptions.CustomException;
import com.bwa.exceptions.LoginException;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.SubMenu;
import com.bwa.persistence.model.SubMenuItem;
import com.bwa.util.AppUtils;
import com.bwa.util.CodeConstants;
import com.bwa.util.Constants;
import com.bwa.util.ControllerUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @Autowired private ILoginLogic loginLogic;
    @Autowired private LoginControllerValidation loginControllerValidation;
    @Autowired private IUtilityLogic utilityLogic;
    @Autowired private ICustomerLogic customerLogic;


    @CrossOrigin
    @RequestMapping(value = "/signUp", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String singUp(HttpServletRequest request,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("emailId") String emailId,
                         @RequestParam("mobileNo") String mobileNo,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("rpassword") String rTpassword,
                         @RequestParam("idOrgUnit") String idOrgUnit) {

        ResponseObject response=new ResponseObject();
        String validationResponse="";
        try {

            validationResponse=loginControllerValidation.validateSignUpRequest(firstName,lastName,emailId
                    ,mobileNo,userName,password,rTpassword,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Long customerId = loginLogic.signUp(1l, "01", userName,
                        mobileNo, firstName, lastName, emailId, password);

                Status status=new Status();
                if (customerId != null) {
                    status.setCode(CodeConstants.CODE_SUCCESS);
                    status.setMsg(CodeConstants.CODE_SUCCESS_MSG);
                }else{
                    status.setCode(Integer.toString(CodeConstants.ERROR_CODE_SIGN_UP_FAILED));
                }
                response.setStatus(status);
            }

        }catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_SIGN_UP_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }

        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/hasLoggedIn", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String hasLoggedIn(HttpServletRequest request) {

        ResponseObject response=new ResponseObject();
        String validationResponse =null;

        try{

            String userName = (String) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_KEY_USERNAME);
            String idOrgUnit = (String) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID);
            //validate logged in user
            validationResponse=loginControllerValidation.validateLoginRequest(userName,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Customer customer = customerLogic.fetchCustomerByUserId(userName,idOrgUnit);
                if(customer!=null){
                    request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID,customer.getId());
                    request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ROLE_ID,customer.getCustomerType().getStrRole());
                }else {
                    throw new LoginException(utilityLogic
                            .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_DOES_NOT_EXIST,new Object[]{}));
                }

                Status status=new Status();
                status.setCode(CodeConstants.CODE_SUCCESS);
                status.setMsg(CodeConstants.CODE_SUCCESS_MSG);

                response.setStatus(status);
            }

        } catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }

        return AppUtils.convertToJson(response);
    }
//    @Transactional(propagation = Propagation.SUPPORTS)
    @CrossOrigin
    @RequestMapping(value = "/login", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String login(HttpServletRequest request,
                        @RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("idOrgUnit") String idOrgUnit) {

        ResponseObject response=new ResponseObject();
        String validationResponse =null;
        try {
            validationResponse=loginControllerValidation.validateLoginRequest(userName,password,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Customer customer=loginLogic.login(userName.toLowerCase(),password);

                Status status=new Status();
                if (customer!=null) {

                    status.setCode(CodeConstants.CODE_SUCCESS);
                    status.setMsg(CodeConstants.CODE_SUCCESS_MSG);

                    request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID,customer.getId());
                    request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID,idOrgUnit);
                    request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ROLE_ID,customer.getCustomerType().getStrRole());

                }else{
                    status.setCode(Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED));
                }
                response.setStatus(status);
            }

        } catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }
        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/fetchNavBar" , method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String fetchNavMenu(HttpServletRequest request) {

        Long customerId = (Long) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID);
        String idOrgUnit = (String) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID);
        String idRole = (String) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_KEY_ROLE_ID);

        ResponseObject response=new ResponseObject();
        Menu menus=new Menu();
        String validationResponse =null;
        try {
            validationResponse=loginControllerValidation.validateFetchNavMenuRequest(idRole,customerId,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                List<String> privilegeList =null;
                List<com.bwa.persistence.model.Menu> navMenus=null;
                List<SubMenu> navSubMenus=null;
                List<SubMenuItem> navSubMenusItem=null;

                privilegeList=utilityLogic.fetchPrivilegesByRole(idRole);

                if(privilegeList!=null){

                    navMenus=utilityLogic.fetchMenusByRole(privilegeList);

                    if(navMenus!=null){
                        navSubMenus=utilityLogic.fetchSubMenusByPrivileges(privilegeList);

                        if(navSubMenus!=null){
                            navSubMenusItem=utilityLogic.fetchSubMenusItemByPrivileges(privilegeList);

                        }
                    }

                }

                List<Menu.MenuBean> menuBeans=new ArrayList<Menu.MenuBean>();

                if(navMenus!=null) {

                    for (com.bwa.persistence.model.Menu menu : navMenus) {

                        Menu.MenuBean menuBean = menus.new MenuBean();
                        menuBean.setIdMenu(menu.getId());
                        menuBean.setTitle(menu.getTitle());
                        menuBean.setPrivilege(menu.getPrivilege());
                        menuBean.setMenuOrder(menu.getMenuOrder().intValue());
                        menuBean.setFileWithPath(menu.getFileWithPath());
                        menuBean.setIsPage(menu.isPage());
//                                menuBean.setMenu(menu);


                        List<Menu.MenuBean.SubMenuBean> subMenuBeans = new ArrayList<Menu.MenuBean.SubMenuBean>();
                        if(navSubMenus!=null) {
                            for (SubMenu subMenu : navSubMenus) {

                                if (subMenu.getMenu().equals(menu.getId())) {
                                    Menu.MenuBean.SubMenuBean subMenuBean = menuBean.new SubMenuBean();
                                    subMenuBean.setIdSubMenu(subMenu.getId());
                                    subMenuBean.setIdMenu(subMenu.getMenu());
                                    subMenuBean.setTitle(subMenu.getTitle());
                                    subMenuBean.setPrivilege(subMenu.getPrivilege());
                                    subMenuBean.setMenuOrder(subMenu.getSubMenuOrder());
                                    subMenuBean.setFileWithPath(subMenu.getFileWithPath());
                                    subMenuBean.setIsMenu(subMenu.isMenu());

//                                        subMenuBean.setSubMenu(subMenu);

                                    List<Menu.MenuBean.SubMenuBean.SubMenuItemBean> subMenuItems = new ArrayList<Menu.MenuBean.SubMenuBean.SubMenuItemBean>();
                                    if(navSubMenusItem!=null) {
                                        for (SubMenuItem subMenuItem : navSubMenusItem) {

                                            if (subMenuItem.getSubMenu().equals(subMenu.getId())) {

                                                Menu.MenuBean.SubMenuBean.SubMenuItemBean subMenuItemBean = subMenuBean.new SubMenuItemBean();
                                                subMenuItemBean.setIdSubMenuItem(subMenuItem.getId());
                                                subMenuItemBean.setIdSubMenu(subMenuItem.getSubMenu());
                                                subMenuItemBean.setTitle(subMenuItem.getTitle());
                                                subMenuItemBean.setPrivilege(subMenuItem.getPrivilege());
                                                subMenuItemBean.setMenuOrder(subMenuItem.getItemOrder());
                                                subMenuItemBean.setFileWithPath(subMenuItem.getFileWithPath());

                                                subMenuItems.add(subMenuItemBean);
                                            }
                                        }
                                    }
                                    subMenuBean.setSubMenuItems(subMenuItems);

                                    subMenuBeans.add(subMenuBean);
                                }
                                menuBean.setSubMenus(subMenuBeans);
                            }
                        }
                        menuBeans.add(menuBean);
                    }
                }
                menus.setMenuBean(menuBeans);

                Status status=new Status();
                status.setCode(CodeConstants.CODE_SUCCESS);
                status.setMsg(CodeConstants.CODE_SUCCESS_MSG);

                response.setData(menus);
                response.setStatus(status);
            }

        } catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }

        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String logout(){

        return "";
    }

}
