package com.bwa.persistence.repository;

import com.bwa.persistence.model.Menu;
import com.bwa.persistence.repository.custom.MenuRepositoryCustom;

public interface MenuRepository extends BaseRepository<Menu,String>, MenuRepositoryCustom{

}
