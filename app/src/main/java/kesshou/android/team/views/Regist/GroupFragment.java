package kesshou.android.team.views.Regist;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kesshou.android.team.R;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.views.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


	public GroupFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_group, container, false);

		final Register register=((StartActivity) getActivity()).register;

		AppCompatButton PreButton= (AppCompatButton) view.findViewById(R.id.PreButton);
		PreButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new LoginFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		AppCompatButton DayButton = (AppCompatButton) view.findViewById(R.id.DayButton);
		DayButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				register.usr_group="student";

				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new SchoolFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		AppCompatButton NightButton = (AppCompatButton) view.findViewById(R.id.NightButton);
		NightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				register.usr_group="night";
				register.school_account="";
				register.school_pwd="";

				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new AccountFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		AppCompatButton GudButton = (AppCompatButton) view.findViewById(R.id.GudButton);
		GudButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				register.usr_group="graduated";

				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new SchoolFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		return view;
	}

}
