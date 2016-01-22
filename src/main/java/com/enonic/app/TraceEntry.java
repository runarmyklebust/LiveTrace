package com.enonic.app;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.enonic.xp.portal.PortalRequest;
import com.enonic.xp.portal.PortalRequestAccessor;

public class TraceEntry
{
    private TraceEntryId id;

    private boolean finished = false;

    private PortalRequest portalRequest;

    private HttpServletRequest request;

    private final long startTime;

    private final Instant requestTime;

    private Long finishedTime;

    private String site;

    private String content;

    public TraceEntry( final HttpServletRequest request )
    {
        this.startTime = System.currentTimeMillis();
        this.request = request;
        this.id = new TraceEntryId();
        this.requestTime = Instant.now();
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

        this.portalRequest = PortalRequestAccessor.get( this.request );

        if ( this.portalRequest != null )
        {
            this.site = this.portalRequest.getSite() != null ? this.portalRequest.getSite().getPath().toString() : "N/A";
            this.content = this.portalRequest.getContent() != null ? this.portalRequest.getContent().getPath().toString() : "N/A";
        }

        this.finished = true;
    }

    public long getTime()
    {
        if ( this.finishedTime != null )
        {
            return this.finishedTime - this.startTime;
        }

        return System.currentTimeMillis() - startTime;
    }

    public Instant getRequestTime()
    {
        return requestTime;
    }

    public String getUrl()
    {
        return this.request.getRequestURI();
    }

    public String getContent()
    {
        return this.content;
    }

    public String getSite()
    {
        return this.site;
    }

}
