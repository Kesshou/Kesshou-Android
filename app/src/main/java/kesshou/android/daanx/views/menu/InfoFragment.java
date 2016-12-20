package kesshou.android.daanx.views.menu;


import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import kesshou.android.daanx.R;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.CheckRegist;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import kesshou.android.daanx.util.network.api.holder.Update;
import retrofit2.Call;
import retrofit2.Response;

import static kesshou.android.daanx.views.regist.AccountFragment.isValidEmail;

/**
 * A simple class.
 */
public class InfoFragment {


	public InfoFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_info,null);


		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		final TextInputLayout tilInputOldPwd = (TextInputLayout) view.findViewById(R.id.til_input_old_password);
		final TextInputEditText inputOldPwd = (TextInputEditText) view.findViewById(R.id.input_old_password);

		final TextInputLayout tilInputNewSchoolPwd = (TextInputLayout) view.findViewById(R.id.til_input_school_password);
		final TextInputEditText inputNewSchoolPwd = (TextInputEditText) view.findViewById(R.id.input_school_password);



		final TextInputLayout tilInputNewNick = (TextInputLayout)view.findViewById(R.id.til_input_new_nick);
		final TextInputEditText inputNewNick = (TextInputEditText)view.findViewById(R.id.input_new_nick);
		inputNewNick.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}
			@Override
			public void afterTextChanged(Editable editable) {
				CheckRegist checkNick = new CheckRegist();
				checkNick.nick = inputNewNick.getText().toString();
				new NetworkingClient(context).checkNick(checkNick, new MyCallBack<StatusResponse>(context) {
					@Override
					public void onSuccess(Response<StatusResponse> response) {
						tilInputNewNick.setError(null);
						tilInputNewNick.setErrorEnabled(false);
					}

					@Override
					public void onErr(Error error, Call<StatusResponse> call) {
						tilInputNewNick.setError(error.message);
					}
				});
			}
		});

		final TextInputLayout tilInputNewAccount = (TextInputLayout)view.findViewById(R.id.til_input_new_account);
		final TextInputEditText inputNewAccount = (TextInputEditText)view.findViewById(R.id.input_new_account);
		TextViewCheckEmail(context,tilInputNewAccount,inputNewAccount);

		TextInputLayout tilInputNewPwd = (TextInputLayout) view.findViewById(R.id.til_input_new_password);
		final TextInputEditText inputNewPwd = (TextInputEditText) view.findViewById(R.id.input_new_password);

		AppCompatButton appCompatButton = (AppCompatButton) view.findViewById(R.id.btn_submit);
		appCompatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Update update = new Update();
				update.password = inputOldPwd.getText().toString();
				if(!inputNewSchoolPwd.getText().toString().equals("")) update.new_spwd = inputNewSchoolPwd.getText().toString();
				if(!inputNewNick.getText().toString().equals("")) update.new_nick = inputNewNick.getText().toString();
				if(!inputNewAccount.getText().toString().equals("")) update.new_email = inputNewAccount.getText().toString();
				if(!inputNewPwd.getText().toString().equals("")) update.new_password = inputNewPwd.getText().toString();
				networkingClient.update(update, new MyCallBack<StatusResponse>(context) {
					@Override
					public void onSuccess(Response<StatusResponse> response) {
						Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onErr(Error error, Call<StatusResponse> call) {
						switch (error.code){
							case 100:
								tilInputOldPwd.setError(error.message);
								break;
							case 102:
								tilInputNewSchoolPwd.setError(error.message);
								break;
							case 500:
								tilInputNewNick.setError(error.message);
								break;
							case 501:
								tilInputNewAccount.setError(error.message);
								break;
						}
					}
				});
			}
		});

		return view;
	}


	private void TextViewCheckEmail(final Context context,final TextInputLayout textInputLayout, final TextInputEditText textInputEditText){
		textInputEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				// null
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if(!isValidEmail(textInputEditText.getText().toString())) {
					textInputLayout.setError(context.getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(!isValidEmail(textInputEditText.getText().toString())) {
					textInputLayout.setError(context.getResources().getString(R.string.regist_error_email));
				}else {
					textInputLayout.setError(null);
					textInputLayout.setErrorEnabled(false);

					CheckRegist checkAccount = new CheckRegist();
					checkAccount.account = textInputEditText.getText().toString();
					new NetworkingClient(context).checkAccount(checkAccount, new MyCallBack<StatusResponse>(context) {
						@Override
						public void onSuccess(Response<StatusResponse> response) {
							textInputLayout.setError(null);
							textInputLayout.setErrorEnabled(false);
						}

						@Override
						public void onErr(Error error, Call<StatusResponse> call) {
							textInputLayout.setError(error.message);
						}
					});
				}
			}
		});
	}

}
