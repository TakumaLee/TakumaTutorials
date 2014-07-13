package takuma.tutorial.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class HttpPostOOMAsyncTask extends AsyncTask<String, Integer, String> {
	
	JSONObject sendParams;
	
	public HttpPostOOMAsyncTask(JSONObject params) {
		this.sendParams = params;
	}

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		String result = "";
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.addHeader("Accept-Language", Locale.getDefault().toString());
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		try {
			StringEntity se = new StringEntity(sendParams.toString());
			post.setEntity(se);
			
			HttpResponse response = client.execute(post);
			
			HttpEntity entity = response.getEntity();
//			result = EntityUtils.toString(entity);
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()), 23 * 1024);
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		    result = sb.toString();
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
