package samtaylor.gameoflife;

import samtaylor.gameoflife.presenter.GamePresenter;

public class MainPresenter
{
    public MainPresenter(MainScene mainScene, final GamePresenter gamePresenter )
    {
        mainScene.addRefreshMenuItemClickListener(new MainScene.MenuItemClickListener() {
            @Override
            public void clicked() {
                gamePresenter.start();
            }
        });

        mainScene.addLifecycleListener(new MainScene.LifecycleListener() {
            @Override
            public void paused() {
                gamePresenter.pause();
            }

            @Override
            public void resumed() {
                gamePresenter.resume();
            }

            @Override
            public void destroyed() {
                gamePresenter.destroy();
            }
        });

        gamePresenter.start();
    }


}
