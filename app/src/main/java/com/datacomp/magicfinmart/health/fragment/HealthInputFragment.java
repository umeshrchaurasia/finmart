package com.datacomp.magicfinmart.health.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.health.healthquotetabs.HealthQuoteBottomTabsActivity;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.SortbyAge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.model.HealthSumAssured;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Nilesh Birhade on 11/02/2018.
 */

public class HealthInputFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener, View.OnTouchListener {

    private static final String TAG = "HealthInputFragment";
    //  public static final String MEMBER_LIST = "member_list";
    //  public static final int REQUEST_MEMBER = 4444;

    Button btnSelf, btnFamily, btnParent;
    ImageView img1, img2, img3, img4, img5, img6;
    EditText et1, et2, et3, et4, et5, et6;
    RecyclerView rvSumAssured;
    AutoCompleteTextView acCity;
    ArrayAdapter<String> cityAdapter;

    List<String> cityList;

    List<MemberListEntity> memberList;

    // 0 - self , 1 -family , 2 - parent
    int coverFor = 0;
    HealthSumAssuredViewAdapter adapter;

    EditText etAmount, etPincode, etName, etMobile;

    Button btnGetHealthQuote;

    HealthQuote healthQuote;
    HealthRequestEntity healthRequestEntity;
    DBPersistanceController db;
    List<HealthSumAssured> listSumAssured;

    EditText editText;

    private PopupWindow mPopupWindow, mPopupWindowSelection;
    View customView, customViewSelection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_input, container, false);
        init(view);

        // set initial values
        healthQuote = new HealthQuote();
        cityList = new ArrayList<>();
        memberList = new ArrayList<>();
        healthRequestEntity = new HealthRequestEntity();

        cityList = new DBPersistanceController(getActivity()).getHealthCity();
        cityBinding();

        db = new DBPersistanceController(getActivity());
        listSumAssured = db.getSumAssured();

        sumAssuredBinding();

        disableAgeEditBox();
        setListener();
        setAgePopUp();

        //default disableAll
        disableAllInputs();

        if (getArguments() != null) {
            healthQuote = getArguments().getParcelable(HealthQuoteBottomTabsActivity.INPUT_DATA);
            healthRequestEntity = healthQuote.getHealthRequest();
            processMemberForAge();
            bindInput();
        } else {
            healthQuote.setAgent_source("App");
            healthQuote.setFba_id(new DBPersistanceController(getContext()).getUserData().getFBAId());
            //default self selected
            btnFamily.performClick();
        }


        return view;
    }

    private void setAgePopUp() {
        // region set Default popUp
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        customView = inflater.inflate(R.layout.layout_age_popup, null);

        // Initialize a new instance of popup window

        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }


        //endregion

        // region set Selection popUp
        LayoutInflater inflaterSelection = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        customViewSelection = inflaterSelection.inflate(R.layout.layout_age_popup_selected, null);

        // Initialize a new instance of popup window
        mPopupWindowSelection = new PopupWindow(
                customViewSelection,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //endregion

    }

    private void OpenPoupWnidow(EditText tmpEdit) {

        editText = tmpEdit;
        if (editText == null || !editText.isEnabled()) {
            return;
        }
        // Get a reference for the custom view close button
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.imgClose);
        ImageButton imgPrev = (ImageButton) customView.findViewById(R.id.imgPrev);
        ImageButton imgNext = (ImageButton) customView.findViewById(R.id.imgNext);

        final Button btnNum1 = (Button) customView.findViewById(R.id.btnNum1);
        Button btnNum2 = (Button) customView.findViewById(R.id.btnNum2);
        Button btnNum3 = (Button) customView.findViewById(R.id.btnNum3);
        Button btnNum4 = (Button) customView.findViewById(R.id.btnNum4);
        Button btnNum5 = (Button) customView.findViewById(R.id.btnNum5);
        Button btnNum6 = (Button) customView.findViewById(R.id.btnNum6);

        Button btnNum7 = (Button) customView.findViewById(R.id.btnNum7);
        Button btnNum8 = (Button) customView.findViewById(R.id.btnNum8);
        Button btnNum9 = (Button) customView.findViewById(R.id.btnNum9);
        Button btnNum0 = (Button) customView.findViewById(R.id.btnNum0);


        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindowSelection.dismiss();


                }
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                if (mPopupWindow.isShowing()) {
                    setNextPrevPopUpAge(editText, "P");

                }
            }
        });


        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                if (mPopupWindow.isShowing()) {
                    setNextPrevPopUpAge(editText, "N");

                }
            }
        });


        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('1', editText);
                }
            }
        });

        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('2', editText);
                }
            }
        });


        btnNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('3', editText);
                }
            }
        });

        btnNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('4', editText);
                }
            }
        });

        btnNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('5', editText);
                }
            }
        });

        btnNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('6', editText);
                }
            }
        });

        btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('7', editText);
                }
            }
        });

        btnNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('8', editText);
                }
            }
        });


        btnNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('9', editText);
                }
            }
        });

        btnNum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    onCharacterPressed('0', editText);
                }
            }
        });
        mPopupWindowSelection.showAsDropDown(editText, 10, -10);

        // mPopupWindow.setAnimationStyle(R.style.Animation);
        mPopupWindow.showAsDropDown(editText, 0, 30);

    }

    private void setNonFocusEditText() {
        et1.setFocusable(false);
        et2.setFocusable(false);
        et3.setFocusable(false);
        et4.setFocusable(false);
        et5.setFocusable(false);
        et6.setFocusable(false);

    }

    private void onCharacterPressed(char digit, EditText et) {
        try {
            CharSequence cur = et.getText();

            int len = cur.length();

            if (cur.length() == 2) {
                et.setText("" + digit);
            } else if (cur.length() < 2) {

                if (cur.length() == 0) {
                    et.setCursorVisible(false);
                }

                cur = cur.subSequence(0, len).toString() + digit;
                et.setText(cur);
                //  et.setSelection(start + 1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setNextPrevPopUpAge(EditText editText, String type) {


        if (type.equals("N")) {

            int i = editText.getId();
            if (i == R.id.etOne) {
                if (et2 != null && et2.isEnabled()) {
                    mPopupWindowSelection.dismiss();
                    mPopupWindow.dismiss();
                    OpenPoupWnidow(et2);
                }


            } else if (i == R.id.etTwo) {
                if (et3 != null && et3.isEnabled()) {
                    mPopupWindowSelection.dismiss();
                    mPopupWindow.dismiss();
                    OpenPoupWnidow(et3);
                }

            } else if (i == R.id.etThree) {
                if (et4 != null && et4.isEnabled()) {
                    mPopupWindowSelection.dismiss();
                    mPopupWindow.dismiss();
                    OpenPoupWnidow(et4);
                }

            } else if (i == R.id.etFour) {
                if (et5 != null && et5.isEnabled()) {
                    mPopupWindowSelection.dismiss();
                    mPopupWindow.dismiss();
                    OpenPoupWnidow(et5);
                }

            } else if (i == R.id.etFive) {
                if (et6 != null && et6.isEnabled()) {
                    mPopupWindowSelection.dismiss();
                    mPopupWindow.dismiss();
                    OpenPoupWnidow(et6);
                }

            } else if (i == R.id.etSix) {
            }

        } else {

            int i = editText.getId();
            if (i == R.id.etOne) {
            } else if (i == R.id.etTwo) {
                mPopupWindowSelection.dismiss();
                mPopupWindow.dismiss();
                OpenPoupWnidow(et1);


            } else if (i == R.id.etThree) {
                mPopupWindowSelection.dismiss();
                mPopupWindow.dismiss();
                OpenPoupWnidow(et2);

            } else if (i == R.id.etFour) {
                mPopupWindowSelection.dismiss();
                mPopupWindow.dismiss();
                OpenPoupWnidow(et3);

            } else if (i == R.id.etFive) {
                mPopupWindowSelection.dismiss();
                mPopupWindow.dismiss();
                OpenPoupWnidow(et4);

            } else if (i == R.id.etSix) {
                mPopupWindowSelection.dismiss();
                mPopupWindow.dismiss();
                OpenPoupWnidow(et5);

            }

        }
    }


    private void disableAgeEditBox() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et1.setShowSoftInputOnFocus(false);
            et2.setShowSoftInputOnFocus(false);
            et3.setShowSoftInputOnFocus(false);
            et4.setShowSoftInputOnFocus(false);
            et5.setShowSoftInputOnFocus(false);
            et6.setShowSoftInputOnFocus(false);

        } else {
            et1.setTextIsSelectable(true);
            et2.setTextIsSelectable(true);

            et3.setTextIsSelectable(true);
            et4.setTextIsSelectable(true);

            et5.setTextIsSelectable(true);
            et6.setTextIsSelectable(true);

        }
    }

    private void processMemberForAge() {
        List<MemberListEntity> listM = new ArrayList<MemberListEntity>();
        if (healthRequestEntity.getMemberList() != null) {
            for (int i = 0; i < healthRequestEntity.getMemberList().size(); i++) {
                MemberListEntity entity = healthRequestEntity.getMemberList().get(i);
                entity.setAge(getAgeFromDate(entity.getMemberDOB()));
                listM.add(entity);
            }

        }
        healthRequestEntity.setMemberList(listM);
    }

    private void sumAssuredBinding() {

        adapter = new HealthSumAssuredViewAdapter(this, listSumAssured);
        rvSumAssured.setAdapter(adapter);
    }

    private void bindInput() {

        resetonClick();
        if (healthRequestEntity.getPolicyFor() != null)
            selectCoverFor(healthRequestEntity.getPolicyFor().toLowerCase());

        etAmount.setText(healthRequestEntity.getSumInsured());
        etMobile.setText(healthRequestEntity.getContactMobile());
        etName.setText(healthRequestEntity.getContactName());
        etPincode.setText(String.valueOf(healthRequestEntity.getPincode()));

        //select existing sum assured amount in recycler view

        for (int i = 0; i < listSumAssured.size(); i++) {
            if (listSumAssured.get(i).getSumAssuredAmount() == Long.parseLong(healthRequestEntity.getSumInsured())) {
                listSumAssured.get(i).setSelected(true);
                break;
            }
        }
        adapter.refreshBinding(listSumAssured);

        acCity.setText(db.getHealthCityName(healthRequestEntity.getCityID()));
        acCity.performCompletion();

        enableMembers();
    }

    private void enableMembers() {

        for (int i = 0; i < healthRequestEntity.getMemberList().size(); i++) {
            MemberListEntity entity = healthRequestEntity.getMemberList().get(i);
            if (i == 0) {
                et1.setText(String.valueOf(entity.getAge()));
                img1.setImageResource(R.mipmap.user_selected);
                enableOne(img1);
                //  img1.performClick();
            }
            if (i == 1) {
                et2.setText(String.valueOf(entity.getAge()));
                img2.setImageResource(R.mipmap.user_selected);
                enableOne(img2);
                // img2.performClick();
            }

            if (i == 2) {
                et3.setText(String.valueOf(entity.getAge()));
                img3.setImageResource(R.mipmap.user_selected);
                enableOne(img3);
                //  img3.performClick();
            }

            if (i == 3) {
                et4.setText(String.valueOf(entity.getAge()));
                img4.setImageResource(R.mipmap.user_selected);
                enableOne(img4);
                //  img4.performClick();
            }

            if (i == 4) {
                et5.setText(String.valueOf(entity.getAge()));
                img5.setImageResource(R.mipmap.user_selected);
                enableOne(img5);
                //  img5.performClick();
            }
            if (i == 5) {
                et6.setText(String.valueOf(entity.getAge()));
                img6.setImageResource(R.mipmap.user_selected);
                enableOne(img6);
                // img6.performClick();
            }
        }
    }

    private void selectCoverFor(String coverfor) {
        switch (coverfor) {
            case "self":
                btnSelf.performClick();
                break;
            case "family":
                btnFamily.performClick();
                break;
            case "parent":
                btnParent.performClick();
                break;
        }
    }


    //region initialise all

    private void disableAllInputs() {
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);

        et1.setEnabled(false);
        et2.setEnabled(false);
        et3.setEnabled(false);
        et4.setEnabled(false);
        et5.setEnabled(false);
        et6.setEnabled(false);
    }

    private void setListener() {

//        et1.setOnClickListener(this);
//        et2.setOnClickListener(this);
//        et3.setOnClickListener(this);
//        et4.setOnClickListener(this);
//        et5.setOnClickListener(this);
//        et6.setOnClickListener(this);

//        et1.setOnFocusChangeListener(this);
//        et2.setOnFocusChangeListener(this);
//        et3.setOnFocusChangeListener(this);
//        et4.setOnFocusChangeListener(this);
//        et5.setOnFocusChangeListener(this);
//        et6.setOnFocusChangeListener(this);

        et1.setOnTouchListener(this);
        et2.setOnTouchListener(this);
        et3.setOnTouchListener(this);
        et4.setOnTouchListener(this);
        et5.setOnTouchListener(this);
        et6.setOnTouchListener(this);

        btnSelf.setOnClickListener(this);
        btnFamily.setOnClickListener(this);
        btnParent.setOnClickListener(this);
        btnGetHealthQuote.setOnClickListener(this);

        //for validating auto complete city
        acCity.setOnFocusChangeListener(acCityFocusChange);

        etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean isChange = false;
                    if (etAmount.getText().toString().length() != 0) {
                        for (int i = 0; i < listSumAssured.size(); i++) {
                            if (listSumAssured.get(i).getSumAssuredAmount() ==
                                    Integer.parseInt(etAmount.getText().toString())) {
                                isChange = true;
                                listSumAssured.get(i).setSelected(true);
                            }
                        }

                        if (!isChange) {
                            adapter.clearBinding();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });


    }

    View.OnFocusChangeListener acCityFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {

                String str = acCity.getText().toString();

                ListAdapter listAdapter = acCity.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        return;
                    }
                }

                acCity.setText("");
                acCity.setError("Invalid city");
                acCity.setFocusable(true);
            } else {
                acCity.setError(null);
            }
        }
    };

    private void cityBinding() {
        cityAdapter = new
                ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cityList);
        acCity.setAdapter(cityAdapter);
        acCity.setThreshold(1);
    }

    private void init(View view) {

        acCity = (AutoCompleteTextView) view.findViewById(R.id.acCity);

        rvSumAssured = (RecyclerView) view.findViewById(R.id.rvSumAssured);

        int numberOfColumns = 4;
        rvSumAssured.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));


        btnSelf = (Button) view.findViewById(R.id.btnSelf);
        btnFamily = (Button) view.findViewById(R.id.btnFamily);
        btnParent = (Button) view.findViewById(R.id.btnParent);

        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img2);
        img3 = (ImageView) view.findViewById(R.id.img3);
        img4 = (ImageView) view.findViewById(R.id.img4);
        img5 = (ImageView) view.findViewById(R.id.img5);
        img6 = (ImageView) view.findViewById(R.id.img6);

        et1 = (EditText) view.findViewById(R.id.etOne);
        et2 = (EditText) view.findViewById(R.id.etTwo);
        et3 = (EditText) view.findViewById(R.id.etThree);
        et4 = (EditText) view.findViewById(R.id.etFour);
        et5 = (EditText) view.findViewById(R.id.etFive);
        et6 = (EditText) view.findViewById(R.id.etSix);

        etAmount = (EditText) view.findViewById(R.id.etAmount);
        etPincode = (EditText) view.findViewById(R.id.etPincode);
        etName = (EditText) view.findViewById(R.id.etName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        btnGetHealthQuote = (Button) view.findViewById(R.id.btnGetHealthQuote);


    }

    //endregion

    public void getSumAssured(long amount) {
        etAmount.setText(String.valueOf(amount));

    }

    @Override
    public void onClick(View view) {

        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindowSelection.dismiss();

        }

        int i = view.getId();
        if (i == R.id.btnSelf) {
            MyApplication.getInstance().trackEvent(Constants.HEALTH_INS, "Clicked", "Health Insurance Cover For : Self");
            coverFor = 0;
            resetonClick();
            enableInputsForSelf();

        } else if (i == R.id.btnFamily) {
            MyApplication.getInstance().trackEvent(Constants.HEALTH_INS, "Clicked", "Health Insurance Cover For : Family");
            coverFor = 1;
            resetonClick();
            enableInputForFamily();

        } else if (i == R.id.btnParent) {
            MyApplication.getInstance().trackEvent(Constants.HEALTH_INS, "Clicked", "Health Insurance Cover For : Parent");
            coverFor = 2;
            resetonClick();
            enableInputForParent();

        } else if (i == R.id.img1) {
            enablePrevious(1);
            // enableOne(view);

        } else if (i == R.id.img2) {
            enablePrevious(2);
            // enableOne(view);

        } else if (i == R.id.img3) {
            enablePrevious(3);
            // enableOne(view);

        } else if (i == R.id.img4) {
            enablePrevious(4);
            //enableOne(view);

        } else if (i == R.id.img5) {
            enablePrevious(5);
            // enableOne(view);

        } else if (i == R.id.img6) {
            enablePrevious(6);
            // enableOne(view);

        } else if (i == R.id.btnGetHealthQuote) {
            Constants.hideKeyBoard(btnGetHealthQuote, getActivity());

            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Get quote health : get quote button for health"), Constants.HEALTH_INS), null);

            MyApplication.getInstance().trackEvent(Constants.HEALTH_INS, "Clicked", "Health Insurance Quote : Get Quote button");
            //region validation
            memberList.clear();

            if ((!et1.isEnabled()) && (!et2.isEnabled()) && (!et3.isEnabled()) && (!et4.isEnabled()) && (!et5.isEnabled()) && (!et6.isEnabled())) {

                showAlert("Please Select Member");
                return;
            }

            if (validateMemberAge() == false) {
                return;
            }

            if (coverFor == 0) {
                int tempAge = Integer.parseInt(et1.getText().toString());
                if (tempAge < 18) {

                    showAlert("Age should  be greater than or equal to 18 years");
                    et1.requestFocus();
                    return;
                }
            }

            if (coverFor == 1) {

                if (validateFamilyAge() == false) {
                    return;
                }

            }

            if (coverFor == 2) {

                if (validateParentAge() == false) {
                    return;
                }
            }

            if (etAmount.getText().toString().trim().length() == 0) {

                //Toast.makeText(getActivity(), "Select cover required.", Toast.LENGTH_SHORT).show();
                showAlert("Select cover required.");
                return;
            }
            if (5 > etAmount.getText().toString().trim().length()
                    || etAmount.getText().toString().trim().length() > 8) {
                showAlert("Invalid Sum Assured.");
                return;
            }

//                if (acCity.getText().toString().length() == 0) {
//                    acCity.setError("Select City.");
//                    acCity.setFocusable(true);
//                    return;
//                }


            if (etName.getText().toString().trim().length() == 0) {
                etName.setError("Enter Name");
                etName.requestFocus();
                etName.setFocusable(true);
                return;
            }
//                if (etMobile.getText().toString().length() < 10) {
//                    etMobile.setError("Invalid mobile");
//                    etMobile.setFocusable(true);
//                    return;
//                }

            if (etPincode.getText().toString().trim().length() == 0) {
                etPincode.setError("Enter Pincode");
                etPincode.requestFocus();
                etPincode.setFocusable(true);
                return;
            }
            if (etPincode.getText().toString().length() < 6) {
                etPincode.setError("Invalid Pincode");
                etPincode.requestFocus();
                etPincode.setFocusable(true);
                return;
            }

            //endregion

            healthRequestEntity.setContactName(etName.getText().toString());
            healthRequestEntity.setContactMobile(etMobile.getText().toString());
            healthRequestEntity.setSumInsured(etAmount.getText().toString());
            healthRequestEntity.setPincode(Integer.parseInt(etPincode.getText().toString()));
            //   healthRequestEntity.setCityID(new DBPersistanceController(getActivity()).getHealthCityID(acCity.getText().toString()));
            //  healthRequestEntity.setCityID(0);


            if (coverFor == 0) { // self

                healthRequestEntity.setPolicyFor("Self");
                if (et1.isEnabled() && et1.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et1.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));


                    if (Age >= 18) {
                        entity.setMemberType("Adult");
                    } else {
                        entity.setMemberType("Child");
                    }

                    memberList.add(entity);
                }

                healthRequestEntity.setMemberList(memberList);
                healthQuote.setHealthRequest(healthRequestEntity);


            } else if (coverFor == 1) { //family

                healthRequestEntity.setPolicyFor("Family");
                healthRequestEntity.setMaritalStatusID(2);

                if (et1.isEnabled() && et1.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et1.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));


                    if (Age >= 18) {
                        entity.setMemberType("Adult");
                    } else {
                        entity.setMemberType("Child");
                    }

                    memberList.add(entity);
                }
                if (et2.isEnabled() && et2.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et2.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));
                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }
                if (et3.isEnabled() && et3.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et3.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));
                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");


                    memberList.add(entity);
                }
                if (et4.isEnabled() && et4.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et4.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));

                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }
                if (et5.isEnabled() && et5.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et5.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));

                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }
                if (et6.isEnabled() && et6.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et6.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));

                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }

                healthRequestEntity.setMemberList(memberList);
                healthQuote.setHealthRequest(healthRequestEntity);

            } else if (coverFor == 2) {  // parent

                healthRequestEntity.setPolicyFor("Parent");
                if (et1.isEnabled() && et1.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et1.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));

                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }
                if (et2.isEnabled() && et2.getText().toString().length() != 0) {
                    MemberListEntity entity = new MemberListEntity();
                    int Age = Integer.parseInt(et2.getText().toString());
                    entity.setAge(Age);
                    entity.setMemberDOB(getDateFromAge(Age));

                    if (Age >= 18)
                        entity.setMemberType("Adult");
                    else
                        entity.setMemberType("Child");

                    memberList.add(entity);
                }


                healthRequestEntity.setMemberList(memberList);
                healthQuote.setHealthRequest(healthRequestEntity);
            }

            if (memberList.size() > 0) {

                //sort member list by higher age
                Collections.sort(memberList, new SortbyAge());
                Collections.reverse(memberList);
                healthQuote.getHealthRequest().setMemberList(memberList);


                //open pop up
                // region commented by rahul
//                    btnGetHealthQuote.setEnabled(false);
//                    Intent intent = new Intent(getActivity(), HealthMemberDetailsDialogActivity.class);
//                    intent.putExtra(MEMBER_LIST, healthQuote);
//                    startActivityForResult(intent, REQUEST_MEMBER);
                //endregion

                // added by rahul
                btnGetHealthQuote.setEnabled(false);


                ((HealthQuoteBottomTabsActivity) getActivity()).redirectToQuote(healthQuote);

            } else {
                Toast.makeText(getActivity(), "Please enter member age", Toast.LENGTH_SHORT).show();
            }

        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        btnGetHealthQuote.setEnabled(true);
//        if (requestCode == REQUEST_MEMBER) {
//            if (data != null) {
//                healthQuote = (HealthQuote) data.getParcelableExtra(HealthMemberDetailsDialogActivity.UPDATE_MEMBER_QUOTE);
//
//                //TODO: Health Quote accepted.
//                //1. pass bundle to quote fragment
//                //2. trigger quote fragment
//                // commented by rahul
//              //  ((HealthQuoteBottomTabsActivity) getActivity()).redirectToQuote(healthQuote);
//            }
//        }
//    }

    private void enableInputForParent() {

        btnSelf.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnFamily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnParent.setBackgroundColor(getResources().getColor(R.color.tab_color));

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);

        et1.setFocusable(false);
        et2.setFocusable(false);
        et3.setFocusable(false);
        et4.setFocusable(false);
        et5.setFocusable(false);
        et6.setFocusable(false);

        img1.setFocusable(true);
        img2.setFocusable(true);
        img3.setFocusable(false);
        img4.setFocusable(false);
        img5.setFocusable(false);
        img6.setFocusable(false);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(null);
        img4.setOnClickListener(null);
        img5.setOnClickListener(null);
        img6.setOnClickListener(null);

    }

    private void enableInputForFamily() {

        btnSelf.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnFamily.setBackgroundColor(getResources().getColor(R.color.tab_color));
        btnParent.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        img5.setEnabled(true);
        img6.setEnabled(true);

        et1.setFocusable(false);
        et2.setFocusable(false);
        et3.setFocusable(false);
        et4.setFocusable(false);
        et5.setFocusable(false);
        et6.setFocusable(false);

        img1.setFocusable(true);
        img2.setFocusable(true);
        img3.setFocusable(true);
        img4.setFocusable(true);
        img5.setFocusable(true);
        img6.setFocusable(true);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);

    }

    private void enableInputsForSelf() {

        btnSelf.setBackgroundColor(getResources().getColor(R.color.tab_color));
        btnFamily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnParent.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        img1.setEnabled(true);
        et1.setFocusable(false);
        img1.setFocusable(true);

        img1.setOnClickListener(this);
        img2.setOnClickListener(null);
        img3.setOnClickListener(null);
        img4.setOnClickListener(null);
        img5.setOnClickListener(null);
        img6.setOnClickListener(null);
    }

    private void resetonClick() {

        img1.setImageResource(R.mipmap.user_unselected);
        img2.setImageResource(R.mipmap.user_unselected);
        img3.setImageResource(R.mipmap.user_unselected);
        img4.setImageResource(R.mipmap.user_unselected);
        img5.setImageResource(R.mipmap.user_unselected);
        img6.setImageResource(R.mipmap.user_unselected);
        et1.setEnabled(false);
        et1.setText("");
        et2.setEnabled(false);
        et2.setText("");
        et3.setEnabled(false);
        et3.setText("");
        et4.setEnabled(false);
        et4.setText("");
        et5.setEnabled(false);
        et5.setText("");
        et6.setEnabled(false);
        et6.setText("");
        et1.setFocusableInTouchMode(false);
        et2.setFocusableInTouchMode(false);
        et3.setFocusableInTouchMode(false);
        et4.setFocusableInTouchMode(false);
        et5.setFocusableInTouchMode(false);
        et6.setFocusableInTouchMode(false);

    }

    private void enableOne(View view) {

        setNonFocusEditText();

        int i = view.getId();
        if (i == R.id.img1) {
            if (!et1.isEnabled()) {
                img1.setImageResource(R.mipmap.user_selected);
                et1.setEnabled(true);
                //   et1.setFocusableInTouchMode(true);
            } else {
                img1.setImageResource(R.mipmap.user_unselected);
                et1.setEnabled(false);
                et1.setText("");
                // et1.setFocusableInTouchMode(false);
            }

        } else if (i == R.id.img2) {
            if (!et2.isEnabled()) {
                img2.setImageResource(R.mipmap.user_selected);
                et2.setEnabled(true);
                // et2.setFocusableInTouchMode(true);
            } else {
                img2.setImageResource(R.mipmap.user_unselected);
                et2.setEnabled(false);
                et2.setText("");
                // et2.setFocusableInTouchMode(false);
            }

        } else if (i == R.id.img3) {
            if (!et3.isEnabled()) {
                img3.setImageResource(R.mipmap.user_selected);
                et3.setEnabled(true);
                //  et3.setFocusableInTouchMode(true);
            } else {
                img3.setImageResource(R.mipmap.user_unselected);
                et3.setEnabled(false);
                et3.setText("");
                //  et3.setFocusableInTouchMode(false);
            }

        } else if (i == R.id.img4) {
            if (!et4.isEnabled()) {
                img4.setImageResource(R.mipmap.user_selected);
                et4.setEnabled(true);
                // et4.setFocusableInTouchMode(true);
            } else {
                img4.setImageResource(R.mipmap.user_unselected);
                et4.setEnabled(false);
                et4.setText("");
                //   et4.setFocusableInTouchMode(false);
            }

        } else if (i == R.id.img5) {
            if (!et5.isEnabled()) {
                img5.setImageResource(R.mipmap.user_selected);
                et5.setEnabled(true);
                // et5.setFocusableInTouchMode(true);
            } else {
                img5.setImageResource(R.mipmap.user_unselected);
                et5.setEnabled(false);
                et5.setText("");
                //  et5.setFocusableInTouchMode(false);
            }

        } else if (i == R.id.img6) {
            if (!et6.isEnabled()) {
                img6.setImageResource(R.mipmap.user_selected);
                et6.setEnabled(true);
                //  et6.setFocusableInTouchMode(true);
            } else {
                img6.setImageResource(R.mipmap.user_unselected);
                et6.setEnabled(false);
                et6.setText("");
                //  et6.setFocusableInTouchMode(false);
            }

        }
    }


    private void setMemberSelcted(EditText et, ImageView img) {
        img.setImageResource(R.mipmap.user_selected);
        et.setEnabled(true);
        //  et.setFocusableInTouchMode(true);
    }

    private void setMemberDeSelcted(EditText et, ImageView img) {
        img.setImageResource(R.mipmap.user_unselected);
        et.setEnabled(false);
        et.setText("");
        //  et.setFocusableInTouchMode(false);
    }

    private void enablePrevious(int memberCount) {

        mPopupWindow.dismiss();
        mPopupWindowSelection.dismiss();

        switch (memberCount) {
            case 1:

                enableOne(img1);

                setMemberDeSelcted(et2, img2);
                setMemberDeSelcted(et3, img3);
                setMemberDeSelcted(et4, img4);
                setMemberDeSelcted(et5, img5);
                setMemberDeSelcted(et6, img6);

                break;

            case 2:

                enableOne(img2);
                setMemberSelcted(et1, img1);

                setMemberDeSelcted(et3, img3);
                setMemberDeSelcted(et4, img4);
                setMemberDeSelcted(et5, img5);
                setMemberDeSelcted(et6, img6);

                break;

            case 3:

                enableOne(img3);
                setMemberSelcted(et1, img1);
                setMemberSelcted(et2, img2);


                setMemberDeSelcted(et4, img4);
                setMemberDeSelcted(et5, img5);
                setMemberDeSelcted(et6, img6);
                break;

            case 4:
                enableOne(img4);
                setMemberSelcted(et1, img1);
                setMemberSelcted(et2, img2);
                setMemberSelcted(et3, img3);

                setMemberDeSelcted(et5, img5);
                setMemberDeSelcted(et6, img6);

                break;

            case 5:
                enableOne(img5);

                setMemberSelcted(et1, img1);
                setMemberSelcted(et2, img2);
                setMemberSelcted(et3, img3);
                setMemberSelcted(et4, img4);

                setMemberDeSelcted(et6, img6);
                break;

            case 6:

                enableOne(img6);
                setMemberSelcted(et1, img1);
                setMemberSelcted(et2, img2);
                setMemberSelcted(et3, img3);
                setMemberSelcted(et4, img4);
                setMemberSelcted(et5, img5);

                break;
        }


    }


    //region image click

    private void enableInputs() {

        switch (coverFor) {
            case 0: //self

                break;
            case 1: //family
                break;
            case 2: //parent
                break;
            default:
                break;
        }
    }

    //endregion

    private boolean validateMemberAge() {
//        if((et1.isEnabled() && et1.getText().toString().length() ==0)  || (et2.isEnabled() && et2.getText().toString().length() ==0) || (et3.isEnabled() && et3.getText().toString().length() ==0) ||
//                (et4.isEnabled() && et4.getText().toString().length() ==0)  || (et5.isEnabled() && et5.getText().toString().length() ==0) || (et6.isEnabled() && et6.getText().toString().length() ==0)   )
//        {
//            showAlert("Please Select Age");
//            return;
//        }

        if ((et1.isEnabled() && et1.getText().toString().length() == 0)) {
            showAlert("Please Enter Age");
            et1.requestFocus();
            return false;
        } else if (et2.isEnabled() && et2.getText().toString().length() == 0) {
            showAlert("Please Enter Age");
            et2.requestFocus();
            return false;
        } else if (et3.isEnabled() && et3.getText().toString().length() == 0) {
            showAlert("Please Enter Age");
            et3.requestFocus();
            return false;
        } else if (et4.isEnabled() && et4.getText().toString().length() == 0) {
            showAlert("Please Enter Age");
            et4.requestFocus();
            return false;
        } else if (et5.isEnabled() && et5.getText().toString().length() == 0) {
            showAlert("Please Enter Age");
            et5.requestFocus();
            return false;
        } else if (et6.isEnabled() && et6.getText().toString().length() == 0) {
            showAlert("Please Enter Age");
            et6.requestFocus();
            return false;
        }


        return true;
    }


    private boolean validateFamilyAge() {

        int Age1 = 0;
        int Age2 = 0;
        int Age3 = 0;
        int Age4 = 0;
        int Age5 = 0;
        int Age6 = 0;
        int countSel = 0;
        int count = 0;
        int countBelow = 0;

        boolean blnchk = true;
        if ((et1.isEnabled() && et1.getText().toString().length() > 0)) {
            Age1 = Integer.parseInt(et1.getText().toString());
            if (Age1 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }

            countSel = countSel + 1;

        }
        if (et2.isEnabled() && et2.getText().toString().length() > 0) {
            Age2 = Integer.parseInt(et2.getText().toString());
            if (Age2 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }

            countSel = countSel + 1;

        }
        if (et3.isEnabled() && et3.getText().toString().length() > 0) {
            Age3 = Integer.parseInt(et3.getText().toString());
            if (Age3 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }

            countSel = countSel + 1;

        }
        if (et4.isEnabled() && et4.getText().toString().length() > 0) {
            Age4 = Integer.parseInt(et4.getText().toString());
            if (Age4 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }

            countSel = countSel + 1;

        }
        if (et5.isEnabled() && et5.getText().toString().length() > 0) {
            Age5 = Integer.parseInt(et5.getText().toString());
            if (Age5 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }
            countSel = countSel + 1;

        }
        if (et6.isEnabled() && et6.getText().toString().length() > 0) {
            Age6 = Integer.parseInt(et6.getText().toString());
            if (Age6 >= 18) {
                count = count + 1;
            } else {
                countBelow = countBelow + 1;
            }

            countSel = countSel + 1;

        }

        if (countSel < 2) {

            showAlert("Select at least 2 members");
            blnchk = false;
        } else if (count == 0) {
            showAlert("Atleast one member age should be greater than or equal to 18 years");
            blnchk = false;
        } else if (count > 2) {
            showAlert("More than 2 member age should not be greater than or equal to 18 years");
            blnchk = false;
        } else if (countBelow > 4) {
            showAlert("More than 4 children's are not allowed");
            blnchk = false;
        } else {

            blnchk = true;
        }

        return blnchk;
    }


    private boolean validateParentAge() {
        int Age1, Age2 = 0;
        boolean blnchk = true;
        boolean blnAge = true;
        int count = 0;
        int countSel = 0;
        if ((et1.isEnabled() && et1.getText().toString().length() > 0)) {
            Age1 = Integer.parseInt(et1.getText().toString());
            if (Age1 < 36) {

                blnAge = false;
            }
            countSel = countSel + 1;

        }
        if (et2.isEnabled() && et2.getText().toString().length() > 0) {
            Age2 = Integer.parseInt(et2.getText().toString());
            if (Age2 < 36) {

                blnAge = false;
            }
            countSel = countSel + 1;
        }

        if (countSel < 2) {

            showAlert("Select two member");
            blnchk = false;
        } else if (blnAge == false) {
            showAlert("Age should  be greater than or equal to 36 years");
            blnchk = false;

        }


        return blnchk;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            getActivity().finish();
            return true;
        } else if (i == R.id.action_home) {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {


        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindowSelection.dismiss();

        }

        int i = view.getId();
        if (i == R.id.etOne) {
            if (hasFocus) {

                OpenPoupWnidow(et1);
            }

        } else if (i == R.id.etTwo) {
            if (hasFocus) {
                OpenPoupWnidow(et2);
            }


        } else if (i == R.id.etThree) {
            if (hasFocus) {
                OpenPoupWnidow(et3);
            }

        } else if (i == R.id.etFour) {
            if (hasFocus) {

                OpenPoupWnidow(et4);
            }

        } else if (i == R.id.etFive) {
            if (hasFocus) {
                OpenPoupWnidow(et5);
            }

        } else if (i == R.id.etSix) {
            if (hasFocus) {
                OpenPoupWnidow(et6);
            }


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (editText != null) {
            if (editText.getId() == view.getId() && mPopupWindow.isShowing()) {
                return false;
            }
        }

        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindowSelection.dismiss();

        }

        int i = view.getId();
        if (i == R.id.etOne) {
            OpenPoupWnidow(et1);

        } else if (i == R.id.etTwo) {
            OpenPoupWnidow(et2);


        } else if (i == R.id.etThree) {
            OpenPoupWnidow(et3);


        } else if (i == R.id.etFour) {
            OpenPoupWnidow(et4);


        } else if (i == R.id.etFive) {
            OpenPoupWnidow(et5);


        } else if (i == R.id.etSix) {
            OpenPoupWnidow(et6);


        }
        return false;
    }
}
