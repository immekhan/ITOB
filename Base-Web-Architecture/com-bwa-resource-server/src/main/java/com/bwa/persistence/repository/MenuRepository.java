package com.bwa.persistence.repository;

import com.bwa.persistence.model.Menu;
import com.bwa.persistence.repository.custom.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu,String>, MenuRepositoryCustom{

    @Query("select m from Menu m where m.privilege in :idPrivileges order by m.menuOrder asc")
    List<Menu> findByPrivilege(@Param("idPrivileges") List<String> idPrivileges);

}
