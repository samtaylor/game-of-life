package samtaylor.gameoflife.grid;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InfiniteGameGridShould
{

    private InfiniteGameGrid infiniteGameGrid;

    @Before
    public void setup()
    {
        infiniteGameGrid = new InfiniteGameGrid( 3, 3 );

        // 1, 0, 0
        // 0, 0, 1
        // 0, 0, 0

        infiniteGameGrid.setCellValue( 0, 0, Grid.ALIVE );
        infiniteGameGrid.setCellValue( 2, 1, Grid.ALIVE );
    }

    @Test
    public void returnTheCorrectNumberOfLiveNeighbours()
    {
        assertThat( infiniteGameGrid.getNumberOfLiveChildren( 1, 1 ), is( 2 ) );
    }

    @Test
    public void wrapAcrossTheHorizontalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( infiniteGameGrid.getNumberOfLiveChildren( 2, 1 ), is( 1 ) );
    }

    @Test
    public void wrapAcrossTheVerticalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( infiniteGameGrid.getNumberOfLiveChildren( 1, 2 ), is( 2 ) );
    }

    @Test
    public void wrapAcrossTheHorizontalOrVerticalBounds_whenCalculatingLiveNeighbourCount()
    {
        assertThat( infiniteGameGrid.getNumberOfLiveChildren( 0, 0 ), is( 1 ) );
    }
}
