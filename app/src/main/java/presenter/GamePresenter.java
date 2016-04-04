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
    private static final int FPS = 1;

    private int tickCount = 0;

    private boolean running = false;

    private Grid grid;

    private Renderer renderer;

    protected GamePresenter( Grid grid, Renderer renderer )
    {
        this.grid = grid;
        this.renderer = renderer;
    }

    public void start()
    {
        this.grid.seed();
        this.renderer.render( this.grid, this.tickCount );

        this.running = true;
        new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                while( running && grid.isAlive() )
                {
                    long elapsedTime = tick();

                    long sleepTime = ( 1000 / FPS ) - elapsedTime;

                    renderer.render( grid, tickCount );

                    try
                    {
                        Thread.sleep( sleepTime );
                    } catch ( InterruptedException ignored ) {}
                }
            }
        }).start();
    }

    public void stop()
    {
        this.running = false;
    }

    public long tick()
    {
        long start = System.currentTimeMillis();

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

        return System.currentTimeMillis() - start;
    }
}
