package com.happenstance.taleport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SeeBookmarks extends Activity {

	public FileInputStream fis;
	public ListView lv;
	public String prevString;
	public String extracted;
	private final String PERF_NAME = "MyFileName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seebookmarks);
		lv = (ListView) findViewById(R.id.listView1);

		File abc = new File(Environment.getExternalStorageDirectory()
				.toString() + "/taleport", "bookmarks.txt");
		BufferedReader br = null;
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(abc));
			i = 0;
			while (true) {
				String test;
				test = br.readLine();
				if (test == null) {
					break;
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] listValues = new String[i];
		File abc1 = new File(Environment.getExternalStorageDirectory()
				.toString() + "/taleport", "bookmarks.txt");
		BufferedReader br1;
		try {
			br1 = new BufferedReader(new FileReader(abc1));
			for (i = 0; i < listValues.length; i++) {
				listValues[i] = br1.readLine();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast toast = Toast.makeText(getApplicationContext(),
					"You don't have any bookmarks!", Toast.LENGTH_LONG);
			toast.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				listValues);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String url = (String) lv.getItemAtPosition(arg2);
				int a = url.indexOf(" - ");
				url = url.substring(a + 3);
				url.trim();
				Intent abc = new Intent(
						"com.happenstance.taleport.BookmarkView");
				abc.putExtra("key", url);
				startActivity(abc);
			}

		});
	}

}
