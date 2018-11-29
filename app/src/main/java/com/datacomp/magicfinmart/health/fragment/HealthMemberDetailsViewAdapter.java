package com.datacomp.magicfinmart.health.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;

public class HealthMemberDetailsViewAdapter extends RecyclerView.Adapter<HealthMemberDetailsViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    List<MemberListEntity> listMemberList;
    //String[] relationShip;
    List<String> relationShip;
    String PolicyFor;
    List<String> temrelationShip;
    List<String> childRelationShip;
    int adultCount = 0;
    SimpleDateFormat simpleDateFormat;
     public Calendar calendarMain ;

    // data is passed into the constructor
    HealthMemberDetailsViewAdapter(Context context, List<MemberListEntity> listMemberList, String policyFor, int adultCount) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.listMemberList = listMemberList;
        this.adultCount = adultCount;
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        relationShip = Arrays.asList(mContext.getResources().getStringArray(R.array.health_relationship));
         calendarMain = Calendar.getInstance();
        this.PolicyFor = policyFor;
        setFamilyList();
        setChildyList();
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_memberdetails, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final MemberListEntity entity = listMemberList.get(position);

        if (PolicyFor.toLowerCase().equals("self")) {

            //relationShip = mContext.getResources().getStringArray(R.array.health_relationship);
            relationShip = Arrays.asList(mContext.getResources().getStringArray(R.array.health_relationship));
            holder.spHealthRelation.setEnabled(false);

        } else if (PolicyFor.toLowerCase().equals("parent")) {

            //relationShip = mContext.getResources().getStringArray(R.array.health_parent_relationship);
            relationShip = Arrays.asList(mContext.getResources().getStringArray(R.array.health_parent_relationship));

            holder.spHealthRelation.setEnabled(true);

        } else if (PolicyFor.toLowerCase().equals("family")) {

            // relationShip = mContext.getResources().getStringArray(R.array.health_family_relationship);
            relationShip = Arrays.asList(mContext.getResources().getStringArray(R.array.health_family_relationship));
            holder.spHealthRelation.setEnabled(true);
        }

        ArrayAdapter<String> relationAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,
                relationShip) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setPadding(8, 12, 4, 12);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        holder.spHealthRelation.setAdapter(relationAdapter);
        holder.spHealthRelation.setTag(R.id.spHealthRelation, entity);
        holder.rbMale.setTag(R.id.llMarried, entity);
        holder.rbFemale.setTag(R.id.llMarried, entity);
        holder.swUnMarried.setTag(R.id.swUnMarried, entity);
        holder.txtMarried.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        if(entity.getMemberDOBTemp() != null)
        {
            holder.etDOB.setText(entity.getMemberDOB().toString());
           // entity.setMemberDOBTemp(entity.getMemberDOB().toString());

        }else {
            holder.etDOB.setText("");
        }


        holder.spHealthRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MemberListEntity memberListEntity = (MemberListEntity) holder.spHealthRelation.getTag(R.id.spHealthRelation);
                memberListEntity.setMemberRelationShip(holder.spHealthRelation.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.swUnMarried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemberListEntity entity = (MemberListEntity) compoundButton.getTag(compoundButton.getId());
                if (b) {
                    //if true maritial status ID =1
                    //false maritial status ID =2
                    holder.txtMarried.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                    holder.txtSingle.setTextColor(mContext.getResources().getColor(R.color.description_text));
                    ((HealthMemberDetailsDialogActivity) mContext).updateMemberList(entity, 1, position);
                } else {
                    ((HealthMemberDetailsDialogActivity) mContext).updateMemberList(entity, 2, position);
                    holder.txtMarried.setTextColor(mContext.getResources().getColor(R.color.description_text));
                    holder.txtSingle.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                }
            }
        });

        holder.rbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    entity.setMemberGender("M");

                } else {
                    entity.setMemberGender("F");
                }
                //update fragment
                ((HealthMemberDetailsDialogActivity) mContext).updateMemberList(entity, 0, position);
            }
        });




        holder.etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.etDOB) {

                    if(entity.getMemberDOB() != null)
                    {
                        try {
                            Date date = simpleDateFormat.parse(entity.getMemberDOB().toString());
                            calendarMain = dateToCalendar(date);
//                            calendarMain.set(Calendar.MONTH ,Calendar.JANUARY);
//                            calendarMain.set(Calendar.DAY_OF_MONTH ,1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    DateTimePicker.showDatePickerDialogHealth(view.getContext(), calendarMain, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                            if (view1.isShown()) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                holder.etDOB.setText(currentDay);
                                entity.setMemberDOB(currentDay);
                                entity.setMemberDOBTemp(currentDay);
                                ((HealthMemberDetailsDialogActivity) mContext).updateMemberList(entity, 0, position);
                            }
                        }
                    });
                }
            }
        });

        if (position == 0) {     //  Self
            //enable llmaried
           // holder.etDOB.setOnClickListener(this);
            holder.llMarried.setVisibility(View.VISIBLE);
            holder.rbMale.setChecked(true);
            holder.spHealthRelation.setSelection(1);
            entity.setMemberGender("M");

            if (PolicyFor.toLowerCase().equals("family")) {
                holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                holder.spHealthRelation.setSelection(1);
            }


        } else if (position == 1) {      // ADULT :
            //hide in all positions
            holder.llMarried.setVisibility(View.GONE);
            holder.rbFemale.setChecked(true);
            entity.setMemberGender("F");

            if (PolicyFor.toLowerCase().equals("family")) {

                if (entity.getAge() >= 18) {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                    holder.spHealthRelation.setSelection(2);
                } else {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(childRelationShip));
                    holder.spHealthRelation.setSelection(1);

                }

            } else {

                holder.spHealthRelation.setSelection(2);

            }

        } else if (position == 2) {
            holder.llMarried.setVisibility(View.GONE);
            holder.rbMale.setChecked(true);
            entity.setMemberGender("M");

            if (PolicyFor.toLowerCase().equals("family")) {

                if (entity.getAge() >= 18) {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                    holder.spHealthRelation.setSelection(2);
                } else {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(childRelationShip));
                    if (adultCount > 1) {
                        holder.spHealthRelation.setSelection(1);
                    } else {
                        holder.spHealthRelation.setSelection(2);
                    }
                }

            } else {
                if (entity.getAge() >= 18)
                    holder.spHealthRelation.setSelection(3);
                else
                    holder.spHealthRelation.setSelection(3);
            }


        } else if (position == 3) {
            holder.llMarried.setVisibility(View.GONE);
            holder.rbFemale.setChecked(true);
            entity.setMemberGender("F");

            if (PolicyFor.toLowerCase().equals("family")) {

                if (entity.getAge() >= 18) {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                    holder.spHealthRelation.setSelection(2);
                } else {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(childRelationShip));
                    if (adultCount > 1) {
                        holder.spHealthRelation.setSelection(2);
                    } else {
                        holder.spHealthRelation.setSelection(3);
                    }
                }

            } else {
                if (entity.getAge() >= 18)
                    holder.spHealthRelation.setSelection(3);
                else
                    holder.spHealthRelation.setSelection(4);
            }


        } else if (position == 4) {
            holder.llMarried.setVisibility(View.GONE);
            holder.rbMale.setChecked(true);
            entity.setMemberGender("M");

            if (PolicyFor.toLowerCase().equals("family")) {

                if (entity.getAge() >= 18) {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                    holder.spHealthRelation.setSelection(2);
                } else {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(childRelationShip));
                    if (adultCount > 1) {
                        holder.spHealthRelation.setSelection(3);
                    } else {
                        holder.spHealthRelation.setSelection(4);
                    }
                }

            } else {
                if (entity.getAge() >= 18)
                    holder.spHealthRelation.setSelection(3);
                else
                    holder.spHealthRelation.setSelection(5);
            }


        } else if (position == 5) {
            holder.llMarried.setVisibility(View.GONE);
            holder.rbMale.setChecked(true);
            entity.setMemberGender("M");
            if (PolicyFor.toLowerCase().equals("family")) {

                if (entity.getAge() >= 18) {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(temrelationShip));
                    holder.spHealthRelation.setSelection(2);
                } else {
                    holder.spHealthRelation.setAdapter(getRelationAdapter(childRelationShip));
                    if (adultCount > 1) {
                        holder.spHealthRelation.setSelection(4);
                    } else {
                        holder.spHealthRelation.setSelection(4);
                    }
                }

            } else {

                if (entity.getAge() >= 18)
                    holder.spHealthRelation.setSelection(3);
                else
                    holder.spHealthRelation.setSelection(6);
            }


        }

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return listMemberList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llMarried;
        Switch swUnMarried;
        RadioButton rbMale, rbFemale;
        Spinner spHealthRelation;
        TextView txtSingle, txtMarried;
        EditText etDOB;

        ViewHolder(View itemView) {
            super(itemView);
            llMarried = (LinearLayout) itemView.findViewById(R.id.llMarried);
            swUnMarried = (Switch) itemView.findViewById(R.id.swUnMarried);
            rbMale = (RadioButton) itemView.findViewById(R.id.rbMale);
            rbFemale = (RadioButton) itemView.findViewById(R.id.rbFemale);
            spHealthRelation = (Spinner) itemView.findViewById(R.id.spHealthRelation);
            txtMarried = (TextView) itemView.findViewById(R.id.txtMarried);
            txtSingle = (TextView) itemView.findViewById(R.id.txtSingle);
            etDOB = (EditText) itemView.findViewById(R.id.etDOB);

        }


    }

    public void setFamilyList() {
        temrelationShip = new ArrayList<String>();
        temrelationShip.add("Relationship");
        temrelationShip.add("Self");
        temrelationShip.add("Spouse");

    }

    public void setChildyList() {
        childRelationShip = new ArrayList<String>();
        childRelationShip.add("Relationship");
        childRelationShip.add("Child1");
        childRelationShip.add("Child2");
        childRelationShip.add("Child3");
        childRelationShip.add("Child4");

    }

    public ArrayAdapter<String> getRelationAdapter(List<String> dataRelationShip) {
        ArrayAdapter<String> relationAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,
                dataRelationShip) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setPadding(8, 12, 4, 12);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        return relationAdapter;
    }


    //Convert Date to Calendar
    public Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }



}