package samtaylor.gameoflife.presenter;

public interface GamePresenter
{
    void resume();
    void pause();
    void start();
    void destroy();

    long tick();
}
