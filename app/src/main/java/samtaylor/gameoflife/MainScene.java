package samtaylor.gameoflife;

public interface MainScene
{
    interface MenuItemClickListener
    {
        void clicked();
    }

    void addRefreshMenuItemClickListener( MenuItemClickListener menuItemClickListener );
}
