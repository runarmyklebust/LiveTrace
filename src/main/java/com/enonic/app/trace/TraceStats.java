package com.enonic.app.trace;

public class TraceStats
{
    private final long totalTime;

    private final long invocations;

    private final long maxTime;

    private final String key;

    private TraceStats( final Builder builder )
    {
        this.key = builder.key;
        this.totalTime = builder.totalTime;
        this.invocations = builder.invocations;
        this.maxTime = builder.maxTime;
    }

    public static Builder create( final String key )
    {
        return new Builder( key );
    }

    public static TraceStats create( final TraceStats source, final Trace trace )
    {
        return create( trace.getUrl() ).
            invocations( source.getInvocations() + 1 ).
            maxTime( trace.getDuration() > source.getMaxTime() ? trace.getDuration() : source.getMaxTime() ).
            totalTime( source.getTotalTime() + trace.getDuration() ).
            build();
    }

    public String getKey()
    {
        return key;
    }

    public long getTotalTime()
    {
        return totalTime;
    }

    public long getInvocations()
    {
        return invocations;
    }

    public long getMaxTime()
    {
        return maxTime;
    }


    public static final class Builder
    {
        private long totalTime;

        private long invocations;

        private long maxTime;

        private String key;

        private Builder( final String key )
        {
            this.key = key;
        }

        public Builder totalTime( final long val )
        {
            totalTime = val;
            return this;
        }

        public Builder invocations( final long val )
        {
            invocations = val;
            return this;
        }

        public Builder maxTime( final long val )
        {
            maxTime = val;
            return this;
        }

        public TraceStats build()
        {
            return new TraceStats( this );
        }
    }
}
