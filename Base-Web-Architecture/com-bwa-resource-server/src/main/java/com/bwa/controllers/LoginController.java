package com.bwa.controllers;

import com.bwa.business.ILoginLogic;
import com.bwa.business.IUtilityLogic;
import com.bwa.controllers.response.LoginResponse;
import com.bwa.controllers.response.MenuResponse;
import com.bwa.controllers.response.SignUpResponse;
import com.bwa.controllers.response.Status;
import com.bwa.exceptions.CustomException;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Menu;
import com.bwa.persistence.model.SubMenu;
import com.bwa.persistence.model.SubMenuItem;
import com.bwa.util.AppUtils;
import com.bwa.util.CodeConstants;
import com.bwa.util.Constants;
import com.bwa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private ILoginLogic loginLogic;

    @Autowired
    private LoginControllerValidation loginControllerValidation;

    @Autowired
    private IUtilityLogic utilityLogic;

    @CrossOrigin
    @RequestMapping(value = "/signUp", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String singUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("emailId") String emailId,
                         @RequestParam("mobileNo") String mobileNo,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("rpassword") String rTpassword,
                         @RequestParam("idOrgUnit") String idOrgUnit) {

        LOG.info("Entered in singUp");

        SignUpResponse response=new SignUpResponse();
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

        LOG.info("Exiting from singUp");
        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String login(HttpServletRequest request,
                        @RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("idOrgUnit") String idOrgUnit) {

        LOG.info("Entered in login");
        LoginResponse response=new LoginResponse();
        String validationResponse =null;
        try {
            validationResponse=loginControllerValidation.validateLoginRequest(userName,password,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Customer customer=loginLogic.login(userName.toLowerCase(),password);

                Status status=new Status();
                if (customer!=null) {

                    status.setCode(CodeConstants.CODE_SUCCESS);
                    status.setMsg(CodeConstants.CODE_SUCCESS_MSG);
                    response.setRole(customer.getCustomerType().getStrRole());//todo remove role from bean
                    response.setCustomerId(customer.getId());//todo remove role from bean

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

        LOG.info("Exiting From login");
        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/getNavMenu" , method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String fetchNavMenu( @RequestParam("idRole") String idRole,
                              @RequestParam(value = "customerId" ) Long customerId,
                              @RequestParam("idOrgUnit") String idOrgUnit) {

        LOG.info("Entered in fetchNavMenu");
        MenuResponse response=new MenuResponse();
        String validationResponse =null;
        try {
            validationResponse=loginControllerValidation.validateFetchNavMenuRequest(idRole,customerId,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                List<String> privilegeList =null;
                List<Menu> navMenus=null;
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

                List<MenuResponse.MenuBean> menuBeans=new ArrayList<MenuResponse.MenuBean>();

                if(navMenus!=null) {

                    for (Menu menu : navMenus) {

                        MenuResponse.MenuBean menuBean = response.new MenuBean();
                        menuBean.setIdMenu(menu.getId());
                        menuBean.setTitle(menu.getTitle());
                        menuBean.setPrivilege(menu.getPrivilege());
                        menuBean.setMenuOrder(menu.getMenuOrder().intValue());
                        menuBean.setFileWithPath(menu.getFileWithPath());
                        menuBean.setIsPage(menu.isPage());
//                                menuBean.setMenu(menu);


                        List<MenuResponse.MenuBean.SubMenuBean> subMenuBeans = new ArrayList<MenuResponse.MenuBean.SubMenuBean>();
                        if(navSubMenus!=null) {
                            for (SubMenu subMenu : navSubMenus) {

                                if (subMenu.getMenu().equals(menu.getId())) {
                                    MenuResponse.MenuBean.SubMenuBean subMenuBean = menuBean.new SubMenuBean();
                                    subMenuBean.setIdSubMenu(subMenu.getId());
                                    subMenuBean.setIdMenu(subMenu.getMenu());
                                    subMenuBean.setTitle(subMenu.getTitle());
                                    subMenuBean.setPrivilege(subMenu.getPrivilege());
                                    subMenuBean.setMenuOrder(subMenu.getSubMenuOrder());
                                    subMenuBean.setFileWithPath(subMenu.getFileWithPath());
                                    subMenuBean.setIsMenu(subMenu.isMenu());

//                                        subMenuBean.setSubMenu(subMenu);

                                    List<MenuResponse.MenuBean.SubMenuBean.SubMenuItemBean> subMenuItems = new ArrayList<MenuResponse.MenuBean.SubMenuBean.SubMenuItemBean>();
                                    if(navSubMenusItem!=null) {
                                        for (SubMenuItem subMenuItem : navSubMenusItem) {

                                            if (subMenuItem.getSubMenu().equals(subMenu.getId())) {

                                                MenuResponse.MenuBean.SubMenuBean.SubMenuItemBean subMenuItemBean = subMenuBean.new SubMenuItemBean();
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
                response.setMenuBean(menuBeans);

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

        LOG.info("Exiting from getNavMenu");
        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = { RequestMethod.POST}, produces = Constants.APPLICATION_JSON)
    @ResponseBody
    public String logout(){

        return "";
    }

}
