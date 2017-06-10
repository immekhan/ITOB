package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;

@Entity
@Table(name="ITOB_SUB_MENU_ITEMS")
@AttributeOverride(name="id", column=@Column(name="ID_SUB_MENU_ITEM", length=80))
public class SubMenuItem extends IdEntry<String> {

    /*@ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_SUB_MENU", nullable=false)
    private SubMenu subMenu;*/

    @Basic(optional = false)
    @Column(name="ID_SUB_MENU", nullable = false)
    private String subMenu;

    @Basic(optional = false)
    @Column(name="STR_TITLE", nullable = false)
    private String title;

    @Basic(optional = false)
    @Column(name="ID_PRIVILEGE", nullable=false)
    private String privilege;

    @Basic
    @Column(name="STR_FILE",length = 100)
    private String fileWithPath;

    @Basic(optional = false)
    @Column(name = "INT_MENU_ORDER", nullable = false)
    private Integer itemOrder;

    public boolean isSetSubMenu() {
        return subMenu != null;
    }

    public boolean isSetTitle() {
        return title != null;
    }

    public boolean isSetPrivilege() {
        return privilege != null;
    }

    public boolean isSetFileWithPath() {
        return fileWithPath != null;
    }

    public boolean isSetItemOrder() {
        return itemOrder != null;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
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

    public String getFileWithPath() {
        return fileWithPath;
    }

    public void setFileWithPath(String fileWithPath) {
        this.fileWithPath = fileWithPath;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }
}
