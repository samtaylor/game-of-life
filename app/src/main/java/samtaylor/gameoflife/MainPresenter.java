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

        gamePresenter.start();
    }


}
