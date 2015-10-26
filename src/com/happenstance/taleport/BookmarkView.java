package com.happenstance.taleport;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BookmarkView extends Activity {

	public ProgressBar pBBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadbookmark);
		WebView bookView = (WebView) findViewById(R.id.bookView);
		pBBook = (ProgressBar) findViewById(R.id.progressBarBook);

		String url = getIntent().getExtras().getString("key");

		bookView.setWebViewClient(new ourViewClient());
		bookView.loadUrl(url);
		bookView.setWebChromeClient(new WebChromeClient() {
			int c = 0;

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);

				if (c == 0) {
					Context context = getApplicationContext();
					CharSequence text = "Loading!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
					toast.show();
					c = 1;
				}

				pBBook.setVisibility(View.VISIBLE);
				pBBook.setProgress(newProgress);

				if (newProgress == 100) {
					pBBook.setVisibility(View.GONE);
					Context context = getApplicationContext();
					CharSequence text = "Loaded!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
					toast.show();
					c = 0;
				}
			}

		});
	}
}
