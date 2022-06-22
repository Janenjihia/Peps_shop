package com.moringaschool.peps_shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private FrameLayout frameLayout;
    private TextInputEditText mEdtUserName, mEdtPwd;
    private TextInputLayout mTxtInputLayoutPwd;
    private TextView txtNoAccount;
    private Button btnLogin;

    private static final String BTN_DISABLE = "#bdc3c7";
    private static final String BTN_ENABLE = "#EF984B";

    public LoginFragment(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initUI(view);
        addActionForNoAccountEvent();
        addActionForBtnLogin();

        addListenerForEditText(mEdtUserName);
        addListenerForEditText(mEdtPwd);

        changeEndIconStateOnFocusEditText(mEdtPwd, mTxtInputLayoutPwd);

        return view;
    }

    private void initUI(View view) {
        txtNoAccount = view.findViewById(R.id.txt_have_account);
        txtNoAccount.append(" " + Html.fromHtml("<u>Sign up now!</u>"));

        mEdtUserName = view.findViewById(R.id.edt_username);
        mEdtPwd = view.findViewById(R.id.edt_pwd);

        mTxtInputLayoutPwd = view.findViewById(R.id.textInputLayout2);

        btnLogin = view.findViewById(R.id.btn_login);
        changeButtonState(false, BTN_DISABLE);
    }

    private void changeButtonState(boolean state, String color) {
        btnLogin.setEnabled(state);
        btnLogin.setBackgroundColor(Color.parseColor(color));
    }

    private void addActionForNoAccountEvent() {
        txtNoAccount.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(frameLayout.getId(), new RegisterFragment(frameLayout)).commit();
        });
    }

    private void addActionForBtnLogin() {
        btnLogin.setOnClickListener(view1 -> {

            if (!isValidAccount(mEdtUserName.getText().toString().trim(), mEdtPwd.getText().toString().trim()))
                return;

            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            getActivity().finish();
        });
    }

    private boolean isValidAccount(String userName, String password) {
        if (!userName.matches("[a-zA-Z0-9]{0,12}")) {
            mEdtUserName.setError("Invalid Username! Please check your Username!");
            mEdtUserName.requestFocus();
            return false;
        }
        return true;
    }

    private void addListenerForEditText(TextInputEditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mEdtPwd.getText().toString().trim().isEmpty() && !mEdtUserName.getText().toString().trim().isEmpty())
                    changeButtonState(true, BTN_ENABLE);
                else
                    changeButtonState(false, BTN_DISABLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void changeEndIconStateOnFocusEditText(TextInputEditText editText, TextInputLayout textInputLayout) {
        editText.setOnFocusChangeListener((view, isFocus) -> {
            textInputLayout.setEndIconActivated(isFocus);
        });
    }
}