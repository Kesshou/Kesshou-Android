package kesshou.android.team.views.regist;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

import kesshou.android.team.R;
import kesshou.android.team.util.component.ToastUtils;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.CheckRegist;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.StatusResponse;
import kesshou.android.team.views.StartActivity;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_account, container, false);

		final TextInputLayout tilInputNick = (TextInputLayout)view.findViewById(R.id.til_input_nick);
		final TextInputEditText inputNick = (TextInputEditText)view.findViewById(R.id.input_nick);
		TextViewCheckEmpty(tilInputNick,inputNick);
		inputNick.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}
			@Override
			public void afterTextChanged(Editable editable) {
				CheckRegist checkNick = new CheckRegist();
				checkNick.nick = inputNick.getText().toString();
				new NetworkingClient(getActivity().getApplicationContext()).checkNick(checkNick, new MyCallBack<StatusResponse>(getActivity().getApplicationContext()) {
					@Override
					public void onSuccess(Response<StatusResponse> response) {
						tilInputNick.setError(null);
						tilInputNick.setErrorEnabled(false);
					}

					@Override
					public void onErr(Error error) {
						tilInputNick.setError(error.message);
					}
				});
			}
		});


		final TextInputLayout tilInputAccount = (TextInputLayout)view.findViewById(R.id.til_input_account);
		final TextInputEditText inputAccount = (TextInputEditText)view.findViewById(R.id.input_account);
		TextViewCheckEmpty(tilInputAccount,inputAccount);
		TextViewCheckEmail(tilInputAccount,inputAccount);

		final TextInputLayout tilInputPassword = (TextInputLayout)view.findViewById(R.id.til_input_password);
		final TextInputEditText inputPassword = (TextInputEditText) view.findViewById(R.id.input_password);
		TextViewCheckEmpty(tilInputPassword,inputPassword);

		final TextInputLayout tilInputRePassword = (TextInputLayout)view.findViewById(R.id.til_input_repassword);
		final TextInputEditText inputRePassword = (TextInputEditText)view.findViewById(R.id.input_repassword);
		TextViewCheckEmpty(tilInputRePassword,inputRePassword);
		TextViewCheckPassword(inputPassword,tilInputRePassword,inputRePassword);

		final Register register = ((StartActivity) getActivity()).register;
		if(register.usr_email != null) inputAccount.setText(register.usr_email);
		if(register.usr_passwd != null) inputPassword.setText(register.usr_passwd);
		if(register.usr_passwd != null) inputRePassword.setText(register.usr_passwd);
		if(register.nickname != null) inputNick.setText(register.nickname);

		AppCompatButton PreButton = (AppCompatButton) view.findViewById(R.id.PreButton);
		PreButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(register.usr_group.equals("night")) {
					FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					Fragment fg = new GroupFragment();
					ft.replace(R.id.fm, fg, "f_m");
					ft.commit();
				}else{
					FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					Fragment fg = new SchoolFragment();
					ft.replace(R.id.fm, fg, "f_m");
					ft.commit();
				}
			}
		});

		AppCompatButton NextButton= (AppCompatButton) view.findViewById(R.id.NextButton);
		NextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String strInputAccount=
					inputAccount.getText().toString();
				String strInputPassword=
					inputPassword.getText().toString();
				String strInputNick=
					inputNick.getText().toString();
				String strInputRePassword=
					inputRePassword.getText().toString();

				if(strInputAccount.equals("")||strInputPassword.equals("")||strInputNick.equals("")||strInputRePassword.equals("")) {
					ToastUtils.makeTextAndShow(getActivity().getApplicationContext(), R.string.regist_error_empty ,Toast.LENGTH_SHORT);
				}else if(!strInputAccount.contains("@")){
					ToastUtils.makeTextAndShow(getActivity().getApplicationContext(),R.string.regist_error_email,Toast.LENGTH_SHORT);
				}else if(!strInputPassword.equals(strInputRePassword)){
					ToastUtils.makeTextAndShow(getActivity().getApplicationContext(),R.string.regist_error_pwd,Toast.LENGTH_SHORT);
				}else{
					register.usr_email = strInputAccount;
					register.usr_passwd= strInputPassword;
					register.nickname= strInputNick;

					FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
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

					CheckRegist checkAccount = new CheckRegist();
					checkAccount.account = textInputEditText.getText().toString();
					new NetworkingClient(getActivity().getApplicationContext()).checkAccount(checkAccount, new MyCallBack<StatusResponse>(getActivity().getApplicationContext()) {
						@Override
						public void onSuccess(Response<StatusResponse> response) {
							textInputLayout.setError(null);
							textInputLayout.setErrorEnabled(false);
						}

						@Override
						public void onErr(Error error) {
							textInputLayout.setError(error.message);
						}
					});
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
