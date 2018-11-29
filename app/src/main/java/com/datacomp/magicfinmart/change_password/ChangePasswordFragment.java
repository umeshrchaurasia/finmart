package com.datacomp.magicfinmart.change_password;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.login.LoginActivity;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.login.LoginController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChangePasswordResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber {

    Button btnChangePassword;
    EditText etConfirmPassword, etNewPassword, etOldPassword;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        btnChangePassword = (Button) view.findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(this);
        etConfirmPassword = (EditText) view.findViewById(R.id.etConfirmPassword);
        etNewPassword = (EditText) view.findViewById(R.id.etNewPassword);
        etOldPassword = (EditText) view.findViewById(R.id.etOldPassword);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnChangePassword) {

            if (etOldPassword.getText().toString().length() == 0) {
                etOldPassword.setError("Enter old password");
                etOldPassword.setFocusable(true);
                return;
            }

            if (etNewPassword.getText().toString().length() < 6) {
                etNewPassword.setError("Minimum 6 characters required");
                etNewPassword.setFocusable(true);
                return;
            }

            if (!etConfirmPassword.getText().toString()
                    .equals(etNewPassword.getText().toString())) {
                etConfirmPassword.setError("Incorrect password.");
                etConfirmPassword.setFocusable(true);
                return;
            }


            showDialog("Please wait..");
            new LoginController(getActivity()).changePassword(etOldPassword.getText().toString(),
                    etNewPassword.getText().toString(), this);
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof ChangePasswordResponse) {
            if (response.getStatusNo() == 0) {
                etOldPassword.setText("");
                etConfirmPassword.setText("");
                etNewPassword.setText("");
                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();

                new DBPersistanceController(getActivity()).logout();
                new PrefManager(getActivity()).clearAll();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
