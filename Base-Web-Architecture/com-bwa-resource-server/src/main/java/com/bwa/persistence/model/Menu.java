package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ITOB_MENUS")
@AttributeOverride(name="id", column=@Column(name="ID_MENU", length=80))
public class Menu extends IdEntry<String> {

    @Basic(optional = false)
    @Column(name="ID_PRIVILEGE", nullable=false)
    private String privilege;

    @Basic(optional = false)
    @Column(name="STR_MENU_TITLE", nullable = false)
    private String title;

    @Basic(optional = false)
    @Column(name="BOL_IS_PAGE" , nullable = false)
    private Character isPage;

    @Basic
    @Column(name="STR_FILE",length = 100)
    private String fileWithPath;

    @Basic(optional = false)
    @Column(name = "INT_MENU_ORDER", nullable = false)
    private Integer menuOrder;

    public Menu(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public Menu(){
        this.setCreationDate(new Date());
    }

    public boolean isSetPrivilege() {
        return privilege != null;
    }

    public boolean isSetTitle() {
        return title != null;
    }

    public boolean isSetIsPage() {
        return isPage != null;
    }

    public boolean isSetFileWithPath() {
        return fileWithPath != null;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPage() {
        return isPage.equals(Character.valueOf('Y'));
    }

    public void setIsPage(boolean isPage) {
        this.isPage =  Character.valueOf(isPage ? 'Y' : 'N');
    }

    public String getFileWithPath() {
        return fileWithPath;
    }

    public void setFileWithPath(String fileWithPath) {
        this.fileWithPath = fileWithPath;
    }

    public boolean isSetMenuOrder() {
        return menuOrder != null;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
}
