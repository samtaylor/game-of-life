package rules;

import grid.Grid;

public class RuleTwo implements Rule
{
    @Override
    public void applyRule( int x, int y, Grid fromGrid, Grid toGrid )
    {
        // Any live cell with two or three live neighbours lives on to the next generation
        int numberOfLiveChildren = fromGrid.getNumberOfLiveChildren( x, y );
        boolean isAlive = fromGrid.isAlive( x,y );
        boolean hasTwoOrThreeLiveNeighbours = numberOfLiveChildren == 2 || numberOfLiveChildren == 3;

        if ( isAlive && hasTwoOrThreeLiveNeighbours )
        {
            toGrid.setCellValue( x, y, Grid.ALIVE );
        }
    }
}
