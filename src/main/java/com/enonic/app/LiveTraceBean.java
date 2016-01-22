package com.enonic.app;

public class LiveTraceBean
{
    public TraceMapper getAll()
    {
        return new TraceMapper( Trace.instance );
    }

    public TraceEntryMapper getEntry( final String id )
    {
        final TraceEntry traceEntry = Trace.instance.get( TraceEntryId.from( id ) );

        if ( traceEntry == null )
        {
            return null;
        }

        return new TraceEntryMapper( traceEntry );
    }

}
