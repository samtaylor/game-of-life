package samtaylor.gameoflife.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import samtaylor.gameoflife.grid.Grid;

public class SurfaceViewRenderer implements Renderer, SurfaceHolder.Callback
{
    public static final int CELL_SIZE = 20;

    private static final int ORIGIN_X = 0;
    private static final int ORIGIN_Y = 0;

    private final SurfaceView surfaceView;
    private boolean readyToDraw;

    public SurfaceViewRenderer( SurfaceView surfaceView )
    {
        this.surfaceView = surfaceView;

        surfaceView.getHolder().addCallback( this );
    }

    @Override
    public void render( Grid grid, int tickCount )
    {
        if ( this.readyToDraw )
        {
            Canvas canvas = this.surfaceView.getHolder().lockCanvas();

            canvas.drawColor( 0xFF000000 );

            Paint fillPaint = new Paint();
            fillPaint.setColor(0xFFFFFFFF);
            fillPaint.setStyle(Paint.Style.FILL);
            fillPaint.setAlpha(128);

            Paint strokePaint = new Paint();
            strokePaint.setColor(0xFFFFFFFF);
            strokePaint.setStyle(Paint.Style.STROKE);

            int width = grid.getWidth();
            int height = grid.getHeight();

            for ( int y = 0; y < height; y ++ )
            {
                float yPos = ORIGIN_Y + ( y * CELL_SIZE);
                for ( int x = 0; x < width; x ++ )
                {
                    float xPos = ORIGIN_X + ( x * CELL_SIZE);
                    if ( grid.isAlive( x, y ) )
                    {
                        canvas.drawRect( xPos, yPos, xPos + CELL_SIZE, yPos + CELL_SIZE, fillPaint );
                        canvas.drawRect( xPos, yPos, xPos + CELL_SIZE, yPos + CELL_SIZE, strokePaint );
                    }
                }
            }

            this.surfaceView.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated( SurfaceHolder surfaceHolder )
    {
        this.readyToDraw = true;
    }

    @Override
    public void surfaceChanged( SurfaceHolder surfaceHolder, int format, int width, int height ) {}

    @Override
    public void surfaceDestroyed( SurfaceHolder surfaceHolder ) {}
}
