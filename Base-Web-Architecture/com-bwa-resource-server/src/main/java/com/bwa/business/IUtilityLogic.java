package com.bwa.business;

import com.bwa.persistence.model.Menu;
import com.bwa.persistence.model.OrgUnit;
import com.bwa.persistence.model.SubMenu;
import com.bwa.persistence.model.SubMenuItem;

import java.util.List;

public interface IUtilityLogic {

    /**
     * @param code
     * @param params
     * @return exception message fetching from DB and formatting
     */
    String fetchExceptionMsg(int code, Object[] params);

    /**
     * @param idOrgUnit
     * @return OrgUnit Object after fetching from DB based on ID
     */
    OrgUnit fetchOrgUnit(String idOrgUnit);

    /**
     * @param idRole
     * @return all the privileges which have been assigned to a specific role
     */
    List<String> fetchPrivilegesByRole(String idRole);

    /**
     * @param privilegesList
     * @return
     */
    List<Menu> fetchMenusByRole(List<String> privilegesList);

    /**
     * @param privilegesList
     * @return all the sub menus in s menu assigned with specific privileges
     */
    List<SubMenu> fetchSubMenusByPrivileges(List<String> privilegesList);

    /**
     * @param privilegesList
     * @return
     */
    List<SubMenuItem> fetchSubMenusItemByPrivileges(List<String> privilegesList);
}
