package samtaylor.gameoflife.grid;

import java.util.Random;

public class InfiniteGameGrid implements Grid
{
    private int [][] grid;

    private int width;
    private int height;

    public InfiniteGameGrid( int width, int height )
    {
        this.grid = new int[ width ][ height ];

        this.width = width;
        this.height = height;
    }

    @Override
    public void seed()
    {
        Random random = new Random( System.currentTimeMillis() );

        int seedCount = (int) (this.width * this.height * 0.1);

        for ( int i = 0; i < seedCount; i ++ )
        {
            int x = random.nextInt( this.width );
            int y = random.nextInt( this.height );

            this.grid[ x ][ y ] = 1;
        }
    }

    @Override
    public boolean isAlive() {
        int gridTotal = 0;

        for ( int y = 0; y < this.height; y ++ )
        {
            for ( int x = 0; x < this.width; x ++ )
            {
                gridTotal += this.grid[ x ][ y ];
            }
        }

        return gridTotal != 0;
    }

    @Override
    public boolean isAlive(int x, int y) {
        return this.grid[ x ][ y ] == 1;
    }

    @Override
    public int getNumberOfLiveChildren(int x, int y) {
        int liveNeighbourCount = 0;

        liveNeighbourCount += this.getCellValue( x - 1, y - 1 );
        liveNeighbourCount += this.getCellValue( x, y - 1 );
        liveNeighbourCount += this.getCellValue( x + 1, y - 1 );

        liveNeighbourCount += this.getCellValue( x - 1, y );
        liveNeighbourCount += this.getCellValue( x + 1, y );

        liveNeighbourCount += this.getCellValue( x - 1, y + 1 );
        liveNeighbourCount += this.getCellValue( x, y + 1 );
        liveNeighbourCount += this.getCellValue( x + 1, y + 1 );

        return liveNeighbourCount;
    }

    @Override
    public int getCellValue(int x, int y) {

        if ( x < 0 ) x += this.width;
        if ( y < 0 ) y += this.height;
        if ( x >= this.width ) x -= this.width;
        if ( y >= this.height ) y -= this.height;

        if ( x < 0 || x >= this.width )
        {
            return 0;
        }
        else if ( y < 0 || y >= this.height )
        {
            return 0;
        }
        else
        {
            return this.grid[ x ][ y ];
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setCellValue(int x, int y, int value) {
        this.grid[ x ][ y ] = value;
    }

    @Override
    public void copy(Grid toGrid) {
        for ( int y = 0; y < this.height; y ++ )
        {
            for ( int x = 0; x < this.width; x ++ )
            {
                toGrid.setCellValue( x, y, this.grid[ x ][ y ] );
            }
        }
    }
}
