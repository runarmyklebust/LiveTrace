package com.enonic.app;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.EvictingQueue;

public class Trace
    implements Iterable<TraceEntry>
{
    public final static Trace instance = new Trace( 50 );

    private EvictingQueue<TraceEntry> traces;

    private Trace( final int size )
    {
        this.traces = EvictingQueue.create( size );
    }

    public TraceEntry add( final HttpServletRequest request )
    {
        final TraceEntry traceEntry = new TraceEntry( request );

        this.traces.add( traceEntry );
        return traceEntry;
    }

    public TraceEntry get( final TraceEntryId id )
    {
        for ( final TraceEntry entry : this.traces )
        {
            if ( entry.getId().equals( id ) )
            {
                return entry;
            }
        }

        return null;
    }

    @Override
    public Iterator<TraceEntry> iterator()
    {
        return this.traces.iterator();
    }
}

