package com.enonic.app.trace;

public class Trace
{
    private final String url;

    private final Long startTime;

    private Long endTime;

    public static Trace start( final String url )
    {
        return new Trace( url );
    }

    public void end()
    {
        this.endTime = System.currentTimeMillis();
    }

    public Trace( final Long endTime, final Long startTime, final String url )
    {
        this.endTime = endTime;
        this.startTime = startTime;
        this.url = url;
    }

    private Trace( final String url )
    {
        this.startTime = System.currentTimeMillis();
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    public Long getDuration()
    {
        return this.endTime - this.startTime;
    }

    public Long getStartTime()
    {
        return startTime;
    }

    public Long getEndTime()
    {
        return endTime;
    }
}

