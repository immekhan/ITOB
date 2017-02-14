package com.itob.app.info;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Hassnan.Ali on 2/14/2017.
 */
@Entity
@Table(name = "COUNTRIES")
public class CountriesModelBean {
    private String countryId;
    private String countryName;
    private Long regionId;
    private RegionsModelBean regionsByRegionId;
    private Collection<LocationsModelBean> locationssByCountryId;

    @Id
    @Column(name = "COUNTRY_ID")
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "COUNTRY_NAME")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Basic
    @Column(name = "REGION_ID")
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesModelBean that = (CountriesModelBean) o;

        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null) return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryId != null ? countryId.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "REGION_ID", referencedColumnName = "REGION_ID")
    public RegionsModelBean getRegionsByRegionId() {
        return regionsByRegionId;
    }

    public void setRegionsByRegionId(RegionsModelBean regionsByRegionId) {
        this.regionsByRegionId = regionsByRegionId;
    }

    @OneToMany(mappedBy = "countriesByCountryId")
    public Collection<LocationsModelBean> getLocationssByCountryId() {
        return locationssByCountryId;
    }

    public void setLocationssByCountryId(Collection<LocationsModelBean> locationssByCountryId) {
        this.locationssByCountryId = locationssByCountryId;
    }
}
