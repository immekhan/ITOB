package com.bwa.persistence.model;

/**
 * Created by Hello Hassnain on 07/05/2017.
 */

import com.bwa.persistence.model.common.CompositeIdEntry;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ITOB_ROLE_PRIVILEGES")
public class RolePrivilege
        extends CompositeIdEntry<RolePrivilege.RolePrivilegePk>
{
    private static final long serialVersionUID = 1L;

    @Deprecated
    protected RolePrivilege() {}

    @Deprecated
    public RolePrivilege(RolePrivilegePk id)
    {
        super(id);
    }

    public String getRole()
    {
        return ((RolePrivilegePk)getId()).getRole();
    }

    public boolean isSetRole()
    {
        return ((RolePrivilegePk)getId()).isSetRole();
    }

    public String getPrivilege()
    {
        return ((RolePrivilegePk)getId()).getPrivilege();
    }

    public boolean isSetPrivilege()
    {
        return ((RolePrivilegePk)getId()).isSetPrivilege();
    }

    @Embeddable
    public static final class RolePrivilegePk
            implements Serializable
    {
        private static final long serialVersionUID = 1L;
        @Basic(optional=false)
        @Column(name="ID_ROLE", nullable=false)
        private String role;
        @Basic(optional=false)
        @Column(name="ID_PRIVILEGE", nullable=false)
        private String privilege;

        @Deprecated
        protected RolePrivilegePk() {}

        public RolePrivilegePk(String role, String privilege)
        {
            this.role = role;
            this.privilege = privilege;
        }

        public boolean equals(Object obj)
        {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            RolePrivilegePk other = (RolePrivilegePk)obj;
            if (this.privilege == null)
            {
                if (other.privilege != null) {
                    return false;
                }
            }
            else if (!this.privilege.equals(other.privilege)) {
                return false;
            }
            if (this.role == null)
            {
                if (other.role != null) {
                    return false;
                }
            }
            else if (!this.role.equals(other.role)) {
                return false;
            }
            return true;
        }

        public int hashCode()
        {
            int prime = 31;
            int result = 1;
            result = 31 * result + (this.privilege == null ? 0 : this.privilege.hashCode());

            result = 31 * result + (this.role == null ? 0 : this.role.hashCode());
            return result;
        }

        public String toString()
        {
            StringBuffer sb = new StringBuffer();
            sb.append('(');
            sb.append(this.role);
            sb.append(',');
            sb.append(this.privilege);
            sb.append(')');
            return sb.toString();
        }

        public String getRole()
        {
            return this.role;
        }

        public boolean isSetRole()
        {
            return this.role != null;
        }

        public String getPrivilege()
        {
            return this.privilege;
        }

        public boolean isSetPrivilege()
        {
            return this.privilege != null;
        }
    }
}

