package samtaylor.gameoflife.presenter;

import samtaylor.gameoflife.grid.BoundedGameGrid;
import samtaylor.gameoflife.grid.Grid;
import samtaylor.gameoflife.renderer.Renderer;
import samtaylor.gameoflife.rules.RuleFour;
import samtaylor.gameoflife.rules.RuleOne;
import samtaylor.gameoflife.rules.RuleThree;
import samtaylor.gameoflife.rules.RuleTwo;

public class AndroidGamePresenter implements GamePresenter
{
    private static final int FPS = 8;

    private int tickCount = 0;

    private boolean running = false;
    private boolean paused = false;

    private Grid grid;

    private Renderer renderer;
    private Thread gameThread;

    protected AndroidGamePresenter(Grid grid, Renderer renderer )
    {
        this.grid = grid;
        this.renderer = renderer;
    }

    @Override
    public void resume()
    {
        if ( this.gameThread == null )
        {
            this.start();
        }
        else
        {
            this.paused = false;
        }
    }

    @Override
    public void pause()
    {
        this.paused = true;
    }

    @Override
    public void start()
    {
        this.tickCount = 0;
        this.grid.seed();
        this.renderer.render( this.grid, this.tickCount );

        this.running = true;
        this.paused = false;

        if ( this.gameThread == null ) {
            this.gameThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        long sleepTime = (1000 / FPS);

                        if (!paused && grid.isAlive()) {
                            long elapsedTime = tick();

                            sleepTime = (1000 / FPS) - elapsedTime;

                            renderer.render(grid, tickCount);
                        }

                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            });
            this.gameThread.start();
        }
    }

    @Override
    public void destroy()
    {
        this.running = false;
    }

    @Override
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
