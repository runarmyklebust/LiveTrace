package com.enonic.app.trace;

import java.util.HashMap;

public interface TraceKeeper
{
    int size();

    void add( final Trace trace );

    HashMap<String, TraceStats> getAll();
}
