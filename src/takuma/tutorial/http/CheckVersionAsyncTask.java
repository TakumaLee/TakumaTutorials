package takuma.tutorial.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout.LayoutParams;

public class CheckVersionAsyncTask extends AsyncTask<String, Void, Boolean> {
	
	Context context;
	String currentVersion;
	String oldVersion;
	
	public CheckVersionAsyncTask(Context context, String version) {
		this.context = context;
		this.oldVersion = version;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		boolean result = false;
		String url = params[0];
        
        HttpPost post = new HttpPost(url);

        HttpClient client = AndroidHttpClient.newInstance("android");
        
        BufferedReader reader = null; 
        try {
            HttpResponse reponse = client.execute(post);
            
            reader = new BufferedReader(new InputStreamReader(reponse.getEntity().getContent()));
            String line;
            Pattern p = Pattern.compile("\"softwareVersion\"\\W*([\\d\\.]+)");
            while( ( line = reader.readLine() ) != null ){
                Matcher matcher = p.matcher(line);
                if( matcher.find() ){
                    Log.v("ids", "ver.: " + matcher.group(1) );
                    currentVersion = matcher.group(1);
                }
            }
            if (currentVersion == null)
            	return false;
            if (currentVersion.compareTo(oldVersion) > 0)
            	result = true;
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.v("ids", "close reader");
            if (client != null)
            	((AndroidHttpClient) client).close();
            try {
                if( reader != null ) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
	}
	
	@Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
//        if (result) {
//        	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//        	alertBuilder.setCustomTitle(AlertUtils.getAlertTextCenter(context, new LayoutParams(LayoutParams.MATCH_PARENT, 125), context.getResources().getString(R.string.new_version_available), 20));
//        	alertBuilder.setView(AlertUtils.getAlertCustom2Message(context, new LayoutParams(LayoutParams.MATCH_PARENT, 200), 
//        															context.getResources().getString(R.string.update_message1), 
//        															context.getResources().getString(R.string.update_message2), 14));
////        	alertBuilder.setMessage("Please update to the latest version.\n(Attention: Some dramas can't play in older version!!)");
//        	alertBuilder.setPositiveButton(R.string.button_update_now, new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					CLAccountManager.getInstance().openUrl(context, AppConfig.GOOGLEPLAY_MARKET);
//					dialog.cancel();
//				}
//			});
//        	alertBuilder.setNegativeButton(R.string.button_never_do, new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					CLAccountManager.getInstance().isNeedUpdate = false;
//					CLAccountManager.getInstance().setSharedPreferences();
//					dialog.cancel();
//				}
//			});
//        	alertBuilder.setNeutralButton(R.string.button_later, new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.cancel();
//				}
//			});
//        	alertBuilder.show();
//        }
//        checkVersionDelegate.didCheckVersion(result);
    }
	
//	public static class CheckVersionDelegate {
//		public void didCheckVersion(boolean isNewVersion) {
//			throw new UnsupportedOperationException(ErrorUtil.throwException);
//		}
//	}

}
