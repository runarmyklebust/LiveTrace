package com.enonic.app;

import java.time.Instant;
import java.util.LinkedHashMap;

import com.google.common.collect.Maps;

public class Trace
{
    private final int size;

    private final LinkedHashMap<TraceEntryId, TraceEntry> traceMap;

    public Trace( final int size )
    {
        this.size = size;
        this.traceMap = Maps.newLinkedHashMap();
    }

    public TraceEntryId add( final Instant startTime )
    {
        final TraceEntry traceEntry = new TraceEntry();

        this.traceMap.put( traceEntry.getId(), traceEntry );
        return traceEntry.getId();
    }


}

