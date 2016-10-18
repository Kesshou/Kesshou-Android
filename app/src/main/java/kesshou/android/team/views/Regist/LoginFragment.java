package kesshou.android.team.views.Regist;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.regex.Pattern;

import kesshou.android.team.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


	public LoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		final TextInputLayout tilInputAccount=(TextInputLayout)view.findViewById(R.id.til_input_account);
		final TextInputEditText inputAccount=(TextInputEditText)view.findViewById(R.id.input_account);
		TextViewCheckEmpty(tilInputAccount,inputAccount);
		TextViewCheckEmail(tilInputAccount,inputAccount);

		final TextInputLayout tilInputPassword=(TextInputLayout)view.findViewById(R.id.til_input_password);
		final TextInputEditText inputPassword=(TextInputEditText) view.findViewById(R.id.input_password);
		TextViewCheckEmpty(tilInputPassword,inputPassword);

		AppCompatButton LoginButton = (AppCompatButton) view.findViewById(R.id.LoginButton);
		LoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		AppCompatButton RegistButton = (AppCompatButton) view.findViewById(R.id.RegistButton);
		RegistButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new GroupFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
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

	private void TextViewCheckEmail(final TextInputLayout textInputLayout, final TextInputEditText textInputEditText){
		textInputEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// null
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if(!isValidEmail(textInputEditText.getText().toString())) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(!isValidEmail(textInputEditText.getText().toString())) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}
		});
	}

	public static final Pattern EMAIL_PATTERN = Pattern
		.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
	public static boolean isValidEmail(String email) {
		boolean result = false;
		if (EMAIL_PATTERN.matcher(email).matches()) {
			result = true;
		}
		return result;
	}
}
