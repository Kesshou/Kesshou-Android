package kesshou.android.team.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kesshou.android.team.R;
import kesshou.android.team.views.Regist.SchoolFragment;

public class StartActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment fg= new SchoolFragment();
		ft.replace(R.id.fm, fg, "f_m");
		ft.commit();
	}
}
