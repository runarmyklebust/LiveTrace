package com.enonic.app;

import com.enonic.app.trace.TraceKeeper;
import com.enonic.app.trace.TraceMapper;
import com.enonic.xp.script.bean.BeanContext;
import com.enonic.xp.script.bean.ScriptBean;

@SuppressWarnings("unused")
public class LiveTraceBean
    implements ScriptBean
{
    private TraceKeeper traceKeeper;

    public TraceMapper getAll()
    {
        return new TraceMapper( traceKeeper );
    }

    @Override
    public void initialize( final BeanContext context )
    {
        this.traceKeeper = context.getService( TraceKeeper.class ).get();
    }

}
