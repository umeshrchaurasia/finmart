package com.datacomp.magicfinmart.helpfeedback.contactus;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ContactUsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ContactUsResponse;

public class ContactUsActivity extends BaseActivity implements IResponseSubcriber {
    RecyclerView rvContactUs;
    ContactUsAdapter mAdapter;
    List<ContactUsEntity> contactUsEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contactUsEntityList = new ArrayList<>();
       /* contactUsEntityList.add(new ContactUsEntity("Finmart Support Centre", "022 - 66048200", "fba.support@magicfinmart.com", "www.magicfinmart.com"));
        contactUsEntityList.add(new ContactUsEntity("Datacomp Web Technologies Pvt.Ltd.", "022 - 61838000", "support@datacompwebtech.com", "www.datacompwebtech.com"));
        contactUsEntityList.add(new ContactUsEntity("Landmark Insurance Brokers P Ltd", "1800-419-419-9", "customercare@policyboss.com", "www.policyboss.com"));
        contactUsEntityList.add(new ContactUsEntity("Rupeeboss Financial Services P Ltd", "1800-267-629-6", "wecare@rupeeboss.com", "www.rupeeboss.com"));
        contactUsEntityList.add(new ContactUsEntity("Health Assure", "022 - 6167 6666", "hello@healthassure.in", "www.heathassure.in"));
        contactUsEntityList.add(new ContactUsEntity("FinPeace Technologies Pvt. Ltd.", "022 49240109", "contact@finpeace.in", "www.finpeace.in"));
*/
        rvContactUs = (RecyclerView) findViewById(R.id.rvContactUs);
        rvContactUs.setHasFixedSize(true);
        rvContactUs.setLayoutManager(new LinearLayoutManager(this));
        showDialog();
        new MasterController(this).getContactList(this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof ContactUsResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                if (((ContactUsResponse) response).getMasterData() != null) {
                    if (((ContactUsResponse) response).getMasterData().size() > 0) {
                        contactUsEntityList = ((ContactUsResponse) response).getMasterData();
                        mAdapter = new ContactUsAdapter(this, contactUsEntityList);
                        rvContactUs.setAdapter(mAdapter);
                    }
                }
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
