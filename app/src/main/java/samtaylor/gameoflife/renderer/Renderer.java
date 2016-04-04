package samtaylor.gameoflife.renderer;

import samtaylor.gameoflife.grid.Grid;

public interface Renderer
{
    void render( Grid grid, int tickCount );
}
