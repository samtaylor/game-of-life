package presenter;

import grid.BoundedGameGrid;
import grid.Grid;
import renderer.Renderer;
import rules.RuleFour;
import rules.RuleOne;
import rules.RuleThree;
import rules.RuleTwo;

public class GamePresenter
{
    private int tickCount = 0;

    private Grid grid;

    private Renderer renderer;

    protected GamePresenter( Grid grid, Renderer renderer )
    {
        this.grid = grid;
        this.renderer = renderer;
    }

    public void seed()
    {
        this.grid.seed();

        this.renderer.render( this.grid, this.tickCount );
    }

    public void tick()
    {
        this.tickCount ++;

        int gridWidth = this.grid.getWidth();
        int gridHeight = this.grid.getHeight();

        Grid newGrid = new BoundedGameGrid( gridWidth, gridHeight );

        RuleOne ruleOne = new RuleOne();
        RuleTwo ruleTwo = new RuleTwo();
        RuleThree ruleThree = new RuleThree();
        RuleFour ruleFour = new RuleFour();

        for ( int y = 0; y < gridHeight; y ++ )
        {
            for ( int x = 0; x < gridWidth; x ++ )
            {
                ruleOne.applyRule( x, y, this.grid, newGrid );
                ruleTwo.applyRule( x, y, this.grid, newGrid );
                ruleThree.applyRule( x, y, this.grid, newGrid );
                ruleFour.applyRule( x, y, this.grid, newGrid );
            }
        }

        newGrid.copy( this.grid );

        this.renderer.render( this.grid, this.tickCount );
    }
}
