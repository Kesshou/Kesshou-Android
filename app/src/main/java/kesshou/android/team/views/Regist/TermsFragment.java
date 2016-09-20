package kesshou.android.team.views.Regist;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kesshou.android.team.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_terms, container, false);

		AppCompatButton PreButton= (AppCompatButton) view.findViewById(R.id.PreButton);
		PreButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				android.app.Fragment fg= new AccountFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		AppCompatButton NextButton= (AppCompatButton) view.findViewById(R.id.NextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				android.app.Fragment fg= new LoadingFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		return view;
	}

}
