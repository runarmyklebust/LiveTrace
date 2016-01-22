package com.enonic.app;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

public class Trace
{
    public final static Trace instance = new Trace( 50 );

    private final int size;

    private final LinkedHashMap<TraceEntryId, TraceEntry> traceMap;

    private Trace( final int size )
    {
        this.size = size;
        this.traceMap = Maps.newLinkedHashMap();
    }

    public TraceEntry add( final HttpServletRequest request )
    {
        final TraceEntry traceEntry = new TraceEntry( request );

        this.traceMap.put( traceEntry.getId(), traceEntry );
        return traceEntry;
    }


}

