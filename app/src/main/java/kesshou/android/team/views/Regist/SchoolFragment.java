package kesshou.android.team.views.Regist;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kesshou.android.team.R;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.views.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment {

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_school, container, false);

		final TextInputLayout tilInputAccount=(TextInputLayout)view.findViewById(R.id.til_input_account);
		final TextInputEditText inputAccount=(TextInputEditText)view.findViewById(R.id.input_account);
		TextViewCheckEmpty(tilInputAccount,inputAccount);

		final TextInputLayout tilInputPassword=(TextInputLayout)view.findViewById(R.id.til_input_password);
		final TextInputEditText inputPassword=(TextInputEditText) view.findViewById(R.id.input_password);
		TextViewCheckEmpty(tilInputPassword,inputPassword);

		final Register register=((StartActivity) getActivity()).register;
		if(register.school_account!=null) inputAccount.setText(register.school_account);
		if(register.school_pwd!=null) inputPassword.setText(register.school_pwd);

		AppCompatButton NextButton= (AppCompatButton) view.findViewById(R.id.NextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String strInputAccount=inputAccount.getText().toString();
				String strInputPassword=inputPassword.getText().toString();

				if(strInputAccount.equals("")||strInputPassword.equals("")){
					Toast.makeText(getActivity().getApplicationContext(),R.string.regist_error_empty,Toast.LENGTH_SHORT).show();
				}else{
					register.school_account = strInputAccount;
					register.school_pwd = strInputPassword.toUpperCase();

					FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					Fragment fg = new AccountFragment();
					ft.replace(R.id.fm, fg, "f_m");
					ft.commit();
				}
			}
		});

		return view;
	}

	private void TextViewCheckEmpty(final TextInputLayout textInputLayout, final TextInputEditText textInputEditText){
		textInputEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// null
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if(textInputEditText.getText().toString().equals("")) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_empty));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(textInputEditText.getText().toString().equals("")) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_empty));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}
		});
	}

}
