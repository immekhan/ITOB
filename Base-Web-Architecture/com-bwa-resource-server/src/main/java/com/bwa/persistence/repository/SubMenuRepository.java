package com.bwa.persistence.repository;

import com.bwa.persistence.model.Menu;
import com.bwa.persistence.model.SubMenu;
import com.bwa.persistence.repository.custom.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubMenuRepository extends BaseRepository<SubMenu,String> {

    @Query("select sm from SubMenu sm where sm.privilege in :privilegeList order by sm.menu asc,sm.subMenuOrder asc")
    List<SubMenu> findByPrivilege(@Param("privilegeList") List<String> privilegeList);

}
