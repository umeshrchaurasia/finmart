package com.datacomp.magicfinmart.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;

public class HealthMemberDetailsDialogActivity extends BaseActivity implements View.OnClickListener {

    public static final String UPDATE_MEMBER_QUOTE = "healthquote_update";
    public static final String UPDATE_MEMBER_DOB = "healthquote_member_dob";

    HealthQuote healthQuote;
    Button btnContinue;
    List<MemberListEntity> listMemberList;
    RecyclerView rvMemberDetail;
    HealthMemberDetailsViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_member_details_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        init_widgets();
        listMemberList = new ArrayList<>();

        if (getIntent().getParcelableExtra(HealthQuoteFragment.MEMBER_LIST) != null) {
            healthQuote = getIntent().getParcelableExtra(HealthQuoteFragment.MEMBER_LIST);
            popupMemberDetail();
        }

    }

    private void popupMemberDetail() {

        int AdultCount = AdultCounting();
        adapter = new HealthMemberDetailsViewAdapter(this, healthQuote.getHealthRequest().getMemberList(), healthQuote.getHealthRequest().getPolicyFor(),AdultCount);
        rvMemberDetail.setAdapter(adapter);
    }

    private void init_widgets() {

        btnContinue = (Button) findViewById(R.id.btnContinue);
        rvMemberDetail = (RecyclerView) findViewById(R.id.rvMemberDetail);
        rvMemberDetail.setLayoutManager(new LinearLayoutManager(this));
        btnContinue.setOnClickListener(this);
    }

    private int AdultCounting()
    {
        List<MemberListEntity> listMember = healthQuote.getHealthRequest().getMemberList();
        int isAdultCount = 0;
        for (int i = 0; i < listMember.size(); i++) {
            MemberListEntity entity = listMember.get(i);
            if (entity.getAge() >= 18) {
                isAdultCount = isAdultCount + 1;
            }
        }
        return  isAdultCount;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnContinue) {
            List<MemberListEntity> updateMember = new ArrayList<>();
            List<MemberListEntity> listMember = healthQuote.getHealthRequest().getMemberList();


            for (int i = 0; i < listMember.size(); i++) {

                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberDOBTemp() == null || entity.getMemberDOBTemp().equals("")) {
                    showAlert("Please Enter Date Of Birth");
                    return;
                }
            }
            // region  Duplication Check for : Adult Gender

            //region for Family
            if (healthQuote.getHealthRequest().getPolicyFor().toLowerCase().equals("family")) {
                int isFamAdultCount = 0;
                for (int i = 0; i < listMember.size(); i++) {

                    MemberListEntity entity = listMember.get(i);
                    if (entity.getMemberRelationShip().toLowerCase().equals("self")  || entity.getMemberRelationShip().toLowerCase().equals("spouse")) {
                        isFamAdultCount++;
                    }
                }

                if(isFamAdultCount > 1)
                {
                   if( listMember.get(0).getMemberGender().equals(listMember.get(1).getMemberGender()))
                   {
                       showAlert("Same gender should not be selected for Self and Spouse");
                       return;
                   }
                }
            }
            //endregion

            //region for Parent
            if (healthQuote.getHealthRequest().getPolicyFor().toLowerCase().equals("parent")) {
                int isParentCount = 0;
                for (int i = 0; i < listMember.size(); i++) {

                    MemberListEntity entity = listMember.get(i);
                    if (entity.getMemberRelationShip().toLowerCase().equals("father")  || entity.getMemberRelationShip().toLowerCase().equals("mother")) {
                        isParentCount++;
                    }
                }

                if(isParentCount > 1)
                {
                    if( listMember.get(0).getMemberGender().equals(listMember.get(1).getMemberGender()))
                    {
                        showAlert("Same gender should not be selected for Father and Mother");
                        return;
                    }
                }
            }
            //endregion

            //endregion

            // region  Duplication Check for :self
            int isRelRepeat = 0;
            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("self")) {
                    isRelRepeat++;
                }
            }
            if (isRelRepeat > 1) {
                showAlert("Self should not be selected multiple times");
                return;
            } else {
                isRelRepeat = 0;
            }
            //endregion

            // region  Duplication Check for :Spouse

            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("spouse")) {
                    isRelRepeat++;
                }
            }
            if (isRelRepeat > 1) {
                showAlert("Spouse should not be selected multiple times");
                return;
            } else {
                isRelRepeat = 0;
            }
            //endregion

            // region  Duplication Check for :Father

            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("father")) {
                    isRelRepeat++;
                }
            }
            if (isRelRepeat > 1) {
                showAlert("Father should not be selected multiple times");
                return;
            } else {
                isRelRepeat = 0;
            }
            //endregion

            // region  Duplication Check for :Mother

            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("mother")) {
                    isRelRepeat++;
                }
            }
            if (isRelRepeat > 1) {
                showAlert("Mother should not be selected multiple times");
                return;
            } else {
                isRelRepeat = 0;
            }
            //endregion

            int isChildRepeat = 0;
            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("child1")) {
                    isChildRepeat++;
                }
            }
            if (isChildRepeat > 1) {
                showAlert(" Same Child should not be selected multiple times");
                return;
            } else {
                isChildRepeat = 0;
            }

            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("child2")) {
                    isChildRepeat++;
                }
            }
            if (isChildRepeat > 1) {
                showAlert(" Same Child should not be selected multiple times");
                return;
            } else {
                isChildRepeat = 0;
            }

            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("child3")) {
                    isChildRepeat++;
                }
            }
            if (isChildRepeat > 1) {
                showAlert(" Same Child should not be selected multiple times");
                return;
            } else {
                isChildRepeat = 0;
            }


            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("child4")) {
                    isChildRepeat++;
                }
            }

            if (isChildRepeat > 1) {
                showAlert(" Same Child should not be selected multiple times");
                return;
            } else {
                isChildRepeat = 0;
            }


            for (int i = 0; i < listMember.size(); i++) {
                MemberListEntity entity = listMember.get(i);
                if (entity.getMemberRelationShip().toLowerCase().equals("self")) {
                    entity.setMemberNumber("1");
                    entity.setMemberTypeID("1");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("spouse")) {
                    entity.setMemberNumber("2");
                    entity.setMemberTypeID("2");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("father")) {
                    entity.setMemberNumber("3");
                    entity.setMemberTypeID("5");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("mother")) {
                    entity.setMemberNumber("4");
                    entity.setMemberTypeID("4");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("child1")) {
                    entity.setMemberNumber("5");
                    entity.setMemberTypeID("3");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("child2")) {
                    entity.setMemberNumber("6");
                    entity.setMemberTypeID("3");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("child3")) {
                    entity.setMemberNumber("7");
                    entity.setMemberTypeID("3");
                } else if (entity.getMemberRelationShip().toLowerCase().equals("child4")) {
                    entity.setMemberNumber("8");
                    entity.setMemberTypeID("3");
                }

                updateMember.add(entity);
            }


            healthQuote.getHealthRequest().setMemberList(updateMember);

            Intent intent = new Intent();
            intent.putExtra(UPDATE_MEMBER_QUOTE, healthQuote);
            setResult(HealthQuoteFragment.REQUEST_MEMBER, intent);
            finish();

        }
    }


    public void updateMemberList(MemberListEntity entity, int maritialStatus, int position) {

        if (maritialStatus != 0) {
            healthQuote.getHealthRequest().setMaritalStatusID(maritialStatus);
        }

        MemberListEntity member = healthQuote.getHealthRequest().getMemberList().get(position);
        member = entity;
        healthQuote.getHealthRequest().getMemberList().set(position, member);

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(UPDATE_MEMBER_DOB, (ArrayList<MemberListEntity>)healthQuote.getHealthRequest().getMemberList());
        setResult(HealthQuoteFragment.REQUEST_MEMBER, intent);
        finish();
    }
}
