package samtaylor.gameoflife.rules;

import org.junit.Before;
import org.junit.Test;

import samtaylor.gameoflife.grid.Grid;
import samtaylor.gameoflife.helpers.CapturingGrid;
import samtaylor.gameoflife.helpers.MockGrid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleFourShould
{
    private RuleFour ruleFour;
    private CapturingGrid capturingGrid;

    @Before
    public void setup()
    {
        this.ruleFour = new RuleFour();
        this.capturingGrid = new CapturingGrid();
    }

    @Test
    public void turnCellOn_ifTheCellIsDead_andHasThreeLiveNeighbours()
    {
        this.ruleFour.applyRule( 4, 6, new MockGrid( false, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedForX, is( 4 ) );
        assertThat( this.capturingGrid.setCellValueInvokedForY, is( 6 ) );
        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.ALIVE ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsAlive()
    {
        this.ruleFour.applyRule( 4, 6, new MockGrid( true, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsDead_andHasFewerThanThreeLivingNeighbours()
    {
        this.ruleFour.applyRule( 4, 6, new MockGrid( false, 1 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsDead_andHasMoreThanThreeLivingNeighbours()
    {
        this.ruleFour.applyRule( 4, 6, new MockGrid( false, 5 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }
}
