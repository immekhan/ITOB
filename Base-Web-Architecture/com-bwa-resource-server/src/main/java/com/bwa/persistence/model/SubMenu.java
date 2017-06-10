package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;

@Entity
@Table(name="ITOB_SUB_MENUS")
@AttributeOverride(name="id", column=@Column(name="ID_SUB_MENU", length=80))
public class SubMenu extends IdEntry<String> {

    /*@ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_MENU", nullable=false)
    private Menu menu;*/
    @Basic(optional = false)
    @Column(name="ID_MENU", nullable = false)
    private String menu;

    @Basic(optional = false)
    @Column(name="STR_SUB_MENU_TITLE", nullable = false)
    private String title;

    @Basic(optional = false)
    @Column(name="ID_PRIVILEGE", nullable=false)
    private String privilege;

    @Basic(optional = false)
    @Column(name="BOL_IS_MENU" , nullable = false)
    private Character isMenu;

    @Basic
    @Column(name="STR_FILE",length = 100)
    private String fileWithPath;

    @Basic(optional = false)
    @Column(name = "INT_MENU_ORDER", nullable = false)
    private Integer subMenuOrder;

    public boolean isSetMenu() {
        return menu != null;
    }

    public boolean isSetTitle() {
        return title != null;
    }

    public boolean isSetPrivilege() {
        return privilege != null;
    }

    public boolean isSetIsMenu() {
        return isMenu != null;
    }

    public boolean isSetFileWithPath() {
        return fileWithPath != null;
    }

    public boolean isSetSubMenuOrder() {
        return subMenuOrder != null;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public boolean isMenu() {
        return isMenu.equals(Character.valueOf('Y'));
    }

    public void setIsMenu(boolean isMenu) {
        this.isMenu = Character.valueOf(isMenu ? 'Y' : 'N');
    }

    public String getFileWithPath() {
        return fileWithPath;
    }

    public void setFileWithPath(String fileWithPath) {
        this.fileWithPath = fileWithPath;
    }

    public Integer getSubMenuOrder() {
        return subMenuOrder;
    }

    public void setSubMenuOrder(Integer subMenuOrder) {
        this.subMenuOrder = subMenuOrder;
    }
}
