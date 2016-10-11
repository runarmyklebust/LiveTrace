package com.enonic.app;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.enonic.app.trace.Trace;
import com.enonic.app.trace.TraceKeeper;
import com.enonic.xp.web.filter.OncePerRequestFilter;

@Component(immediate = true, service = Filter.class,
    property = {"osgi.http.whiteboard.filter.pattern=/portal", //
        "service.ranking:Integer=500", //
        "osgi.http.whiteboard.filter.dispatcher=REQUEST"})
public class LiveTraceFilter
    extends OncePerRequestFilter
{
    private TraceKeeper traceKeeper;

    @Override
    protected void doHandle( final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain )
        throws Exception
    {

        if ( this.traceKeeper == null )
        {
            System.out.println( "TRACEKEEPER IS NULL!!" );
        }

        Trace trace = Trace.start( req.getRequestURI() );
        req.setAttribute( Trace.class.getName(), trace );

        chain.doFilter( req, res );

        trace = (Trace) req.getAttribute( Trace.class.getName() );
        trace.end();

        traceKeeper.add( trace );
    }

    @Reference
    public void setTraceKeeper( final TraceKeeper traceKeeper )
    {
        this.traceKeeper = traceKeeper;
    }
}
