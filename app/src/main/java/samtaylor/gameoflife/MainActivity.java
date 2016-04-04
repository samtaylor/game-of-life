package samtaylor.gameoflife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.ViewTreeObserver;

import samtaylor.gameoflife.grid.InfiniteGameGrid;
import samtaylor.gameoflife.presenter.AndroidGamePresenter;
import samtaylor.gameoflife.presenter.AndroidGamePresenterBuilder;
import samtaylor.gameoflife.renderer.SurfaceViewRenderer;

public class MainActivity extends AppCompatActivity implements MainScene
{
    private AndroidGamePresenter gamePresenter;

    private MenuItemClickListener refreshMenuItemClickListener;
    private LifecycleListener lifecycleListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if ( this.gamePresenter == null ) {
            final SurfaceView surfaceView = (SurfaceView) this.findViewById(R.id.surface_view);

            surfaceView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    surfaceView.getViewTreeObserver().removeOnGlobalLayoutListener( this );

                    int width = surfaceView.getWidth();
                    int height = surfaceView.getHeight();

                    gamePresenter = new AndroidGamePresenterBuilder()
                            .with(new InfiniteGameGrid( width / SurfaceViewRenderer.CELL_SIZE, height / SurfaceViewRenderer.CELL_SIZE ) )
                            .with(new SurfaceViewRenderer(surfaceView))
                            .build();

                    new MainPresenter( MainActivity.this, gamePresenter );
                }
            });
        }
        else
        {
            this.lifecycleListener.resumed();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        this.lifecycleListener.paused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.lifecycleListener.destroyed();
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
                this.refreshMenuItemClickListener.clicked();

                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void addRefreshMenuItemClickListener( MenuItemClickListener menuItemClickListener )
    {
        this.refreshMenuItemClickListener = menuItemClickListener;
    }

    @Override
    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }
}
