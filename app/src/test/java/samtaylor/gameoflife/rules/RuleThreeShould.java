package samtaylor.gameoflife.rules;

import org.junit.Before;
import org.junit.Test;

import grid.Grid;
import rules.RuleThree;
import samtaylor.gameoflife.helpers.CapturingGrid;
import samtaylor.gameoflife.helpers.MockGrid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleThreeShould
{
    private RuleThree ruleThree;
    private CapturingGrid capturingGrid;

    @Before
    public void setup()
    {
        this.ruleThree = new RuleThree();
        this.capturingGrid = new CapturingGrid();
    }

    @Test
    public void killTheCell_ifTheCellIsAlive_andHasMoreThanThreeLivingNeighbours()
    {
        this.ruleThree.applyRule( 1, 3, new MockGrid( true, 4 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedForX, is( 1 ) );
        assertThat( this.capturingGrid.setCellValueInvokedForY, is( 3 ) );
        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.DEAD ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsDead()
    {
        this.ruleThree.applyRule( 1, 3, new MockGrid( false, 4 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }

    @Test
    public void leaveTheGridAlone_ifTheCellIsAlive_andHasFewerThanFourLivingNeighbours()
    {
        this.ruleThree.applyRule( 1, 3, new MockGrid( true, 3 ), this.capturingGrid );

        assertThat( this.capturingGrid.setCellValueInvokedWithValue, is( Grid.INVALID ) );
    }
}
