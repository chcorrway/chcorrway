package TicTacToe.build1;

import processing.core.PApplet;

public class TicTacToeApp extends PApplet
{
    Grid[][] grid = new Grid[3][3];
    int size = 600;     // Universal Size :) 600 is default.
    int gridSize = size / 3 - 1;
    boolean xTurn;      //  X always starts first.
    int gameScreen = 0; // 0 = game;   1 = winScreen   /*This is done so that the mouse inputs can reset the game.
                                                                           /*|--> see mouseReleased() line:49.*/
    @Override
    public void settings()
    {
        size(size, size);
    }

    public void init()
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                grid[i][j].setXON('N');
            }
        }
        gameScreen = 0;
        xTurn = true;
    }

    @Override
    public void setup()
    {
        background(0);
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                grid[i][j] = new Grid(i * gridSize, j * gridSize, gridSize, gridSize);
            }
        }
        init();
    }



    @Override
    public void mouseReleased()
    {
        if (gameScreen == 0)
        {
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (grid[i][j].getXON() == 'N' &&
                            (mouseX > grid[i][j].getX() && mouseX < grid[i][j].getX() + grid[i][j].getSizeX()) &&
                            (mouseY > grid[i][j].getY() && mouseY < grid[i][j].getY() + grid[i][j].getSizeY()))
                    {
                        grid[i][j].mousePressed(xTurn);
                        grid[i][j].gridColor = color(90,90,90);
                        xTurn = !xTurn;
                    }
                }
            }
        }
        if (gameScreen == 1)    // When winScreen(char Player) /*line: 131*/ is activated a noLoop() is called
        {                           // and gameScreen is set to 1. By changing gameScreen to 1, it tells
            init();                 // mouseReleased() to reset and start looping draw() again when mouse input is
            loop();                 // activated.
        }
    }

    @Override
    public void draw()
    {
        if (gameScreen == 0)            //gameScreens are handy for menus too ;) but here it is not implemented.
        {
            for (int i = 0; i < 3; ++i)
            {
                for (int j = 0; j < 3; j++)
                {
                    grid[i][j].draw();
                    grid[i][j].drawPlayerInput();
                    flashyButtons(i, j);
                }
            }
            checkWinner();
        }
    }

    private void flashyButtons(int x, int y)
    {
        if((mouseX > grid[x][y].getX() && mouseX < grid[x][y].getX() + grid[x][y].getSizeX()) &&
                (mouseY > grid[x][y].getY() && mouseY < grid[x][y].getY() + grid[x][y].getSizeY()))
            grid[x][y].gridColor = color(60,60,60);
        else
            grid[x][y].gridColor = 0;// this is just for fun, does exactly what it says, don't pay too much attention.
    }

    public void checkWinner()
    {
        boolean win = false;
        char checkPlayer = 'X';
        for(int i = 0; i < 2; ++i)
        {
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
            checkPlayer = 'O';
        }
        checkPlayer = 'N';
        if ((grid[0][0].getXON() != checkPlayer && grid[0][1].getXON() != checkPlayer && grid[0][2].getXON() != checkPlayer) && //top to bottom from left to  right.
                (grid[1][0].getXON() != checkPlayer && grid[1][1].getXON() != checkPlayer && grid[1][2].getXON() != checkPlayer) &&
                (grid[2][0].getXON() != checkPlayer && grid[2][1].getXON() != checkPlayer && grid[2][2].getXON() != checkPlayer) && !win)
        {
            winScreen(checkPlayer);
        }
    }

    void winScreen(char Player)
    {
        noLoop();       // The noLoop() here is important for the opacity of rect and to stop draw from well...
        textAlign(CENTER, CENTER);  // drawing grids all over our lovely winScreen() :)
        textSize(gridSize / 2F);
        fill(0, 180);   //fill() has a second parameter for opacity --> see Processing Reference for more info.
        rect(grid[0][1].getX(), grid[0][1].getY(), gridSize * 3 + 4, gridSize);
        fill(255);
        if (Player == 'N')
            text("Draw!", width / 2F, height / 2F);
        else
            text(Player + " Wins!", width / 2F, height / 2F);
        gameScreen = 1;     // gameScreen now equals 1, mouseReleased has got the go ahead to reset the game
    }

    public static void main(String[] args)
    {
        PApplet.runSketch(new String[]{""}, new TicTacToeApp());
    }

    /*///////////////////////
    //////////Grid//////////
    /////////////////////*/

    class Grid
    {
        float x;
        float y;
        float sizeX;
        float sizeY;
        char XON;
        int gridColor = 0;

        public Grid(float x, float y, float sizeX, float sizeY)
        {
            this.x = x;
            this.y = y;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }

        public float getX()
        {
            return x;
        }

        public float getY()
        {
            return y;
        }

        public float getSizeX()
        {
            return sizeX;
        }

        public float getSizeY()
        {
            return sizeY;
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
            fill(gridColor);
            rect(x, y, sizeX, sizeY);   // Can be simplified with square(x,y,size) to get rid of variable sizeY.
        }                               // but decided to use rect(x,y,sizeX,sizeY) for more universal use. Menu
                                        // buttons for example.
        public void drawPlayerInput()
        {
            fill(255);
            if(XON != 'N')
                text(XON, x + sizeX / 2, y + sizeY / 2.5F);
        }

        public void mousePressed(boolean turn)
        {
            textAlign(CENTER, CENTER);
            textSize(sizeY);
            fill(255);
            if (turn)
                XON = 'X';
            if (!turn)
                XON = 'O';
        }
    }
}