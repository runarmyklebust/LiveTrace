package com.enonic.app;

import javax.servlet.http.HttpServletRequest;

import com.enonic.xp.portal.PortalRequest;
import com.enonic.xp.portal.PortalRequestAccessor;

public class TraceEntry
{
    private TraceEntryId id;

    private boolean finished;

    private PortalRequest portalRequest;

    private HttpServletRequest request;

    private final long startTime;

    private Long finishedTime;

    public TraceEntry( final HttpServletRequest request )
    {
        this.startTime = System.currentTimeMillis();
        this.request = request;
    }

    public TraceEntryId getId()
    {
        return id;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void finished()
    {
        this.finishedTime = System.currentTimeMillis();
        this.portalRequest = PortalRequestAccessor.get();
    }

    public long getTime()
    {
        if ( this.finishedTime != null )
        {
            return this.finishedTime - this.startTime;
        }

        return System.currentTimeMillis() - startTime;
    }
}
