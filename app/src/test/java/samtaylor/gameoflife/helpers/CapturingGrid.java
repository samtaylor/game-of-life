package samtaylor.gameoflife.helpers;

import grid.Grid;

import static junit.framework.Assert.fail;

public class CapturingGrid implements Grid
{
    public int setCellValueInvokedForX = -1;
    public int setCellValueInvokedForY = -1;
    public int setCellValueInvokedWithValue = -1;

    @Override
    public void seed() {
        fail();
    }

    @Override
    public boolean isAlive() {
        fail();
        return false;
    }

    @Override
    public boolean isAlive(int x, int y) {
        fail();
        return false;
    }

    @Override
    public int getNumberOfLiveChildren(int x, int y) {
        fail();

        return 0;
    }

    @Override
    public int getCellValue(int x, int y)
    {
        fail();
        return 0;
    }

    @Override
    public int getWidth()
    {
        fail();
        return 0;
    }

    @Override
    public int getHeight()
    {
        fail();
        return 0;
    }

    @Override
    public void setCellValue(int x, int y, int value) {
        this.setCellValueInvokedForX = x;
        this.setCellValueInvokedForY = y;
        this.setCellValueInvokedWithValue = value;
    }

    @Override
    public void copy(Grid toGrid) {
        fail();
    }
}
