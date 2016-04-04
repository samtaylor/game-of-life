package grid;

public interface Grid
{
    int ALIVE = 1;
    int DEAD = 0;
    int INVALID = -1;

    void seed();

    boolean isAlive();
    boolean isAlive(int x, int y );

    int getNumberOfLiveChildren(int x, int y );
    int getCellValue( int x, int y );
    int getWidth();
    int getHeight();

    void setCellValue( int x, int y, int value );
    void copy( Grid toGrid );
}
