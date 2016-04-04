package samtaylor.gameoflife.presenter;

import samtaylor.gameoflife.grid.BoundedGameGrid;
import samtaylor.gameoflife.grid.Grid;
import samtaylor.gameoflife.renderer.ConsoleRenderer;
import samtaylor.gameoflife.renderer.Renderer;

public class AndroidGamePresenterBuilder
{
    private Grid grid;
    private Renderer renderer;

    public AndroidGamePresenterBuilder with(Grid grid )
    {
        this.grid = grid;

        return this;
    }

    public AndroidGamePresenterBuilder with(Renderer renderer )
    {
        this.renderer = renderer;

        return this;
    }

    public AndroidGamePresenter build()
    {
        if ( this.grid == null )
        {
            this.grid = new BoundedGameGrid( 10, 10 );
        }

        if ( this.renderer == null )
        {
            this.renderer = new ConsoleRenderer();
        }

        return new AndroidGamePresenter( this.grid, this.renderer );
    }
}
