package com.happenstance.taleport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.happenstance.taleport.MainActivity.PlanetFragment;

public class Bookmark extends Activity {

	String url;
	String prevString;
	FileOutputStream fos;
	boolean canW;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmark);

		canW = canWrite();

		url = PlanetFragment.getUrl();
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Context context = getApplicationContext();
				EditText et = (EditText) findViewById(R.id.editText1);
				String a = et.getText().toString().trim();
				if (a.length() == 0) {
					Toast toast = Toast.makeText(context,
							"Please enter a name for the bookmark!",
							Toast.LENGTH_LONG);
					toast.setGravity(
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
					toast.show();
				} else {
					String root = Environment.getExternalStorageDirectory()
							.toString();
					File dir = new File(root + "/taleport");
					dir.mkdirs();
					File file = new File(dir, "bookmarks.txt");
					try {
						File abc = new File(Environment
								.getExternalStorageDirectory().toString()
								+ "/taleport", "bookmarks.txt");
						BufferedReader br = new BufferedReader(new FileReader(
								abc));
						int i = 0;
						while (true) {
							String test = br.readLine();
							if (test == null) {
								break;
							}
							i++;
						}

						File abc1 = new File(Environment
								.getExternalStorageDirectory().toString()
								+ "/taleport", "bookmarks.txt");
						BufferedReader br1 = new BufferedReader(new FileReader(
								abc1));

						String[] array = new String[i];

						for (i = 0; i < array.length; i++) {
							array[i] = br1.readLine();
						}

						FileOutputStream f = new FileOutputStream(file);
						PrintWriter pw = new PrintWriter(f);

						for (i = 0; i < array.length; i++) {
							pw.println(array[i]);
						}
						pw.println(a + " - " + url);
						pw.flush();
						pw.close();
						f.close();
						br1.close();
						br.close();
						Toast toast = Toast.makeText(context,
								"Bookmark Added!", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.BOTTOM
								| Gravity.CENTER_HORIZONTAL, 0, 30);
						toast.show();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}

	private boolean canWrite() {
		// TODO Auto-generated method stub
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}

	/*
	 * public class loadSomeStuff extends AsyncTask<String, Integer, String> {
	 * 
	 * @Override protected String doInBackground(String... arg0) { // TODO
	 * Auto-generated method stub String collected = null; FileInputStream fis =
	 * null; try { fis = openFileInput(PERF_NAME); byte[] dataArray = new
	 * byte[fis.available()]; if (dataArray.length > 0) while
	 * (fis.read(dataArray) != -1) { collected = new String(dataArray); }
	 * fis.close(); return collected; } catch (FileNotFoundException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return
	 * null; }
	 * 
	 * protected void onPostExecute(String result) { prevString = result; } }
	 */
}
