package samtaylor.gameoflife.rules;

import samtaylor.gameoflife.grid.Grid;

public interface Rule
{
    void applyRule(int x, int y, Grid fromGrid, Grid toGrid );
}
