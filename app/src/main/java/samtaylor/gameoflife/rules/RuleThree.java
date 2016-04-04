package samtaylor.gameoflife.rules;

import samtaylor.gameoflife.grid.Grid;

public class RuleThree implements Rule
{
    @Override
    public void applyRule( int x, int y, Grid fromGrid, Grid toGrid )
    {
        // Any live cell with more than three live neighbours dies, as if by over-population
        boolean isAlive = fromGrid.isAlive( x, y );
        boolean hasMoreThanThreeLiveNeighbours = fromGrid.getNumberOfLiveChildren( x, y ) > 3;

        if ( isAlive && hasMoreThanThreeLiveNeighbours )
        {
            toGrid.setCellValue( x, y, Grid.DEAD );
        }
    }
}
