package com.enonic.app;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.enonic.xp.web.filter.OncePerRequestFilter;

@Component(immediate = true, service = Filter.class,
    property = {"osgi.http.whiteboard.filter.pattern=/", //
        "service.ranking:Integer=100", //
        "osgi.http.whiteboard.filter.dispatcher=FORWARD", //
        "osgi.http.whiteboard.filter.dispatcher=REQUEST"})
public class LiveTraceFilter
    extends OncePerRequestFilter
{

    @Override
    protected void doHandle( final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain )
        throws Exception
    {
        final Trace trace = Trace.instance;
        TraceEntry entry = trace.add( req );
        req.setAttribute( TraceEntry.class.getName(), entry );

        chain.doFilter( req, res );

        entry = (TraceEntry) req.getAttribute( TraceEntry.class.getName() );
        entry.finished( req );
    }
}
