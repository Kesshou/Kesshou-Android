package kesshou.android.daanx;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yoyo930021 on 2017/1/16.
 */

public class BaseActivity extends AppCompatActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(MyContextWrapper.wrap(newBase,"fr"));
	}
}
