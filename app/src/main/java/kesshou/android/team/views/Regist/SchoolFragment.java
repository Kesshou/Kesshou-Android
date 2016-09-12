package kesshou.android.team.views.Regist;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kesshou.android.team.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchoolFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_school, container, false);

		AppCompatButton NextButton= (AppCompatButton) view.findViewById(R.id.NextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new AccountFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		return view;
	}


}
