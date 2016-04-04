package samtaylor.gameoflife.rules;

import samtaylor.gameoflife.grid.Grid;

public class RuleFour implements Rule
{

    @Override
    public void applyRule( int x, int y, Grid fromGrid, Grid toGrid )
    {
        // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
        boolean isDead = !fromGrid.isAlive( x, y );
        boolean hasExactlyThreeLiveNeighbours = fromGrid.getNumberOfLiveChildren( x, y ) == 3;

        if ( isDead && hasExactlyThreeLiveNeighbours )
        {
            toGrid.setCellValue( x, y, Grid.ALIVE );
        }
    }
}
