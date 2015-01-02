package takuma.tutorial.activity;

import takuma.tutorial.exception.AnalyticsExceptionParser;

import com.androidquery.callback.BitmapAjaxCallback;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.ExceptionReporter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

public class TakumaBaseActivity extends FragmentActivity {
	private static String TAG = TakumaBaseActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
//		FlurryAgent.onStartSession(this, CLAppConfig.Flurry_KEY);
		Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (uncaughtExceptionHandler instanceof ExceptionReporter) {
          ExceptionReporter exceptionReporter = (ExceptionReporter) uncaughtExceptionHandler;
          exceptionReporter.setExceptionParser(new AnalyticsExceptionParser());
        }
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
//		FlurryAgent.onEndSession(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		AppEventsLogger.activateApp(this, getString(R.string.app_id));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	public final <E extends View> E getView(int id) {
		return (E) findViewById(id);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Runtime runtime = Runtime.getRuntime();
		long freeMemory = runtime.freeMemory();
		long maxMemory = runtime.maxMemory();
		long totalMemory = runtime.totalMemory();
		Log.v(TAG, "Free Memory = " + freeMemory);
		Log.v(TAG, "Max Memory = " + maxMemory);
		Log.v(TAG, "Total Memory = " + totalMemory);
		if (totalMemory > maxMemory * 2 / 10) {
			Log.v(TAG, "Total: " + totalMemory + "\nMax: " + maxMemory * 0.7);
			BitmapAjaxCallback.clearCache();
		}
		runtime.gc();
		System.gc();
	}

}
