/*package TicTacToe.build1;

public class Grid
{
    float x;
    float y;
    int size;
    char XON;

    public Grid(float x, float y, int size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getSize()
    {
        return size;
    }

    public char getXON()
    {
        return XON;
    }

    public void setXON(char nXON)
    {
        XON = nXON;
    }

    public void draw()
    {
        stroke(255);
        noFill();
        square(x, y, size);
    }

    public void mousePressed(boolean turn)
    {
        textAlign(CENTER, CENTER);
        textSize(size);
        fill(255);
        if (turn)
            XON = 'X';
        if (!turn)
            XON = 'O';
        text(XON, x + size / 2F, y + size / 2.5F);
    }
}











if ((grid[0][0].getXON() == checkPlayer && grid[0][1].getXON() == checkPlayer && grid[0][2].getXON() == checkPlayer) || //top to bottom from left to  right.
        (grid[1][0].getXON() == checkPlayer && grid[1][1].getXON() == checkPlayer && grid[1][2].getXON() == checkPlayer) ||
        (grid[2][0].getXON() == checkPlayer && grid[2][1].getXON() == checkPlayer && grid[2][2].getXON() == checkPlayer) ||
        (grid[0][0].getXON() == checkPlayer && grid[1][0].getXON() == checkPlayer && grid[2][0].getXON() == checkPlayer) || //left to right from top to bottom.
        (grid[0][1].getXON() == checkPlayer && grid[1][1].getXON() == checkPlayer && grid[2][1].getXON() == checkPlayer) ||
        (grid[0][2].getXON() == checkPlayer && grid[1][2].getXON() == checkPlayer && grid[2][2].getXON() == checkPlayer) ||
        (grid[0][0].getXON() == checkPlayer && grid[1][1].getXON() == checkPlayer && grid[2][2].getXON() == checkPlayer) || // diagonals right to left from top to bottom
        (grid[0][2].getXON() == checkPlayer && grid[1][1].getXON() == checkPlayer && grid[2][0].getXON() == checkPlayer))
        {
        winScreen(checkPlayer);
        win = true;
        }
        checkPlayer = 'O';*/