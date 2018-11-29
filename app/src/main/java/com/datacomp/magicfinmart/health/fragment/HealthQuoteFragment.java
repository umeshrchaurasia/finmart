package com.datacomp.magicfinmart.health.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.health.compare.HealthCompareActivity;
import com.datacomp.magicfinmart.health.healthquotetabs.HealthQuoteBottomTabsActivity;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.SortbyInsurer;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.health.HealthController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.HealthCompareRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteCompareResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteExpResponse;

/**
 * Created by Nilesh Birhade on 14/02/2018.
 */

public class HealthQuoteFragment extends BaseFragment implements IResponseSubcriber, View.OnClickListener, BaseFragment.PopUpListener {

    public static final int RESULT_COMPARE = 1000;
    private static final String FLOATER = "FLOATER STANDARD";
    private static final String INDIVIDUAL = "INDIVIDUAL STANDARD";
    public static final String HEALTH_COMPARE = "health_compare";
    private static final String SHARE_TEXT = " Results from www.policyboss.com";
    public static final int REQUEST_MEMBER = 4444;
    public static final String MEMBER_LIST = "member_list";

    TextView txtCoverType, txtCoverAmount, textCover;
    HealthQuote healthQuote;
    LinearLayout llMembers;
    ImageView webViewLoader;
    RecyclerView rvHealthQuote;
    HealthQuoteAdapter adapter;
    ImageView ivEdit;
    TextView tvCount;// txtCompareCount;

    List<HealthQuoteEntity> listCompare;
    List<HealthQuoteEntity> listShareAll;
    List<HealthQuoteEntity> listDataHeader;
    HashMap<Integer, List<HealthQuoteEntity>> listDataChild;

    String jsonShareString = "";

    List<HealthQuoteEntity> listHeader;
    List<HealthQuoteEntity> listChild;

    HealthQuoteEntity buyHealthQuoteEntity;
    //ImageView ivHealthCompare;


    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthcontent_quote, container, false);
        registerPopUp(this);
        initView(view);
        setListener();

        //set actionmode call back
        actionModeCallback = new ActionModeCallback();

        Constants.hideKeyBoard(ivEdit, getActivity());

        listCompare = new ArrayList<>();
        listDataHeader = new ArrayList<>();
        listShareAll = new ArrayList<>();
        listDataChild = new HashMap<Integer, List<HealthQuoteEntity>>();
        // txtCompareCount.setVisibility(View.INVISIBLE);
        // ivHealthCompare.setVisibility(View.INVISIBLE);

        if (getArguments() != null) {
            if (getArguments().getParcelable(HealthQuoteBottomTabsActivity.QUOTE_DATA) != null) {
                healthQuote = new HealthQuote();
                healthQuote = getArguments().getParcelable(HealthQuoteBottomTabsActivity.QUOTE_DATA);
                bindHeaders();
                fetchQuotes();
            }
        }

        return view;
    }

    private void setListener() {
        ivEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivEdit) {
            ((HealthQuoteBottomTabsActivity) getActivity()).redirectToInput();
        }
    }

    public void redirectToHealthCompare() {

        if (listCompare.size() == 1) {
            showAlert("Please select at least 2 plans for comparison");
        } else {
            Intent intent = new Intent(getActivity(), HealthCompareActivity.class);
            intent.putParcelableArrayListExtra(HEALTH_COMPARE, (ArrayList<? extends Parcelable>) listCompare);
            startActivity(intent);
        }

    }

    private void bindHeaders() {

        if (healthQuote.getHealthRequest().getMemberList() != null &&
                healthQuote.getHealthRequest().getMemberList().size() > 1) {
            txtCoverType.setText(FLOATER);
        } else {
            txtCoverType.setText(INDIVIDUAL);
        }
        tvCount.setTag(R.id.tvCount, 0);
        tvCount.setText("0 " + SHARE_TEXT);
        textCover.setText("COVER - ");
        txtCoverAmount.setText(healthQuote.getHealthRequest().getSumInsured());
        bindImages(healthQuote.getHealthRequest().getMemberList());

    }

    private void bindImages(List<MemberListEntity> listmember) {
        if (listmember != null) {
            for (int i = 0; i < listmember.size(); i++) {

                ImageView imageview = new ImageView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout
                        .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

                imageview.setPadding(2, 0, 2, 0);

                if (listmember.get(i).getAge() >= 18)
                    imageview.setImageResource(R.mipmap.adult);
                else
                    imageview.setImageResource(R.mipmap.child);

                imageview.setLayoutParams(params);
                llMembers.addView(imageview);
            }
        }
    }

    private void initView(View view) {
        webViewLoader = (ImageView) view.findViewById(R.id.webViewLoader);
        txtCoverAmount = (TextView) view.findViewById(R.id.txtCoverAmount);
        textCover = (TextView) view.findViewById(R.id.textCover);
        txtCoverType = (TextView) view.findViewById(R.id.txtCoverType);
        llMembers = (LinearLayout) view.findViewById(R.id.llMembers);
        tvCount = (TextView) view.findViewById(R.id.tvCount);
        ivEdit = (ImageView) view.findViewById(R.id.ivEdit);

        // txtCompareCount = (TextView) view.findViewById(R.id.txtCompareCount);
        rvHealthQuote = (RecyclerView) view.findViewById(R.id.rvHealthQuote);
        rvHealthQuote.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHealthQuote.setLayoutManager(layoutManager);

        // ivHealthCompare = (ImageView) view.findViewById(R.id.ivHealthCompare);
    }

    public void redirectToDetail(HealthQuoteEntity entity) {

        Intent intent = new Intent(getActivity(), HealthQuoteDetailsDialogActivity.class);
        intent.putExtra("DETAIL", entity);
        intent.putExtra("NAME", healthQuote.getHealthRequest().getContactName());
        startActivityForResult(intent, RESULT_COMPARE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case (RESULT_COMPARE): {
                if (resultCode == RESULT_COMPARE) {
                    if (data.getParcelableExtra("BUY") != null) {
                        //  redirectToBuy((HealthQuoteEntity) data.getParcelableExtra("BUY"));
                        popUpHealthMemberDetails((HealthQuoteEntity) data.getParcelableExtra("BUY"));
                    }
                }
                break;
            }

            case (REQUEST_MEMBER): {
                if (data != null) {
                    if ((HealthQuote) data.getParcelableExtra(HealthMemberDetailsDialogActivity.UPDATE_MEMBER_QUOTE) != null) {
                        healthQuote = (HealthQuote) data.getParcelableExtra(HealthMemberDetailsDialogActivity.UPDATE_MEMBER_QUOTE);
                        // commented by rahul
                        redirectToBuy(buyHealthQuoteEntity);
                    } else if (data.getParcelableArrayListExtra(HealthMemberDetailsDialogActivity.UPDATE_MEMBER_DOB) != null) {
                        healthQuote.getHealthRequest().setMemberList(data.<MemberListEntity>getParcelableArrayListExtra(HealthMemberDetailsDialogActivity.UPDATE_MEMBER_DOB));
                    }
                }
            }
            break;
        }
    }

    public void redirectToBuy(HealthQuoteEntity entity) {
        if (Utility.checkShareStatus(getActivity()) == 1) {


            buyHealthQuoteEntity = new HealthQuoteEntity();
            buyHealthQuoteEntity = entity;
            HealthCompareRequestEntity compareRequestEntity = new HealthCompareRequestEntity();
            compareRequestEntity.setProdID(String.valueOf(entity.getProdID()));
            compareRequestEntity.setPlanID(String.valueOf(buyHealthQuoteEntity.getPlanID()));
            compareRequestEntity.setHealthRequestId(String.valueOf(healthQuote.getHealthRequestId()));
            compareRequestEntity.setSelectedPrevInsID(healthQuote.getHealthRequest().getSelectedPrevInsID());
            compareRequestEntity.setInsImage(entity.getInsurerLogoName());
            compareRequestEntity.setMemberlist(healthQuote.getHealthRequest().getMemberList());

            showDialog();
            new HealthController(getActivity()).compareQuote(compareRequestEntity, this);
        } else {
            openPopUp(ivEdit, "Message", "Your POSP status is INACTIVE", "OK", true);

        }
        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Buy health : buy button for health"), Constants.HEALTH_INS), null);
    }

    public void popUpHealthMemberDetails(HealthQuoteEntity entity) {
        buyHealthQuoteEntity = entity;
        Intent intent = new Intent(getActivity(), HealthMemberDetailsDialogActivity.class);
        intent.putExtra(MEMBER_LIST, healthQuote);
        startActivityForResult(intent, REQUEST_MEMBER);
    }

    public void getgoogleTrackingHealthBuy()
    {
        MyApplication.getInstance().trackEvent( Constants.HEALTH_INS,"Clicked","Health Insurance BUY NOW :Buy Now button");
    }
    public void fetchQuotes() {
        //visibleLoader();
        showDialog("Please wait.. fetching quotes");
        new HealthController(getActivity()).getHealthQuoteExp(healthQuote, this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        //hideLoader();
        cancelDialog();
        if (response instanceof HealthQuoteExpResponse) {
            if (((HealthQuoteExpResponse) response).getMasterData().getHealth_quote().getHeader() != null) {

                //update request id
                ((HealthQuoteBottomTabsActivity) getActivity()).updateRequestID(((HealthQuoteExpResponse) response).getMasterData().getHealthRequestId());
                listHeader = ((HealthQuoteExpResponse) response).getMasterData()
                        .getHealth_quote().getHeader();
                listChild = ((HealthQuoteExpResponse) response).getMasterData()
                        .getHealth_quote().getChild();


                for (int i = 0; i < listHeader.size(); i++) {

                    if (listHeader.get(i).getServicetaxincl().equalsIgnoreCase("e"))
                        listHeader.get(i).setDisplayPremium(listHeader.get(i).getNetPremium());
                    else if (listHeader.get(i).getServicetaxincl().equalsIgnoreCase("i"))
                        listHeader.get(i).setDisplayPremium(listHeader.get(i).getGrossPremium());

                }

                for (int i = 0; i < listChild.size(); i++) {

                    if (listChild.get(i).getServicetaxincl().equalsIgnoreCase("e"))
                        listChild.get(i).setDisplayPremium(listChild.get(i).getNetPremium());
                    else if (listChild.get(i).getServicetaxincl().equalsIgnoreCase("i"))
                        listChild.get(i).setDisplayPremium(listChild.get(i).getGrossPremium());

                }


                //share all list json
                listShareAll.addAll(listHeader);
                listShareAll.addAll(listChild);

                prepareChild();

                //share data
                // new AsyncShareJson().execute();

            }
        } else if (response instanceof HealthQuoteCompareResponse) {

            int finalPremium = 0;
            if (buyHealthQuoteEntity.getServicetaxincl().toLowerCase().equals("e")) {
                finalPremium = (int) Math.round(buyHealthQuoteEntity.getNetPremium());
            } else if (buyHealthQuoteEntity.getServicetaxincl().toLowerCase().equals("i")) {
                finalPremium = (int) Math.round(buyHealthQuoteEntity.getGrossPremium());
            }

            if (finalPremium == (int) Math.round(((HealthQuoteCompareResponse) response).getMasterData().getNetPremium()))
                redirectProposal((HealthQuoteCompareResponse) response);
            else
                buyHealthDialog((HealthQuoteCompareResponse) response);


        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        if (t.getMessage().equals("FAILURE")) {
            ErrorDialog();

        } else {
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void ErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Try again..");
        builder.setMessage("We are unable to verify the premium from insurer at this moment..!");
        builder.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                redirectToBuy(buyHealthQuoteEntity);
            }
        })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void buyHealthDialog(final HealthQuoteCompareResponse healthQuoteCompareResponse) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_compare_health_quote, null);
        builder.setView(view);
        ImageView imgInsurerLogo = (ImageView) view.findViewById(R.id.imgInsurerLogo);
        TextView txtPlanName = (TextView) view.findViewById(R.id.txtPlanName);
        TextView txtProductName = (TextView) view.findViewById(R.id.txtProductName);
        TextView txtEstPremium = (TextView) view.findViewById(R.id.txtEstPremium);
        TextView txtInsPremium = (TextView) view.findViewById(R.id.txtInsPremium);
        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        Button btnClose = (Button) view.findViewById(R.id.btnClose);
        final AlertDialog dialog = builder.create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                redirectProposal(healthQuoteCompareResponse);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        int finalPremium = 0;
        if (buyHealthQuoteEntity.getServicetaxincl().toLowerCase().equals("e")) {
            finalPremium = (int) Math.round(buyHealthQuoteEntity.getNetPremium());
        } else if (buyHealthQuoteEntity.getServicetaxincl().toLowerCase().equals("i")) {
            finalPremium = (int) Math.round(buyHealthQuoteEntity.getGrossPremium());
        }

        Glide.with(this).load(buyHealthQuoteEntity.getInsurerLogoName())
                .into(imgInsurerLogo);
        txtProductName.setText("" + buyHealthQuoteEntity.getProductName());
        txtPlanName.setText("" + buyHealthQuoteEntity.getPlanName());
        txtEstPremium.setText("\u20B9 " + finalPremium);
        txtInsPremium.setText("\u20B9 " + Math.round(healthQuoteCompareResponse.getMasterData().getNetPremium()));
        dialog.show();
        TextView msgTxt = (TextView) dialog.findViewById(android.R.id.message);
        msgTxt.setTextSize(12.0f);
    }

    private void redirectProposal(HealthQuoteCompareResponse healthQuoteCompareResponse) {
        Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
        intent.putExtra("URL", healthQuoteCompareResponse.getMasterData().getProposerPageUrl());
        intent.putExtra("TITLE", "HEALTH INSURANCE");
        intent.putExtra("NAME", "HEALTH INSURANCE");
        startActivity(intent);
    }

    private void prepareChild() {
        for (int i = 0; i < listHeader.size(); i++) {

            HealthQuoteEntity header = listHeader.get(i);
            List<HealthQuoteEntity> childList = new ArrayList<>();
            int childCount = 0;
            for (int j = 0; j < listChild.size(); j++) {
                HealthQuoteEntity child = listChild.get(j);
                //TODO: Prepare child as per insurer id and product name
                if (header.getInsurerId() == child.getInsurerId())
                //&& header.getProductName().equalsIgnoreCase(child.getProductName())) {
                {
                    childList.add(child);
                }
                if (header.getInsurerId() == child.getInsurerId()
                        && header.getProductName().equalsIgnoreCase(child.getProductName())) {
                    //child count with product name
                    childCount++;
                }
            }

            header.setTotalChilds(childCount);

            listDataChild.put(header.getInsurerId(), childList);
            listDataHeader.add(header);

        }
        bindRecyclerView(listDataHeader);
        int total = listHeader.size();// + listChild.size();
        shareTextCount(total, true);
    }

    public void shareTextCount(int total, boolean isAdd) {
        int count = (int) tvCount.getTag(R.id.tvCount);
        if (isAdd) {
            count = total + count;
        } else {
            count = count - total;
        }
        tvCount.setText(count + SHARE_TEXT);
        tvCount.setTag(R.id.tvCount, count);
    }

    public void bindRecyclerView(List<HealthQuoteEntity> list) {
        adapter = new HealthQuoteAdapter(this, list);
        rvHealthQuote.setAdapter(adapter);

    }

    private void refreshAdapter(List<HealthQuoteEntity> list) {
        adapter.refreshNewQuote(list);
    }

    private void updateMainQuoteAdapter(List<HealthQuoteEntity> list) {
        adapter.updateMainQuote(list);
    }

    public void addMoreQuote(HealthQuoteEntity entity) {

        List<HealthQuoteEntity> childList = listDataChild.get(entity.getInsurerId());
        List<HealthQuoteEntity> preparedchildList = new ArrayList<>();

        for (int i = 0; i < childList.size(); i++) {
            if (entity.getInsurerId() == childList.get(i).getInsurerId()
                    && entity.getProductName().equalsIgnoreCase(childList.get(i).getProductName())) {
                preparedchildList.add(childList.get(i));
            }
        }

        refreshAdapter(preparedchildList);
        shareTextCount((preparedchildList.size()), true);
    }


    //region Action Mode CallBack Interface
    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.health_menu_actionmode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.health_share) {// delete all the selected messages
                //Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                new AsyncShareJson(listCompare).execute();
                // mode.finish();
                return true;
            } else if (i == R.id.health_compare) {
                redirectToHealthCompare();
                return true;
            } else {
                return false;
            }
        }


        @Override
        public void onDestroyActionMode(ActionMode mode) {

            actionMode = null;
            for (int i = 0; i < listDataHeader.size(); i++) {
                //reset all selected quotes
                listDataHeader.get(i).setCompare(false);
            }
            updateMainQuoteAdapter(listDataHeader);
            //clear selected compare list to reset
            listCompare.clear();

            //share all list json
            listShareAll.clear();
            listShareAll.addAll(listHeader);
            listShareAll.addAll(listChild);
        }
    }
    //endregion


    @Override
    public void onPause() {
        super.onPause();
        if (actionMode != null) {
            listCompare.clear();
            listShareAll.clear();
            actionMode.finish();
        }

    }

    public void addRemoveCompare(HealthQuoteEntity entity, boolean isAdd) {
        if (isAdd) {
            if (listCompare.size() < 4) {

                listCompare.add(entity);

                if (actionMode == null) {
                    actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
                }

                actionModeRefresh();

            } else {
                showAlert("Cannot select more than 4 quotes");
            }
        } else {
            //remove item from list
            for (Iterator<HealthQuoteEntity> iter = listCompare.listIterator(); iter.hasNext(); ) {
                HealthQuoteEntity a = iter.next();
                if (a.getInsurerId() == entity.getInsurerId() && a.getPlanID() == entity.getPlanID()
                        && a.getProductName().equalsIgnoreCase(entity.getProductName())) {
                    iter.remove();
                }
            }

            actionModeRefresh();

        }

    }

    public void actionModeRefresh() {
        if (listCompare.size() == 0) {

            actionMode.finish();
        } else {
            actionMode.setTitle("" + listCompare.size());
            actionMode.invalidate();
        }
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
//        if (view.getId() == R.id.ivHealthCompare) {
//            dialog.cancel();
//        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
//        if (view.getId() == R.id.ivHealthCompare) {
//            dialog.cancel();
//        }
    }

    class AsyncShareJson extends AsyncTask<Void, Void, String> {
        List<HealthQuoteEntity> shareList = new ArrayList<>();

        public AsyncShareJson(List<HealthQuoteEntity> list) {
            shareList = list;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //shareList.addAll(listHeader);
            //shareList.addAll(listChild);

            //shareList.addAll(listCompare);
            Collections.sort(shareList, new SortbyInsurer());

            JSONArray jsonArrayNew = new JSONArray();
            Gson gson = new Gson();
            String json = gson.toJson(shareList);
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    if (obj.has("LstbenfitsFive")) {
                        obj.remove("LstbenfitsFive");
                    }
                    jsonArrayNew.put(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonArrayNew.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            jsonShareString = "";
            jsonShareString = s;
            shareAllPdf();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.share_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            getActivity().finish();
            return true;
        } else if (i == R.id.action_share) {
            new AsyncShareJson(listShareAll).execute();
            // shareAllPdf();

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

    private void shareAllPdf() {
        if (Utility.checkShareStatus(getActivity()) == 1) {

            if (!jsonShareString.equals("")) {
                Intent intent = new Intent(getActivity(), ShareQuoteActivity.class);
                intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "HEALTH_ALL_QUOTE");
                intent.putExtra("RESPONSE", jsonShareString);
                intent.putExtra("NAME", healthQuote.getHealthRequest().getContactName());
                startActivity(intent);
            }
        } else {
            openPopUp(ivEdit, "Message", "Your POSP status is INACTIVE", "OK", true);

        }
    }


}
