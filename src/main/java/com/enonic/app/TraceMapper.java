package com.enonic.app;

import com.enonic.xp.script.serializer.MapGenerator;
import com.enonic.xp.script.serializer.MapSerializable;

public class TraceMapper
    implements MapSerializable
{

    private final Trace trace;

    public TraceMapper( final Trace trace )
    {
        this.trace = trace;
    }

    @Override
    public void serialize( final MapGenerator gen )
    {
        

    }
}
