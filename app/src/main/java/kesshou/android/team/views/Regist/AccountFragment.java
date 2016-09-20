package kesshou.android.team.views.Regist;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
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
public class AccountFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_account, container, false);

		final TextInputLayout tilInputNick=(TextInputLayout)view.findViewById(R.id.til_input_nick);
		final TextInputEditText inputNick=(TextInputEditText)view.findViewById(R.id.input_nick);
		TextViewCheckEmpty(tilInputNick,inputNick);

		final TextInputLayout tilInputAccount=(TextInputLayout)view.findViewById(R.id.til_input_account);
		final TextInputEditText inputAccount=(TextInputEditText)view.findViewById(R.id.input_account);
		TextViewCheckEmpty(tilInputAccount,inputAccount);
		TextViewCheckEmail(tilInputAccount,inputAccount);

		final TextInputLayout tilInputPassword=(TextInputLayout)view.findViewById(R.id.til_input_password);
		final TextInputEditText inputPassword=(TextInputEditText) view.findViewById(R.id.input_password);
		TextViewCheckEmpty(tilInputPassword,inputPassword);

		final TextInputLayout tilInputRePassword=(TextInputLayout)view.findViewById(R.id.til_input_repassword);
		final TextInputEditText inputRePassword=(TextInputEditText)view.findViewById(R.id.input_repassword);
		TextViewCheckEmpty(tilInputRePassword,inputRePassword);
		TextViewCheckPassword(inputPassword,tilInputRePassword,inputRePassword);

		final Register register=((StartActivity) getActivity()).register;
		if(register.usr_email!=null) inputAccount.setText(register.usr_email);
		if(register.usr_passwd!=null) inputPassword.setText(register.usr_passwd);
		if(register.usr_passwd!=null) inputRePassword.setText(register.usr_passwd);
		if(register.nickname!=null) inputNick.setText(register.nickname);

		AppCompatButton PreButton= (AppCompatButton) view.findViewById(R.id.PreButton);
		PreButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new SchoolFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		AppCompatButton NextButton= (AppCompatButton) view.findViewById(R.id.NextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String inputAccount=
					((TextInputEditText)view.findViewById(R.id.input_account)).getText().toString();
				String inputPassword=
					((TextInputEditText)view.findViewById(R.id.input_password)).getText().toString();
				String inputNick=
					((TextInputEditText)view.findViewById(R.id.input_nick)).getText().toString();
				String inputRePassword=
					((TextInputEditText)view.findViewById(R.id.input_repassword)).getText().toString();

				if(inputAccount.equals("")||inputPassword.equals("")||inputNick.equals("")||inputRePassword.equals("")) {
					Toast.makeText(getActivity().getApplicationContext(),R.string.regist_error_empty,Toast.LENGTH_SHORT).show();
				}else if(!inputAccount.contains("@")){
					Toast.makeText(getActivity().getApplicationContext(),R.string.regist_error_email,Toast.LENGTH_SHORT).show();
				}else if(!inputPassword.equals(inputRePassword)){
					Toast.makeText(getActivity().getApplicationContext(),R.string.regist_error_pwd,Toast.LENGTH_SHORT).show();
				}else{
					register.usr_email = inputAccount;
					register.usr_passwd= inputPassword;
					register.nickname= inputNick;

					FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					Fragment fg = new TermsFragment();
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

	private void TextViewCheckEmail(final TextInputLayout textInputLayout, final TextInputEditText textInputEditText){
		textInputEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// null
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if(!textInputEditText.getText().toString().contains("@")) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(!textInputEditText.getText().toString().contains("@")) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}
		});
	}

	private void TextViewCheckPassword(final TextInputEditText equalsInputPassword, final TextInputLayout textInputLayout, final TextInputEditText textInputEditText){
		textInputEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// null
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if(!equalsInputPassword.getText().toString().equals(textInputEditText.getText().toString())) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_pwd));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(!equalsInputPassword.getText().toString().equals(textInputEditText.getText().toString())) {
					textInputLayout.setError(getResources().getString(R.string.regist_error_pwd));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}
		});
	}

}
