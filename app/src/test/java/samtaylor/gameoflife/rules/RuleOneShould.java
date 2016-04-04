package samtaylor.gameoflife.rules;

import org.junit.Before;
import org.junit.Test;

import grid.Grid;
import rules.RuleOne;
import samtaylor.gameoflife.helpers.CapturingGrid;
import samtaylor.gameoflife.helpers.MockGrid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleOneShould
{
    private RuleOne ruleOne;
    private CapturingGrid capturingGrid;

    @Before
    public void setup()
    {
        this.ruleOne = new RuleOne();
        this.capturingGrid = new CapturingGrid();
    }

    @Test
    public void turnCellOff_whenCellIsAlive_andHasFewerThanTwoLiveNeighbours()
    {
        this.ruleOne.applyRule( 3, 4, new MockGrid( true, 0 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedForX, is( 3 ) );
        assertThat( this.capturingGrid.setCellValueInvokedForY, is( 4 ) );
        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.DEAD ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsDead()
    {
        this.ruleOne.applyRule( 1, 2, new MockGrid( false, 0 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsAlive_andHasMoreThanOneNeighbour()
    {
        this.ruleOne.applyRule( 5, 6, new MockGrid( true, 2 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }

}
