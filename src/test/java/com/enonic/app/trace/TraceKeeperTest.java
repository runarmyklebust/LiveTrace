package com.enonic.app.trace;

import org.junit.Test;

public class TraceKeeperTest
{
    @Test
    public void add_to_same()
        throws Exception
    {
        /*
        final TraceKeeper traceKeeper = new TraceKeeper();

        final String url = "http://test.com/1";
        traceKeeper.add( new NewTrace( 100L, 0L, url ) );
        traceKeeper.add( new NewTrace( 100L, 0L, url ) );
        traceKeeper.add( new NewTrace( 100L, 0L, url ) );

        final ConcurrentHashMap<String, TraceStats> map = traceKeeper.getAll();

        final TraceStats traceStats = map.get( url );

        assertEquals( 300, traceStats.getTotalTime() );
        assertEquals( 3, traceStats.getInvocations() );
        assertEquals( 100, traceStats.getMaxTime() );
    */
    }

    @Test
    public void add_to_different()
        throws Exception
    {
       /* final TraceKeeper traceKeeper = new TraceKeeper};

        final String url1 = "http://test.com/1";
        final String url2 = "http://test.com/2";
        final String url3 = "http://test.com/3";

        traceKeeper.add( new NewTrace( 100L, 0L, url1 ) );
        traceKeeper.add( new NewTrace( 200L, 0L, url1 ) );
        traceKeeper.add( new NewTrace( 300L, 0L, url2 ) );
        traceKeeper.add( new NewTrace( 400L, 0L, url2 ) );
        traceKeeper.add( new NewTrace( 500L, 0L, url3 ) );
        traceKeeper.add( new NewTrace( 600L, 0L, url3 ) );

        final ConcurrentHashMap<String, TraceStats> map = traceKeeper.getAll();

        assertEquals( map.get( url1 ).getInvocations(), 2 );
        assertEquals( map.get( url1 ).getMaxTime(), 200 );
        assertEquals( map.get( url2 ).getInvocations(), 2 );
        assertEquals( map.get( url2 ).getMaxTime(), 400 );
        assertEquals( map.get( url3 ).getInvocations(), 2 );
        assertEquals( map.get( url3 ).getMaxTime(), 600 );
        */
    }
}