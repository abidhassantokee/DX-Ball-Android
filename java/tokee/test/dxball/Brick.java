package tokee.test.dxball;

public class Brick {

    private static int nBricks,nRowLevel,perBrickPoint,brickPerRow,type2BrickVal1,type2BrickVal2,bonusPoint;
    private static float brickStartX,brickStartY,brickWidth,brickHeight,brickSpaceX,brickSpaceY;
    private static float [] rowLevelStartY,brickX1,brickX2,brickY1,brickY2;
    private static boolean [] brickStatus;
    private static boolean brickDrawn;
    private static int [] brickType;

    public static void setStageProperties(int stage_nBricks, int stage_nRowLevel, int stage_perBrickPoint, int stage_brickPerRow, int stage_type2BrickVal1, int stage_type2BrickVal2, int stage_bonusPoint)
    {
        nBricks=stage_nBricks;
        nRowLevel=stage_nRowLevel;
        perBrickPoint=stage_perBrickPoint;
        brickPerRow=stage_brickPerRow;
        type2BrickVal1=stage_type2BrickVal1;
        type2BrickVal2=stage_type2BrickVal2;
        bonusPoint=stage_bonusPoint;
    }

    public static void setBrickProperties()
    {
        brickStartX=90;
        brickStartY=80;
        brickWidth=100;
        brickHeight=60;
        brickSpaceX=10;
        brickSpaceY=80;
        rowLevelStartY = new float[nRowLevel];
        brickX1 = new float[nBricks];
        brickX2 = new float[nBricks];
        brickY1 = new float[nBricks];
        brickY2 = new float[nBricks];
        brickStatus = new boolean[nBricks];
        brickType = new int[nBricks];
        for(int i=0;i<nBricks;i++)
        {
            brickStatus[i]=true;
        }
        int count=0,n=0;
        for(int i=0;i<nBricks;i++)
        {
           if(count==type2BrickVal1)
           {
               if(n<type2BrickVal2)
               {
                   brickType[i]=2;
                   n++;
               }
               else
               {
                   n=0;
                   count=1;
                   brickType[i]=1;
               }
           }
            else
           {
               count++;
               brickType[i]=1;
           }
        }
        brickDrawn=false;
    }
    public static boolean getBrickDrawn() { return brickDrawn; }

    public static int getNumRowLevel() { return nRowLevel; }

    public static int getNBricks() { return nBricks; }

    public static int getBrickPerRow() { return brickPerRow; }

    public static int getPerBrickPoint() { return perBrickPoint; }

    public static float getBrickStartX() { return brickStartX; }

    public static float getBrickStartY() { return brickStartY; }

    public static float getBrickWidth() { return brickWidth; }

    public static float getBrickHeight() { return brickHeight; }

    public static float getBrickSpaceX() { return brickSpaceX; }

    public static float getBrickSpaceY() { return brickSpaceY; }

    public static float getRowLevelY(int n) { return rowLevelStartY[n]; }

    public static float getBrickX1(int n) { return brickX1[n]; }

    public static float getBrickX2(int n) { return brickX2[n]; }

    public static float getBrickY1(int n) { return brickY1[n]; }

    public static float getBrickY2(int n) { return brickY2[n]; }

    public static boolean getBrickStatus(int n) { return brickStatus[n]; }

    public static int getBrickType(int n) { return brickType[n]; }

    public static void setRowLevelStartY(int n, float posY)
    {
        if(n==0)
        {
            rowLevelStartY[n]=posY;
        }
        else
        {
            rowLevelStartY[n]=rowLevelStartY[n-1]+posY;
        }

    }

    public static void setBrickX1(int n, float posX)
    {
        brickX1[n]=posX;
    }

    public static void setBrickX2(int n, float posX) { brickX2[n]=posX; }

    public static void setBrickY1(int n, float posY) { brickY1[n]=posY; }

    public static void setBrickY2(int n, float posY)
    {
        brickY2[n]=posY;
    }

    public static void setBrickDrawn(boolean val)
    {
        brickDrawn = val;
    }

    public static void setBrickStatus(int n, boolean val)
    {
        brickStatus[n] = val;
    }

    public static void setBrickType(int n, int val) { brickType[n] = val; }

    public static void checkBrickStatus()
    {
        boolean allBricksDestroyed = true;
        GameCanvas.score=0;
        for(int i=0; i<nBricks; i++)
        {
            if(brickStatus[i]==true)
            {
                allBricksDestroyed = false;
            }
            else
            {
                GameCanvas.score+=perBrickPoint;
            }
        }
        if(allBricksDestroyed==true)
        {
            GameCanvas.score+=bonusPoint;
            GameCanvas.gameOver=true;
        }
    }

}
