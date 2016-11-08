package com.example.jagdeepsingh.samplepro.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;

public class HandlerThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = HandlerThreadActivity.class.getSimpleName();
    TextView    thread1_text_tv;
    TextView    thread2_text_tv;
    Button      thread1_btn;
    Button      thread2_btn;
    Button      thread_a_to_b_btn;
    Handler     handler;
    // Handler threads with no need of Looper methods
    HandlerThread1     handlerThread1;
    HandlerThread2     handlerThread2;

    Looper handthread1Looper;
    Handler Thread1handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        initializeIds();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();

                /**
                 * Values coming from the thread1 and thread2
                 */
                if(bundle.containsKey("i"))
                    thread1_text_tv.setText(bundle.getInt("i")+"");
                else if(bundle.containsKey("j"))
                    thread2_text_tv.setText(bundle.getInt("j")+"");
            }
        };

        handlerThread1  =   new HandlerThread1("handlerThread1");
        handlerThread2  =   new HandlerThread2("handlerThread2");
        handlerThread1.start();
        handthread1Looper = handlerThread1.getLooper();
        Thread1handler  = new Handler(handthread1Looper);
//        handlerThread1.prepareHandler();
//        handlerThread2.start();
    }

    private void initializeIds() {
        thread1_text_tv = (TextView)findViewById(R.id.thread1_text_tv);
        thread2_text_tv = (TextView)findViewById(R.id.thread2_text_tv);
        thread_a_to_b_btn = (Button)findViewById(R.id.thread_a_to_b_btn);
        thread_a_to_b_btn.setOnClickListener(this);
        thread1_btn     = (Button)findViewById(R.id.thread1_btn    );
        thread1_btn.setOnClickListener(this);
        thread2_btn     = (Button)findViewById(R.id.thread2_btn    );
        thread2_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thread1_btn:
                /**
                 * Sending Message to Thread1 from MainUi thread
                 */
                Bundle bundle = new Bundle();
                bundle.putString("MSG1_FROM_UI","msg1 from UI ");
                Message msg = new Message();
                msg.setData(bundle);
//                handlerThread1.thread1Handler.sendMessage(msg);
                
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG," This is Task Message from the Handler thread Activity");
                    }
                };
                Thread1handler.post(runnable);
                break;
            case R.id.thread2_btn:
                /**
                 * Sending Message to Thread2 from MainUi thread
                 */
                Bundle bundle2 = new Bundle();
                bundle2.putString("MSG2_FROM_UI","msg2 from UI ");
                Message msg2 = new Message();
                msg2.setData(bundle2);
                handlerThread2.thread2Handler.sendMessage(msg2);
                break;
            case R.id.thread_a_to_b_btn:
                Message msg3 = new Message();
                msg3.arg1 = 12;
//                handlerThread1.sendMessageToThreadB(msg3);
        }
    }
}
