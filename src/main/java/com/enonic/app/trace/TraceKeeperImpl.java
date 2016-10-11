package com.enonic.app.trace;

import java.util.HashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import com.enonic.app.CreateNodeParamsFactory;
import com.enonic.xp.branch.Branch;
import com.enonic.xp.context.Context;
import com.enonic.xp.context.ContextAccessor;
import com.enonic.xp.context.ContextBuilder;
import com.enonic.xp.node.Node;
import com.enonic.xp.node.NodeService;
import com.enonic.xp.repository.CreateRepositoryParams;
import com.enonic.xp.repository.Repository;
import com.enonic.xp.repository.RepositoryId;
import com.enonic.xp.repository.RepositoryService;
import com.enonic.xp.repository.RepositorySettings;
import com.enonic.xp.repository.ValidationSettings;
import com.enonic.xp.security.RoleKeys;
import com.enonic.xp.security.User;
import com.enonic.xp.security.auth.AuthenticationInfo;

@SuppressWarnings("unused")
@Component(immediate = true)
public class TraceKeeperImpl
    implements TraceKeeper
{
    private RepositoryService repositoryService;

    private NodeService nodeService;

    private final static RepositoryId REPOSITORY_ID = RepositoryId.from( "live-trace" );

    private final static Branch BRANCH = Branch.from( "master" );

    private final Logger LOG = LoggerFactory.getLogger( TraceKeeperImpl.class );

    @SuppressWarnings("unused")
    @Activate
    public void initialize()
    {
        final Repository repository = repositoryService.get( REPOSITORY_ID );

        if ( repository == null )
        {
            LOG.info( "Repository [" + REPOSITORY_ID + "] not initialized, initializing...." );

            final Repository repo = this.repositoryService.create( CreateRepositoryParams.create().
                repositoryId( REPOSITORY_ID ).
                repositorySettings( RepositorySettings.create().
                    validationSettings( ValidationSettings.create().
                        checkExists( false ).
                        checkParentExists( false ).
                        build() ).
                    build() ).
                build() );

            LOG.info( "Repository [" + repo.getId() + "] initialized successfully" );
        }
        else
        {
            LOG.info( "Repository [" + REPOSITORY_ID + "] already initialized" );
        }
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public void add( final Trace trace )
    {
        createAdminContext().runWith( () -> this.doStoreEntry( trace ) );
    }

    private Context createAdminContext()
    {
        return ContextBuilder.from( ContextAccessor.current() ).
            repositoryId( REPOSITORY_ID ).
            branch( BRANCH ).
            authInfo( AuthenticationInfo.create().
                user( User.ANONYMOUS ).
                principals( RoleKeys.ADMIN ).
                build() ).
            build();
    }

    private void doStoreEntry( final Trace trace )
    {
        final Node node = this.nodeService.create( CreateNodeParamsFactory.create( trace ) );
    }

    @Override
    public HashMap<String, TraceStats> getAll()
    {
        return Maps.newHashMap();
    }

    @SuppressWarnings("unused")
    @Reference
    public void setRepositoryService( final RepositoryService repositoryService )
    {
        this.repositoryService = repositoryService;
    }

    @SuppressWarnings("unused")
    @Reference
    public void setNodeService( final NodeService nodeService )
    {
        this.nodeService = nodeService;
    }
}
