package com.enonic.app;

import java.util.UUID;

public class TraceEntryId
{

    private String value;

    public String getValue()
    {
        return value;
    }

    public static TraceEntryId from( final String value )
    {
        final TraceEntryId traceEntryId = new TraceEntryId();
        traceEntryId.value = value;
        return traceEntryId;
    }

    public TraceEntryId()
    {
        this.value = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals( final Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        final TraceEntryId that = (TraceEntryId) o;

        return !( value != null ? !value.equals( that.value ) : that.value != null );

    }

    @Override
    public int hashCode()
    {
        return value != null ? value.hashCode() : 0;
    }
}
