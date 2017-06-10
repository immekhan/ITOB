package com.bwa.controllers.response;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse {

    private Status status;
    private List<MenuBean> menuBean;

    public MenuResponse(){

    }

    public class MenuBean{

        // menu;
        private String idMenu;
        private String privilege;
        private String title;
        private String fileWithPath;
        private int menuOrder;
        private boolean isPage;

        public List<SubMenuBean> subMenus;

        public class SubMenuBean{

            private String idSubMenu;
            private String idMenu;
            private String title;
            private String privilege;
            private String fileWithPath;
            private int menuOrder;
            private boolean isMenu;


            public List<SubMenuItemBean> subMenuItems;

            //class SubMenuItemBean starts
            public class SubMenuItemBean{

                private String idSubMenuItem;
                private String idSubMenu;
                private String title;
                private String privilege;
                private String fileWithPath;
                private int menuOrder;

                public String getIdSubMenuItem() {
                    return idSubMenuItem;
                }

                public void setIdSubMenuItem(String idSubMenuItem) {
                    this.idSubMenuItem = idSubMenuItem;
                }

                public String getIdSubMenu() {
                    return idSubMenu;
                }

                public void setIdSubMenu(String idSubMenu) {
                    this.idSubMenu = idSubMenu;
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

                public int getMenuOrder() {
                    return menuOrder;
                }

                public void setMenuOrder(int menuOrder) {
                    this.menuOrder = menuOrder;
                }
            }
            //class SubMenuItemBean ends


            public String getIdSubMenu() {
                return idSubMenu;
            }

            public void setIdSubMenu(String idSubMenu) {
                this.idSubMenu = idSubMenu;
            }

            public String getIdMenu() {
                return idMenu;
            }

            public void setIdMenu(String idMenu) {
                this.idMenu = idMenu;
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

            public int getMenuOrder() {
                return menuOrder;
            }

            public void setMenuOrder(int menuOrder) {
                this.menuOrder = menuOrder;
            }

            public boolean isMenu() {
                return isMenu;
            }

            public void setIsMenu(boolean menu) {
                isMenu = menu;
            }

            public List<SubMenuItemBean> getSubMenuItems() {
                return subMenuItems;
            }

            public void setSubMenuItems(List<SubMenuItemBean> subMenuItems) {
                this.subMenuItems = subMenuItems;
            }
        }
        //class SubMenuBean ends


        public String getIdMenu() {
            return idMenu;
        }

        public void setIdMenu(String idMenu) {
            this.idMenu = idMenu;
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

        public String getFileWithPath() {
            return fileWithPath;
        }

        public void setFileWithPath(String fileWithPath) {
            this.fileWithPath = fileWithPath;
        }

        public int getMenuOrder() {
            return menuOrder;
        }

        public void setMenuOrder(int menuOrder) {
            this.menuOrder = menuOrder;
        }

        public boolean isPage() {
            return isPage;
        }

        public void setIsPage(boolean page) {
            isPage = page;
        }

        public List<SubMenuBean> getSubMenus() {
            return subMenus;
        }

        public void setSubMenus(List<SubMenuBean> subMenus) {
            this.subMenus = subMenus;
        }
    }
    public MenuResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public List<MenuResponse.MenuBean> getMenuBean() {
        if(menuBean ==null){
            new ArrayList<MenuResponse.MenuBean>();
        }
        return menuBean;
    }

    public void setMenuBean(List<MenuResponse.MenuBean> menuBean) {
        this.menuBean = menuBean;
    }
}
