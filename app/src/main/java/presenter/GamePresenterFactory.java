package presenter;

import grid.BoundedGameGrid;
import renderer.ConsoleRenderer;
import renderer.Renderer;

public class GamePresenterFactory
{
    public GamePresenter create( int gridWidth, int gridHeight )
    {
        return new GamePresenter( new BoundedGameGrid( gridWidth, gridHeight ), new ConsoleRenderer() );
    }
}
