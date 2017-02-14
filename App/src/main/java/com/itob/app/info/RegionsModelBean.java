package com.itob.app.info;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Hassnan.Ali on 2/14/2017.
 */
@Entity
@Table(name = "REGIONS")
public class RegionsModelBean {
    private long regionId;
    private String regionName;
    private Collection<CountriesModelBean> countriesByRegionId;

    @Id
    @Column(name = "REGION_ID")
    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "REGION_NAME")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionsModelBean that = (RegionsModelBean) o;

        if (regionId != that.regionId) return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (regionId ^ (regionId >>> 32));
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "regionsByRegionId")
    public Collection<CountriesModelBean> getCountriesByRegionId() {
        return countriesByRegionId;
    }

    public void setCountriesByRegionId(Collection<CountriesModelBean> countriesByRegionId) {
        this.countriesByRegionId = countriesByRegionId;
    }
}
