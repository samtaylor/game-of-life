package renderer;

import grid.Grid;

public class ConsoleRenderer implements Renderer
{
    @Override
    public void render( Grid grid, int tickCount )
    {
        System.out.println( "Tick Count: " + tickCount );

        for ( int y = 0; y < grid.getHeight(); y ++ )
        {
            for ( int x = 0; x < grid.getWidth(); x ++ )
            {
                System.out.print( grid.getCellValue( x, y ) );
                System.out.print( " " );
            }
            System.out.println( "" );
        }
    }
}
