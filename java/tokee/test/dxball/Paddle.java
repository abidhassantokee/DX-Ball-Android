package tokee.test.dxball;

import android.graphics.Canvas;

public class Paddle {

    private static float paddleX1,paddleX2,paddleY1,paddleY2,paddleWidth,paddleMoveSpeed,paddlePortion1,paddlePortion2,paddlePortion3,paddlePortion4,paddlePortion5;

    public static void setPaddle(Canvas canvas)
    {
        paddleWidth=canvas.getWidth()/6;
        paddleX1=(canvas.getWidth()/2)-(paddleWidth/2);
        paddleX2=(canvas.getWidth()/2)+(paddleWidth/2);
        paddleY1=canvas.getHeight()-160;
        paddleY2=paddleY1+40;
        paddleMoveSpeed=1;
        updatePaddlePortion();
    }

    public static float getPaddleX1() { return paddleX1; }

    public static float getPaddleX2()
    {
        return paddleX2;
    }

    public static float getPaddleY1()
    {
        return paddleY1;
    }

    public static float getPaddleY2()
    {
        return paddleY2;
    }

    public static float getPaddleMoveSpeed()
    {
        return paddleMoveSpeed;
    }

    public static void setPaddleX1(float xPoint)
    {
        paddleX1=xPoint;
    }

    public static void setPaddleX2(float xPoint)
    {
        paddleX2=xPoint;
    }

    public static void updatePaddlePortion()
    {
        paddlePortion1=paddleX1;
        paddlePortion2=paddleX1+(paddleWidth/5);
        paddlePortion3=paddlePortion2+(paddleWidth/5);
        paddlePortion4=paddlePortion3+(paddleWidth/5);
        paddlePortion5=paddlePortion4+(paddleWidth/5);
    }

    public static float getPaddlePortion1()
    {
        return paddlePortion1;
    }

    public static float getPaddlePortion2()
    {
        return paddlePortion2;
    }

    public static float getPaddlePortion3()
    {
        return paddlePortion3;
    }

    public static float getPaddlePortion4()
    {
        return paddlePortion4;
    }

    public static float getPaddlePortion5()
    {
        return paddlePortion5;
    }

}

