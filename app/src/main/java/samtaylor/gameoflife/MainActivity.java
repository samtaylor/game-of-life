package samtaylor.gameoflife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import samtaylor.gameoflife.grid.BoundedGameGrid;
import samtaylor.gameoflife.grid.InfiniteGameGrid;
import samtaylor.gameoflife.presenter.GamePresenter;
import samtaylor.gameoflife.presenter.GamePresenterBuilder;
import samtaylor.gameoflife.renderer.SurfaceViewRenderer;

public class MainActivity extends AppCompatActivity
{
    private GamePresenter gamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if ( this.gamePresenter == null ) {
            final TextView tickCount = (TextView) this.findViewById(R.id.tick_count);
            final SurfaceView surfaceView = (SurfaceView) this.findViewById(R.id.surface_view);

            surfaceView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    surfaceView.getViewTreeObserver().removeOnGlobalLayoutListener( this );

                    int width = surfaceView.getWidth();
                    int height = surfaceView.getHeight();

                    gamePresenter = new GamePresenterBuilder()
                            .with(new InfiniteGameGrid( width / SurfaceViewRenderer.CELL_SIZE, height / SurfaceViewRenderer.CELL_SIZE ) )
                            .with(new SurfaceViewRenderer(tickCount, surfaceView))
                            .build();
                    gamePresenter.resume();
                }
            });

        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if ( this.gamePresenter != null ) {
            this.gamePresenter.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( this.gamePresenter != null ) {
            this.gamePresenter.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate( R.menu.main, menu );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case R.id.menu_refresh:
            {
                this.gamePresenter.start();

                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
