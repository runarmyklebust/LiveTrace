package com.enonic.app;

import java.time.Instant;

import com.enonic.app.trace.Trace;
import com.enonic.xp.data.PropertyTree;
import com.enonic.xp.index.IndexConfig;
import com.enonic.xp.index.IndexConfigDocument;
import com.enonic.xp.index.PatternIndexConfigDocument;
import com.enonic.xp.node.CreateNodeParams;
import com.enonic.xp.node.NodeId;
import com.enonic.xp.node.NodePath;

public class CreateNodeParamsFactory
{
    private final static IndexConfigDocument INDEX_CONFIG = PatternIndexConfigDocument.create().
        defaultConfig( IndexConfig.BY_TYPE ).
        add( "startTime", IndexConfig.MINIMAL ).
        add( "endTime", IndexConfig.MINIMAL ).
        add( "duration", IndexConfig.MINIMAL ).
        build();

    public static CreateNodeParams create( final Trace trace )
    {
        PropertyTree data = new PropertyTree();
        data.setString( "url", trace.getUrl() );
        data.setInstant( "startTime", Instant.ofEpochMilli( trace.getStartTime() ) );
        data.setInstant( "endTime", Instant.ofEpochMilli( trace.getEndTime() ) );
        data.setLong( "duration", trace.getDuration() );
        final NodeId nodeId = new NodeId();

        return CreateNodeParams.create().
            setNodeId( nodeId ).
            name( nodeId.toString() ).
            parent( NodePath.ROOT ).
            data( data ).
            indexConfigDocument( INDEX_CONFIG ).
            build();
    }

}
