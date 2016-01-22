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
        gen.value( "total", trace.size() );
        gen.value( "max", trace.maxSize() );

        gen.array( "entries" );

        for ( final TraceEntry entry : trace )
        {
            gen.map();
            gen.value( "id", entry.getId().getValue() );
            gen.value( "time", entry.getTime() );
            gen.value( "completed", entry.isFinished() );
            gen.end();
        }

        gen.end();
    }
}
