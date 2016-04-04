package samtaylor.gameoflife.grid;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoundedGameGridShould
{

    private BoundedGameGrid boundedGameGrid;

    @Before
    public void setup()
    {
        boundedGameGrid = new BoundedGameGrid( 3, 3 );

        // 1, 0, 0
        // 0, 0, 1
        // 0, 0, 0

        boundedGameGrid.setCellValue( 0, 0, Grid.ALIVE );
        boundedGameGrid.setCellValue( 2, 1, Grid.ALIVE );
    }

    @Test
    public void returnTheCorrectNumberOfLiveNeighbours()
    {
        assertThat( boundedGameGrid.getNumberOfLiveChildren( 1, 1 ), is( 2 ) );
    }

    @Test
    public void notWrapAcrossTheHorizontalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( boundedGameGrid.getNumberOfLiveChildren( 2, 1 ), is( 0 ) );
    }

    @Test
    public void notWrapAcrossTheVerticalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( boundedGameGrid.getNumberOfLiveChildren( 1, 2 ), is( 1 ) );
    }

    @Test
    public void notWrapAcrossTheHorizontalOrVerticalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( boundedGameGrid.getNumberOfLiveChildren( 0, 0 ), is( 0 ) );
    }

}
