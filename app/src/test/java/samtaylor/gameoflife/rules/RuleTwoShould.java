package samtaylor.gameoflife.rules;

import org.junit.Before;
import org.junit.Test;

import grid.Grid;
import rules.RuleTwo;
import samtaylor.gameoflife.helpers.CapturingGrid;
import samtaylor.gameoflife.helpers.MockGrid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleTwoShould
{
    private RuleTwo ruleTwo;
    private CapturingGrid capturingGrid;

    @Before
    public void setup()
    {
        this.ruleTwo = new RuleTwo();
        this.capturingGrid = new CapturingGrid();
    }

    @Test
    public void turnCellOn_whenCellIsAlive_andHasTwoLiveNeighbours()
    {
        this.ruleTwo.applyRule( 1, 2, new MockGrid( true, 2 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedForX, is ( 1 ) );
        assertThat( this.capturingGrid.setCellValueInvokedForY, is ( 2 ) );
        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is ( Grid.ALIVE ) );
    }

    @Test
    public void turnCellOn_whenCellIsAlive_andHasThreeLiveNeighbours()
    {
        this.ruleTwo.applyRule( 1, 2, new MockGrid( true, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedForX, is ( 1 ) );
        assertThat( this.capturingGrid.setCellValueInvokedForY, is ( 2 ) );
        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is ( Grid.ALIVE ) );
    }

    @Test
    public void leaveTheGridAlone_ifCellIsDead()
    {
        this.ruleTwo.applyRule( 1, 2, new MockGrid( false, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is ( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifCellIsAlive_andHasFewerThanTwoLiveNeighbours()
    {
        this.ruleTwo.applyRule( 1, 2, new MockGrid( true, 1 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is ( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifCellIsAlive_andHasMoreThanThreeLiveNeighbours()
    {
        this.ruleTwo.applyRule( 1, 2, new MockGrid( false, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is ( Grid.INVALID ) );
    }
}
