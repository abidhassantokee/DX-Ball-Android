package tokee.test.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View {

    Paint paint;
    static Canvas myCanvas;
    static Handler mHandler = new Handler();
    public static boolean firstTime=true,gameView=false,gameOver,gamePause,otherThreadRunning;
    public static int score=0;

    public void setBrickColor(int n)
    {
        switch(n%7)
        {
            case 0:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(103, 58, 183));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 1:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(63, 81, 181));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 2:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(33, 150, 243));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 3:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(244, 67, 64));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 4:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(233, 33, 99));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 5:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(156, 39, 176));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
            case 6:
                if(Brick.getBrickType(n)==1)
                {
                    paint.setColor(Color.rgb(0, 188, 212));
                }
                else
                {
                    paint.setColor(Color.rgb(255, 193, 7));
                }
                break;
        }
    }

    public void setAllBricks(Canvas canvas)
    {
        int n=0,rowFirstN=0,rowLastN=Brick.getBrickPerRow(),count=0;
        boolean firstTime = true;
        while(n<Brick.getNumRowLevel())
        {
            if(firstTime)
            {
                Brick.setRowLevelStartY(n,Brick.getBrickStartY());
                firstTime = false;
            }
            else
            {
                Brick.setRowLevelStartY(n,(Brick.getBrickHeight()+Brick.getBrickSpaceY()));
            }
            Brick.setBrickX1(rowFirstN,Brick.getBrickStartX());
            Brick.setBrickX2(rowFirstN,(Brick.getBrickX1(rowFirstN)+Brick.getBrickWidth()));
            Brick.setBrickY1(rowFirstN,Brick.getRowLevelY(n));
            Brick.setBrickY2(rowFirstN,(Brick.getRowLevelY(n)+Brick.getBrickHeight()));
            // Log.d("Brick Value "+rowFirstN+": ",Brick.getBrickX1(rowFirstN)+","+Brick.getBrickY1(rowFirstN)+","+Brick.getBrickX2(rowFirstN)+","+Brick.getBrickY2(rowFirstN));
            setBrickColor(count);
            canvas.drawRect(Brick.getBrickX1(rowFirstN),Brick.getBrickY1(rowFirstN),Brick.getBrickX2(rowFirstN),Brick.getBrickY2(rowFirstN), paint);
            count++;
            for (int i=(rowFirstN+1);i<rowLastN;i++)
            {
                Brick.setBrickX1(i,Brick.getBrickX2(i-1)+Brick.getBrickSpaceX());
                Brick.setBrickX2(i,(Brick.getBrickX1(i)+Brick.getBrickWidth()));
                Brick.setBrickY1(i,Brick.getRowLevelY(n));
                Brick.setBrickY2(i,(Brick.getRowLevelY(n)+Brick.getBrickHeight()));
                // Log.d("Brick Value "+i+": ",Brick.getBrickX1(i)+","+Brick.getBrickY1(i)+","+Brick.getBrickX2(i)+","+Brick.getBrickY2(i));
                setBrickColor(count);
                canvas.drawRect(Brick.getBrickX1(i),Brick.getBrickY1(i),Brick.getBrickX2(i),Brick.getBrickY2(i), paint);
                count++;
            }
            n++;
            rowFirstN=rowFirstN+Brick.getBrickPerRow();
            rowLastN=rowLastN+Brick.getBrickPerRow() ;
        }
        Brick.setBrickDrawn(true);
    }

    public void drawBricks(Canvas canvas)
    {
        int count=0;
        for (int i=0; i<Brick.getNBricks(); i++)
        {
            setBrickColor(count);
            if(Brick.getBrickStatus(i) == true)
            {
                canvas.drawRect(Brick.getBrickX1(i),Brick.getBrickY1(i),Brick.getBrickX2(i),Brick.getBrickY2(i), paint);
            }
            count++;
        }
    }

    public void drawBall(Canvas canvas)
    {
        paint.setColor(Color.rgb(255, 255, 255));
        if(Math.abs((Ball.getBall_dx()))>Ball.getBallMoveSpeed()&&Math.abs((Ball.getBall_dy()))>Ball.getBallMoveSpeed())
        {
            paint.setColor(Color.rgb(255, 87, 34));
        }
        paint.setStyle(Style.FILL);
        canvas.drawCircle(Ball.getBallX(),Ball.getBallY(),Ball.getBallR(), paint);
    }

    public void drawPaddle(Canvas canvas)
    {
        paint.setColor(Color.rgb(255, 23, 68));
        canvas.drawRect(Paddle.getPaddleX1(),Paddle.getPaddleY1(),Paddle.getPaddleX2(),Paddle.getPaddleY2(), paint);
    }

    public void drawString(Canvas canvas)
    {
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setTextSize(40);
        if(gameOver)
        {
            paint.setColor(Color.rgb(255, 23, 68));
            paint.setTextSize(80);
            canvas.drawText("Touch Here to Retry", (canvas.getWidth()/2)-330, canvas.getHeight()-250, paint);
            paint.setColor(Color.rgb(255, 255, 255));
            paint.setTextSize(50);
            canvas.drawText("Or Press Back Button to Select Stage", (canvas.getWidth()/2)-385, canvas.getHeight()-150, paint);
            paint.setTextSize(40);
            canvas.drawText("Game Over", (canvas.getWidth()/2)-80, canvas.getHeight()-70, paint);
        }
        canvas.drawText("Score : "+score, (canvas.getWidth()/2)-80, canvas.getHeight()-20, paint);
    }

    static Runnable brickThread = new Runnable() {
        @Override
        public void run() {
            otherThreadRunning =true;
            Brick.checkBrickStatus();
            if(!gameOver && !gamePause)
                mHandler.postDelayed(brickThread, 10);
            else
                otherThreadRunning =false;
        }
    };

    static Runnable ballThread = new Runnable() {
        @Override
        public void run() {
            otherThreadRunning =true;
            Ball.calculateBallNextPos(myCanvas);
            if(!gameOver && !gamePause)
                mHandler.postDelayed(ballThread, 10);
            else
                otherThreadRunning =false;
        }
    };

    public static void startOtherThreads()
    {
        mHandler.postDelayed(ballThread, 10);
        mHandler.postDelayed(brickThread, 10);
    }


    public void onDraw(Canvas canvas) {
        canvas.drawRGB(0, 0, 0);
        if(firstTime)
        {
            firstTime=false;
            gameOver=false;
            gamePause=false;
            gameView=true;
            otherThreadRunning =false;
            Paddle.setPaddle(canvas);
            Ball.setBall(canvas);
            Brick.setBrickProperties();
            setAllBricks(canvas);
            myCanvas = canvas;
            startOtherThreads();
        }
        if(!gameOver)
        {
            drawBall(canvas);
            drawPaddle(canvas);
        }
        drawBricks(canvas);
        drawString(canvas);
        invalidate();
    }

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                float xTouch = event.getX();
                float yTouch = event.getY();
                float paddleMidpoint=(float)((Paddle.getPaddleX1()+Paddle.getPaddleX2())/2);
                if(!gameOver)
                {
                    if(yTouch>=(Paddle.getPaddleY1()-80) && yTouch<=(Paddle.getPaddleY2()+80))
                    {
                        if (xTouch < paddleMidpoint && xTouch >= Paddle.getPaddleX1()) {
                            while(xTouch<=(paddleMidpoint-Paddle.getPaddleMoveSpeed()) && Paddle.getPaddleX1()>=0)
                            {
                                Paddle.setPaddleX1(Paddle.getPaddleX1()-Paddle.getPaddleMoveSpeed());
                                Paddle.setPaddleX2(Paddle.getPaddleX2()-Paddle.getPaddleMoveSpeed());
                                paddleMidpoint=(float)((Paddle.getPaddleX1()+Paddle.getPaddleX2())/2);
                                Paddle.updatePaddlePortion();
                            }
                        } else if (xTouch > paddleMidpoint && xTouch <= Paddle.getPaddleX2()) {
                            while(xTouch>=paddleMidpoint+Paddle.getPaddleMoveSpeed() && Paddle.getPaddleX2()<=v.getWidth())
                            {
                                Paddle.setPaddleX1(Paddle.getPaddleX1()+Paddle.getPaddleMoveSpeed());
                                Paddle.setPaddleX2(Paddle.getPaddleX2()+Paddle.getPaddleMoveSpeed());
                                paddleMidpoint=(float)((Paddle.getPaddleX1()+Paddle.getPaddleX2())/2);
                                Paddle.updatePaddlePortion();
                            }
                        }
                    }

                }
                else
                {
                    if(xTouch>=630&&xTouch<=1350)
                    {
                        if(yTouch<=830&&yTouch>=750)
                        {
                            firstTime=true;
                        }
                    }
                }
                return true;
            }
        });
    }

}

