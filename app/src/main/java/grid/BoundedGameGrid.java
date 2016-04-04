package grid;

import java.util.Random;

public class BoundedGameGrid implements Grid
{
    private static final int SEED_COUNT = 20;

    private int [][] grid;

    private int width;
    private int height;

    public BoundedGameGrid( int width, int height )
    {
        this.width = width;
        this.height = height;

        this.grid = new int[ width ][ height ];
    }

    @Override
    public void seed()
    {
        Random random = new Random( System.currentTimeMillis() );
        // initialise the grid
        for ( int i = 0; i < SEED_COUNT; i ++ )
        {
            int x = random.nextInt( this.width );
            int y = random.nextInt( this.height );

            this.grid[ x ][ y ] = 1;
        }
    }

    @Override
    public boolean isAlive( int x, int y )
    {
        return this.grid[ x ][ y ] == 1;
    }

    @Override
    public int getNumberOfLiveChildren( int x, int y )
    {
        int liveNeighbourCount = 0;

        liveNeighbourCount += this.getCellValue( x - 1, y - 1 );
        liveNeighbourCount += this.getCellValue( x, y - 1 );
        liveNeighbourCount += this.getCellValue( x + 1, y - 1 );

        liveNeighbourCount += this.getCellValue( x - 1, y );
        liveNeighbourCount += this.getCellValue( x + 1, y );

        liveNeighbourCount += this.getCellValue( x - 1, y + 1 );
        liveNeighbourCount += this.getCellValue( x, y + 1 );
        liveNeighbourCount += this.getCellValue( x + 1, y + 1 );

//        int neighbourMinX = x == 0 ? this.width - 1 : x - 1;
//        int neighbourMaxX = x == this.width - 1 ? 0 : x + 1;
//
//        int neighbourMinY = y == 0 ? this.height - 1 : y - 1;
//        int neighbourMaxY = y == this.height - 1 ? 0 : y + 1;
//
//        liveNeighbourCount += this.grid[ neighbourMinX ][ neighbourMinY ];
//        liveNeighbourCount += this.grid[ x ][ neighbourMinY ];
//        liveNeighbourCount += this.grid[ neighbourMaxX ][ neighbourMinY ];
//
//        liveNeighbourCount += this.grid[ neighbourMinX ][ y ];
//        liveNeighbourCount += this.grid[ neighbourMaxX ][ y ];
//
//        liveNeighbourCount += this.grid[ neighbourMinX ][ neighbourMaxY ];
//        liveNeighbourCount += this.grid[ x ][ neighbourMaxY ];
//        liveNeighbourCount += this.grid[ neighbourMaxX ][ neighbourMaxY ];

        return liveNeighbourCount;
    }

    @Override
    public int getCellValue( int x, int y )
    {
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
    public int getWidth()
    {
        return this.width;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }

    @Override
    public void setCellValue( int x, int y, int value )
    {
        this.grid[ x ][ y ] = value;
    }

    @Override
    public void copy( Grid toGrid )
    {
        for ( int y = 0; y < this.height; y ++ )
        {
            for ( int x = 0; x < this.width; x ++ )
            {
                toGrid.setCellValue( x, y, this.grid[ x ][ y ] );
            }
        }
    }
}
