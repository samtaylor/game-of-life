package samtaylor.gameoflife;

public interface MainScene
{
    interface MenuItemClickListener
    {
        void clicked();
    }

    interface LifecycleListener
    {
        void paused();
        void resumed();
        void destroyed();
    }

    void addRefreshMenuItemClickListener( MenuItemClickListener menuItemClickListener );
    void addLifecycleListener( LifecycleListener lifecycleListener );
}
