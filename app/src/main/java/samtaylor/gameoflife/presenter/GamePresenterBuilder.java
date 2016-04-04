package samtaylor.gameoflife.presenter;

import samtaylor.gameoflife.grid.BoundedGameGrid;
import samtaylor.gameoflife.grid.Grid;
import samtaylor.gameoflife.renderer.ConsoleRenderer;
import samtaylor.gameoflife.renderer.Renderer;

public class GamePresenterBuilder
{
    private Grid grid;
    private Renderer renderer;

    public GamePresenterBuilder with( Grid grid )
    {
        this.grid = grid;

        return this;
    }

    public GamePresenterBuilder with( Renderer renderer )
    {
        this.renderer = renderer;

        return this;
    }

    public GamePresenter build()
    {
        if ( this.grid == null )
        {
            this.grid = new BoundedGameGrid( 10, 10 );
        }

        if ( this.renderer == null )
        {
            this.renderer = new ConsoleRenderer();
        }

        return new GamePresenter( this.grid, this.renderer );
    }
}
