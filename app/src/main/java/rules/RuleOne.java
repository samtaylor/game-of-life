package rules;

import grid.Grid;

public class RuleOne implements Rule
{
    @Override
    public void applyRule( int x, int y, Grid fromGrid, Grid toGrid )
    {
        // Any live cell with fewer than two live neighbours dies, as if caused by under-population
        boolean isAlive = fromGrid.isAlive( x, y );
        boolean hasFewerThanTwoLiveNeighbours = fromGrid.getNumberOfLiveChildren( x, y ) < 2;

        if ( isAlive && hasFewerThanTwoLiveNeighbours )
        {
            toGrid.setCellValue( x, y, Grid.DEAD );
        }
    }
}
