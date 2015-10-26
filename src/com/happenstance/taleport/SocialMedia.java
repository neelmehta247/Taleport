package com.happenstance.taleport;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class SocialMedia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social_media);
		TextView tv = (TextView) findViewById(R.id.textView2);
		Linkify.addLinks(tv, Linkify.ALL);
		tv = (TextView) findViewById(R.id.textView3);
		Linkify.addLinks(tv, Linkify.ALL);
		tv = (TextView) findViewById(R.id.textView4);
		Linkify.addLinks(tv, Linkify.ALL);
	}

}
