package com.bwa.persistence.model;

import com.bwa.persistence.model.common.UpdatableDbEntry;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hello Hassnain on 07/05/2017.
 */
@Entity
@Table(name="ITOB_CITY")
public class City extends UpdatableDbEntry {


    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "ID_CITY_CODE", length = 5)
    private String cityCode;

    @Id
    @Basic(optional = false)
    @Column(name = "STR_CITY_NAME", length = 100)
    private String cityName;

    @Basic(optional = true)
    @Column(name = "ID_REGION_CODE" , length = 1)
    private Character regionCode;

    public City(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public City(){
        this.setCreationDate(new Date());
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public boolean isSetCityCode(){
        return this.cityCode != null;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isCityName(){
        return this.cityName != null;
    }

    public Character getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Character idRegionCode) {
        this.regionCode = idRegionCode;
    }

    public boolean isSetRegionCode(){
        return regionCode != null;
    }
}
