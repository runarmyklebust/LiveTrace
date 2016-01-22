package com.enonic.app;

import java.time.Instant;

public class TraceEntry
{
    private TraceEntryId id;

    private boolean finished;

    private final Instant startTime;

    private Instant finishedTime;

    public TraceEntry()
    {
        this.startTime = Instant.now();
    }

    public TraceEntryId getId()
    {
        return id;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public boolean
}
