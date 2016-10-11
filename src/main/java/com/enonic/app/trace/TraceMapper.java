package com.enonic.app.trace;

import com.enonic.xp.script.serializer.MapGenerator;
import com.enonic.xp.script.serializer.MapSerializable;

public class TraceMapper
    implements MapSerializable
{
    private final TraceKeeper traceKeeper;

    public TraceMapper( final TraceKeeper traceKeeper )
    {
        this.traceKeeper = traceKeeper;
    }

    @Override
    public void serialize( final MapGenerator gen )
    {
        gen.value( "total", traceKeeper.size() );

        gen.array( "results" );

        for ( final TraceStats stat : traceKeeper.getAll().values() )
        {
            gen.map();
            gen.value( "count", stat.getInvocations() );
            gen.value( "key", stat.getKey() );
            gen.value( "max", stat.getMaxTime() );
            gen.value( "total", stat.getTotalTime() );
            gen.end();
        }

        gen.end();
    }
}
