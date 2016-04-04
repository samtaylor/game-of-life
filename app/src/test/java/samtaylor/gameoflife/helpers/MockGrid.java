package samtaylor.gameoflife.helpers;

import grid.Grid;

import static junit.framework.Assert.fail;

public class MockGrid implements Grid
{
    private boolean isAlive;
    private int numberOfChildren;

    public MockGrid(boolean isAlive, int numberOfChildren) {
        this.isAlive = isAlive;
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public boolean isAlive(int x, int y) {
        return this.isAlive;
    }

    @Override
    public int getNumberOfLiveChildren(int x, int y) {
        return this.numberOfChildren;
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
        fail();
    }

    @Override
    public void copy(Grid toGrid) {
        fail();
    }
}
