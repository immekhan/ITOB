package com.bwa.persistence.repository;

import com.bwa.persistence.model.SubMenu;
import com.bwa.persistence.model.SubMenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubMenuItemRepository extends BaseRepository<SubMenuItem,String> {

    @Query("select smi from SubMenuItem smi where smi.privilege in :privilegeList order by smi.subMenu asc, smi.itemOrder asc ")
    List<SubMenuItem> findByPrivilege(@Param("privilegeList") List<String> privilegeList);

}
