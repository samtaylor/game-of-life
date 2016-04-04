package samtaylor.gameoflife;

import org.junit.Test;

import samtaylor.gameoflife.presenter.GamePresenter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPresenterShould
{
    @Test
    public void startTheGamePresenter()
    {
        CapturingGamePresenter gamePresenter = new CapturingGamePresenter();

        new MainPresenter( new CapturingMainScene(), gamePresenter );

        assertThat( gamePresenter.startInvoked, is( true ) );
    }

    @Test
    public void registerForRefreshClickEvents()
    {
        CapturingMainScene mainScene = new CapturingMainScene();

        new MainPresenter(mainScene, new CapturingGamePresenter() );

        assertThat( mainScene.addRefreshMenuItemClickListenerInvoked, is( true ) );
    }

    @Test
    public void registerForLifecycleEvents()
    {
        CapturingMainScene mainScene = new CapturingMainScene();

        new MainPresenter( mainScene, new CapturingGamePresenter() );

        assertThat( mainScene.addLifecycleListenerInvoked, is( true ) );
    }

    @Test
    public void startTheGamePresenter_whenTheRefreshMenuItemIsClicked()
    {
        CapturingMainScene mainScene = new CapturingMainScene();
        CapturingGamePresenter capturingGamePresenter = new CapturingGamePresenter();

        new MainPresenter( mainScene, capturingGamePresenter );
        mainScene.menuItemClickListener.clicked();

        assertThat( capturingGamePresenter.startInvoked, is( true ) );
    }

    @Test
    public void pauseTheGamePresenter_whenTheActivityIsPaused()
    {
        CapturingMainScene mainScene = new CapturingMainScene();
        CapturingGamePresenter capturingGamePresenter = new CapturingGamePresenter();

        new MainPresenter( mainScene, capturingGamePresenter );
        mainScene.lifecycleListener.paused();

        assertThat( capturingGamePresenter.pauseInvoked, is( true ) );
    }

    @Test
    public void resumeTheGamePresenter_whenTheActivityIsResumed()
    {
        CapturingMainScene mainScene = new CapturingMainScene();
        CapturingGamePresenter capturingGamePresenter = new CapturingGamePresenter();

        new MainPresenter( mainScene, capturingGamePresenter );
        mainScene.lifecycleListener.resumed();

        assertThat( capturingGamePresenter.resumeInvoked, is( true ) );
    }

    @Test
    public void destroyTheGamePresenter_whenTheActivityIsDestroyed()
    {
        CapturingMainScene mainScene = new CapturingMainScene();
        CapturingGamePresenter capturingGamePresenter = new CapturingGamePresenter();

        new MainPresenter( mainScene, capturingGamePresenter );
        mainScene.lifecycleListener.destroyed();

        assertThat( capturingGamePresenter.destroyInvoked, is( true ) );
    }

    private static class CapturingMainScene implements MainScene
    {
        private boolean addRefreshMenuItemClickListenerInvoked;
        private boolean addLifecycleListenerInvoked;

        private MenuItemClickListener menuItemClickListener;
        private LifecycleListener lifecycleListener;

        @Override
        public void addRefreshMenuItemClickListener( MenuItemClickListener menuItemClickListener )
        {
            this.addRefreshMenuItemClickListenerInvoked = true;

            this.menuItemClickListener = menuItemClickListener;
        }

        @Override
        public void addLifecycleListener( LifecycleListener lifecycleListener )
        {
            this.addLifecycleListenerInvoked = true;

            this.lifecycleListener = lifecycleListener;
        }
    }

    private static class CapturingGamePresenter implements GamePresenter
    {
        private boolean startInvoked;
        private boolean pauseInvoked;
        private boolean resumeInvoked;
        private boolean destroyInvoked;

        @Override
        public void resume() {
            this.resumeInvoked = true;
        }

        @Override
        public void pause()
        {
            this.pauseInvoked = true;
        }

        @Override
        public void start()
        {
            this.startInvoked = true;
        }

        @Override
        public void destroy() {
            this.destroyInvoked = true;
        }

        @Override
        public long tick() {
            return 0;
        }
    }
}
