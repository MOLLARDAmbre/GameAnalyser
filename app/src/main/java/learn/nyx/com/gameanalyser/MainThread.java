package learn.nyx.com.gameanalyser;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * The thread we will use.
 * Will be remade when I know how to do it
 */
public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private AppPanel appPanel;
    private boolean running;
    private static Canvas c;


    public MainThread(SurfaceHolder sh, AppPanel gp) {
        super();
        this.surfaceHolder = sh;
        this.appPanel = gp;

    }


    public void setRunning(boolean running) {
        this.running = running;
    }


    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            c = null;
            try {
                c = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.appPanel.update();
                    this.appPanel.draw(c);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (c != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount ++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                //System.out.println(averageFPS);
            }
        }
    }


}

