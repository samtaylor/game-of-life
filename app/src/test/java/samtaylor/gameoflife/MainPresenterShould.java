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
    public void startTheGamePresenter_whenTheRefreshMenuItemIsClicked()
    {
        CapturingMainScene mainScene = new CapturingMainScene();
        CapturingGamePresenter capturingGamePresenter = new CapturingGamePresenter();

        new MainPresenter( mainScene, capturingGamePresenter );
        mainScene.menuItemClickListener.clicked();

        assertThat( capturingGamePresenter.startInvoked, is( true ) );
    }

    private static class CapturingMainScene implements MainScene
    {
        private boolean addRefreshMenuItemClickListenerInvoked;

        private MenuItemClickListener menuItemClickListener;

        @Override
        public void addRefreshMenuItemClickListener( MenuItemClickListener menuItemClickListener )
        {
            this.addRefreshMenuItemClickListenerInvoked = true;

            this.menuItemClickListener = menuItemClickListener;
        }
    }

    private static class CapturingGamePresenter implements GamePresenter
    {
        private boolean startInvoked;

        @Override
        public void resume() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void start()
        {
            this.startInvoked = true;
        }

        @Override
        public void stop() {

        }

        @Override
        public long tick() {
            return 0;
        }
    }
}
