package com.enonic.app;

import com.enonic.xp.script.serializer.MapGenerator;
import com.enonic.xp.script.serializer.MapSerializable;

public class TraceEntryMapper
    implements MapSerializable
{
    private final TraceEntry entry;

    public TraceEntryMapper( final TraceEntry entry )
    {
        this.entry = entry;
    }

    @Override
    public void serialize( final MapGenerator gen )
    {
        gen.value( "id", entry.getId().getValue() );
        gen.value( "time", entry.getTime() );
        gen.value( "completed", entry.isFinished() );
        gen.value( "url", entry.getUrl() );
        gen.value( "site", entry.getSite() );
        gen.value( "content", entry.getContent() );

    }
}
