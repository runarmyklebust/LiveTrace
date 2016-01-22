package com.enonic.app;

import javax.servlet.http.HttpServletRequest;

public class TraceEntry
{
    private TraceEntryId id;

    private boolean finished;

    private final long startTime;

    private long finishedTime;

    public TraceEntry( final HttpServletRequest request )
    {
        this.startTime = System.currentTimeMillis();
    }

    public TraceEntryId getId()
    {
        return id;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void finished( final HttpServletRequest req )
    {
        this.finishedTime = System.currentTimeMillis();
    }

    public long getTime()
    {
        return this.finishedTime - this.startTime;
    }

}
