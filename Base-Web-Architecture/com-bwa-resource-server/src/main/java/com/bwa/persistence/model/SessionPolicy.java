package com.bwa.persistence.model;


import com.bwa.persistence.model.common.GeneratedIdEntry;

import javax.persistence.*;

@Entity
@Table(name="ITOB_SESSION_POLICIES")
@AttributeOverride(name="id", column=@Column(name="ID_SESSION_POLICY"))
public class SessionPolicy
        extends GeneratedIdEntry
{
    private static final long serialVersionUID = 1L;
    @Basic(optional=false)
    @Column(name="STR_SESSION_POLICY", nullable=false, length=80)
    private String name;
    @Basic(optional=false)
    @Column(name="INT_SESSION_TIMEOUT_SECONDS", nullable=false)
    private Integer sessionTimeoutSeconds = Integer.valueOf(0);
    @Basic(optional=false)
    @Column(name="INT_MAX_SESSIONS_ALLOWED", nullable=false)
    private Integer maxSessionsAllowed;
    @Basic(optional=false)
    @Column(name="BOL_EXT_SESSION_ON_ACT", nullable=false)
    private Character dbExtendSessionOnActivity = Character.valueOf('Y');
    @Basic(optional=false)
    @Column(name="BOL_KILL_OLDEST_WHEN_LIMIT_HIT", nullable=false)
    private Character dbKillOldestWhenLimitHit = Character.valueOf('Y');
    @Basic(optional=false)
    @Column(name="BOL_RECYCLE_WHEN_LIMIT_HIT", nullable=false)
    private Character dbRecycleWhenLimitHit = Character.valueOf('Y');

    public int getSessionTimeoutSeconds()
    {
        return this.sessionTimeoutSeconds.intValue();
    }

    public boolean isSetSessionTimeoutSeconds()
    {
        return this.sessionTimeoutSeconds != null;
    }

    public void setSessionTimeoutSeconds(int sessionTimeoutSeconds)
    {
        this.sessionTimeoutSeconds = Integer.valueOf(sessionTimeoutSeconds);
    }

    public int getMaxSessionsAllowed()
    {
        return this.maxSessionsAllowed.intValue();
    }

    public boolean isSetMaxSessionsAllowed()
    {
        return this.maxSessionsAllowed != null;
    }

    public void setMaxSessionsAllowed(int maxSessionsAllowed)
    {
        this.maxSessionsAllowed = Integer.valueOf(maxSessionsAllowed);
    }

    public boolean isExtendSessionOnActivity()
    {
        return this.dbExtendSessionOnActivity.equals(Character.valueOf('Y'));
    }

    public boolean isSetExtendSessionOnActivity()
    {
        return this.dbExtendSessionOnActivity != null;
    }

    public void setExtendSessionOnActivity(boolean extendSessionOnActivity)
    {
        this.dbExtendSessionOnActivity = Character.valueOf(extendSessionOnActivity ? 'Y' : 'N');
    }

    public boolean isKillOldestWhenLimitHit()
    {
        return this.dbKillOldestWhenLimitHit.equals(Character.valueOf('Y'));
    }

    public boolean isSetKillOldestWhenLimitHit()
    {
        return this.dbKillOldestWhenLimitHit != null;
    }

    public void setKillOldestWhenLimitHit(boolean killOldestWhenLimitHit)
    {
        this.dbKillOldestWhenLimitHit = Character.valueOf(killOldestWhenLimitHit ? 'Y' : 'N');
    }

    public boolean isRecycleWhenLimitHit()
    {
        return this.dbRecycleWhenLimitHit.equals(Character.valueOf('Y'));
    }

    public boolean isSetRecycleWhenLimitHit()
    {
        return this.dbRecycleWhenLimitHit != null;
    }

    public void setRecycleWhenLimitHit(boolean recycleWhenLimitHit)
    {
        this.dbRecycleWhenLimitHit = Character.valueOf(recycleWhenLimitHit ? 'Y' : 'N');
    }

    public boolean isSetName()
    {
        return this.name != null;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}

