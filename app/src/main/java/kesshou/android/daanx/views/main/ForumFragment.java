package kesshou.android.daanx.views.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kesshou.android.daanx.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {

	private View rootView;

	public ForumFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if(rootView == null){
			rootView = inflater.inflate(R.layout.fragment_forum, container, false);
		}
//		Bundle bundle = new Bundle();
//		bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Forum Forum");
//		bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Forum Start");
//		bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Forum");
//		((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
		return rootView;
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();
		if(rootView != null){
			((ViewGroup) rootView.getParent()).removeView(rootView);
		}
	}
}
