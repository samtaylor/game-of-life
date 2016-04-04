package samtaylor.gameoflife.presenter;

import samtaylor.gameoflife.grid.BoundedGameGrid;
import samtaylor.gameoflife.renderer.ConsoleRenderer;

public class GamePresenterFactory
{
    public GamePresenter create( int gridWidth, int gridHeight )
    {
        return new GamePresenter( new BoundedGameGrid( gridWidth, gridHeight ), new ConsoleRenderer() );
    }
}
