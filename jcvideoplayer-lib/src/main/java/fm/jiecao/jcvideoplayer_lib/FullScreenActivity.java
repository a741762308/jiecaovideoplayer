package fm.jiecao.jcvideoplayer_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import de.greenrobot.event.EventBus;

/**
 * Created by Nathen
 * On 2015/12/01 11:17
 */
public class FullScreenActivity extends Activity {
    public static void toActivity(Context context, int type) {
        TYPE = type;
        Intent intent = new Intent(context, FullScreenActivity.class);
        context.startActivity(intent);
    }

    JCVideoView jcVideoView;
    public static String URL = "";
    /**
     * 刚启动全屏时的播放状态
     */
    public static int TYPE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fullscreen);
        jcVideoView = (JCVideoView) findViewById(R.id.jcvideoview);
        jcVideoView.setFullScreen(true);
        //TODO 来到全屏之后继续之前的播放
        //TODO 取得之前的播放状态，和地址
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        jcVideoView.goOn();
//                    }
//                });
//            }
//        }).start();

    }

    public void onEventMainThread(VideoEvents videoEvents) {
        if (videoEvents.type == VideoEvents.VE_SURFACEHOLDER_CREATED) {
            jcVideoView.goOn();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}