package samtaylor.gameoflife.presenter;

public interface GamePresenter
{
    void resume();
    void pause();
    void start();
    void stop();

    long tick();
}
