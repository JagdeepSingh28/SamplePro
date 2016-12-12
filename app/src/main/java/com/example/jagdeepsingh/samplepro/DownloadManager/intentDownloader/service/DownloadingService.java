package com.example.jagdeepsingh.samplepro.DownloadManager.intentDownloader.service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.example.jagdeepsingh.samplepro.DownloadManager.intentDownloader.model.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.Build.ID;

/**
 * Created by jagdeep.singh on 12-12-2016.
 */

public class DownloadingService extends IntentService {

    public static String PROGRESS_UPDATE_ACTION = DownloadingService.class.getSimpleName() + "progress_update";

    public static String CANCEL_UPDATE_ACTION = DownloadingService.class.getSimpleName() + "progress_update";

    private boolean mIsAlreadyRunning;
    private boolean mReceiversRegistered;

    private ExecutorService mExec;
    private CompletionService<NoResultType> mEcs;
    private LocalBroadcastManager mBroadcastManager;
    private List<DownloadTask> mTasks;

    private static final long INTERVAL_BROADCAST = 800;
    private long mLastUpdate = 0;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadingService(String name) {
        super("DownloadingService");
        mExec = Executors.newFixedThreadPool( /* only 5 at a time */5);
        mEcs = new ExecutorCompletionService<NoResultType>(mExec);
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        mTasks = new ArrayList<DownloadTask>();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mIsAlreadyRunning) {
            publishCurrentProgressOneShot(true);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (mIsAlreadyRunning) {
            return;
        }
        mIsAlreadyRunning = true;

        ArrayList<File> files = intent.getParcelableArrayListExtra("files");
        final Collection<DownloadTask> tasks = mTasks;
        int index = 0;
        for (File file : files) {
            DownloadTask yt1 = new DownloadTask(index++, file);
            tasks.add(yt1);
        }

        for (DownloadTask t : tasks) {
            mEcs.submit(t);
        }
        // wait for finish
        int n = tasks.size();
        for (int i = 0; i < n; ++i) {
            NoResultType r;
            try {
                r = mEcs.take().get();
                if (r != null) {
                    // use you result here
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // send a last broadcast
        publishCurrentProgressOneShot(true);
        mExec.shutdown();
    }

    private synchronized void publishProgress(int position, int progress) {
        Intent i = new Intent();
        i.setAction(PROGRESS_UPDATE_ACTION);
        i.putExtra("progress", progress);
        i.putExtra("position", position);
        mBroadcastManager.sendBroadcast(i);
    }

    private void publishCurrentProgressOneShot(boolean forced) {
        if (forced
                || System.currentTimeMillis() - mLastUpdate > INTERVAL_BROADCAST) {
            mLastUpdate = System.currentTimeMillis();
            final List<DownloadTask> tasks = mTasks;
            int[] positions = new int[tasks.size()];
            int[] progresses = new int[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                DownloadTask t = tasks.get(i);
                positions[i] = t.mPosition;
                progresses[i] = t.mProgress;
            }
            publishProgress(positions, progresses);
        }
    }

    private void publishCurrentProgressOneShot() {
        publishCurrentProgressOneShot(false);
    }

    private synchronized void publishProgress(int[] positions,
                                              int[] progresses) {
        Intent i = new Intent();
        i.setAction(PROGRESS_UPDATE_ACTION);
        i.putExtra("position", positions);
        i.putExtra("progress", progresses);
        i.putExtra("oneshot", true);
        mBroadcastManager.sendBroadcast(i);
    }

    // following methods can also be used but will cause lots of broadcasts
    private void publishCurrentProgress() {
        final Collection<DownloadTask> tasks = mTasks;
        for (DownloadTask t : tasks) {
            publishProgress(t.mPosition, t.mProgress);
        }
    }

    class DownloadTask implements Callable<NoResultType> {
        private int mPosition;
        private int mProgress;
        private boolean mCancelled;
        private final File mFile;
        private Random mRand = new Random();

        public DownloadTask(int position, File file) {
            mPosition = position;
            mFile = file;
        }

        @Override
        public NoResultType call() throws Exception {
            while (mProgress < 100 && !mCancelled) {
                mProgress += mRand.nextInt(5);
                Thread.sleep(mRand.nextInt(500));

                // publish progress
                publishCurrentProgressOneShot();

                // we can also call publishProgress(int position, int
                // progress) instead, which will work fine but avoid broadcasts
                // by aggregating them

                // publishProgress(mPosition,mProgress);
            }
            return new NoResultType();
        }

        public int getProgress() {
            return mProgress;
        }

        public int getPosition() {
            return mPosition;
        }

        public void cancel() {
            mCancelled = true;
        }
    }

    private void registerReceiver() {
        unregisterReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadingService.CANCEL_UPDATE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mCommunicationReceiver, filter);
        mReceiversRegistered = true;
    }

    private void unregisterReceiver() {
        if (mReceiversRegistered) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(
                    mCommunicationReceiver);
            mReceiversRegistered = false;
        }
    }

    private final BroadcastReceiver mCommunicationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(
                    DownloadingService.CANCEL_UPDATE_ACTION)) {
                final long id = intent.getLongExtra(ID, -1);
                if (id != -1) {
                    for (DownloadTask task : mTasks) {
                        if (task.mFile.getId() == id) {
                            task.cancel();
                            break;
                        }
                    }
                }
            }
        }
    };

    class NoResultType {
    }
}
