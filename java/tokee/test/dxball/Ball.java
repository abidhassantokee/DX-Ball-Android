package tokee.test.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class Ball {

    private static float ballX,ballY,ballR,ball_dx,ball_dy,ballMoveSpeed;
    private static Context ballContext;
    public static MediaPlayer ballSound=null;

    public static void setBall(Canvas canvas)
    {
        ballR=20;
        ballMoveSpeed=5;
        ball_dx=ballMoveSpeed;
        ball_dy=ballMoveSpeed;
        ballX=canvas.getWidth()/2;
        ballY=Paddle.getPaddleY1()-ballR;
        ballSound = MediaPlayer.create(ballContext, R.raw.bsound);
    }

    public static float getBallX()
    {
        return ballX;
    }

    public static float getBallY()
    {
        return ballY;
    }

    public static float getBallR()
    {
        return ballR;
    }

    public static float getBall_dx()
    {
        return ball_dx;
    }

    public static float getBall_dy()
    {
        return ball_dy;
    }

    public static float getBallMoveSpeed()
    {
        return ballMoveSpeed;
    }

    public static void calculateBallNextPos(Canvas canvas){
        if(ballY==ballR)
        {
            ball_dy=-ball_dy;
        }
        if(ballY==Paddle.getPaddleY1()-ballR)
        {
            if((ballX+ballR)>Paddle.getPaddleX1()&&(ballX-ballR)<=Paddle.getPaddleX2())
            {
                playBallSound();
                if(ballX<Paddle.getPaddlePortion2())
                {
                    ball_dx=-ballMoveSpeed*2;
                    ball_dy=-ballMoveSpeed*2;
                }
                else if(ballX<Paddle.getPaddlePortion3())
                {
                    ball_dx=-ballMoveSpeed;
                    ball_dy=-ballMoveSpeed;
                }
                else if(ballX<Paddle.getPaddlePortion4())
                {
                    ball_dx=0;
                    ball_dy=-ballMoveSpeed;
                }
                else if(ballX<Paddle.getPaddlePortion5())
                {
                    ball_dx=ballMoveSpeed;
                    ball_dy=-ballMoveSpeed;
                }
                else
                {
                    ball_dx=ballMoveSpeed*2;
                    ball_dy=-ballMoveSpeed*2;
                }
            }
        }
        for(int i =0; i<Brick.getNBricks(); i++)
        {
            if((ballY-ballR)==Brick.getBrickY2(i))
            {
                if((ballX+ballR)>Brick.getBrickX1(i) && (ballX-ballR)<Brick.getBrickX2(i) && Brick.getBrickStatus(i)==true)
                {
                    if(ball_dy<0)
                        ball_dy=-ball_dy;
                    if(Brick.getBrickType(i)==1)
                        Brick.setBrickStatus(i,false);
                    else
                        Brick.setBrickType(i,1);
                }
            }
            if((ballY+ballR)==Brick.getBrickY1(i))
            {
                if((ballX+ballR)>Brick.getBrickX1(i) && (ballX-ballR)<Brick.getBrickX2(i) && Brick.getBrickStatus(i)==true)
                {
                    if(ball_dy>0)
                        ball_dy=-ball_dy;
                    if(Brick.getBrickType(i)==1)
                        Brick.setBrickStatus(i,false);
                    else
                        Brick.setBrickType(i,1);
                }
            }
            if(((ballX-ballR)==Brick.getBrickX2(i)))
            {
                if((((ballY-ballR)<=Brick.getBrickY2(i)&&(ballY-ballR)>=Brick.getBrickY1(i)) || ((ballY+ballR)>=Brick.getBrickY1(i)&&(ballY+ballR)<=Brick.getBrickY2(i))) && Brick.getBrickStatus(i)==true)
                {
                    if(ball_dx<0)
                        ball_dx=-ball_dx;
                    if(Brick.getBrickType(i)==1)
                        Brick.setBrickStatus(i,false);
                    else
                        Brick.setBrickType(i,1);
                }
            }
            if(((ballX+ballR)==Brick.getBrickX1(i)))
            {
                if((((ballY-ballR)<=Brick.getBrickY2(i)&&(ballY-ballR)>=Brick.getBrickY1(i)) || ((ballY+ballR)>=Brick.getBrickY1(i)&&(ballY+ballR)<=Brick.getBrickY2(i))) && Brick.getBrickStatus(i)==true)
                {
                    if(ball_dx>0)
                        ball_dx=-ball_dx;
                    if(Brick.getBrickType(i)==1)
                        Brick.setBrickStatus(i,false);
                    else
                        Brick.setBrickType(i,1);
                }
            }
        }
        if(ballX==ballR)
        {
            ball_dx=-ball_dx;
        }
        if(ballX==canvas.getWidth()-ballR)
        {
            ball_dx=-ball_dx;
        }
        if(ballY>(Paddle.getPaddleY1()-ballR))
        {
            GameCanvas.gameOver=true;
        }
        ballY+=ball_dy;
        ballX+=ball_dx;
    }
    public static void setContext(Context c)
    {
        ballContext = c;
    }
    public static void playBallSound()
    {
        ballSound.start();
    }

}
