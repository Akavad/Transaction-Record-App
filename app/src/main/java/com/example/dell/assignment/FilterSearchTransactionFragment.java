package com.example.dell.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 12-02-2018.
 */

public class FilterSearchTransactionFragment extends Fragment {

    View view;
    CheckBox cbparticular,cbdate,cbmonth,cb_debit_amount,cb_credit_amount;
    Spinner spmonth;
    AutoCompleteTextView actvparticular;
    EditText etdate;
    Button btnsearch;
    ListView lv;

    ArrayAdapter arrayAdapter;

    String selection="";
    String[] selectionArgs;

    boolean pastStatus1,pastStatus2;

    TransactionOpenHelper helper;
    List<Transaction> transaction_list;
    ListViewCustomAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragmentthree","oncreate");
        view=inflater.inflate(R.layout.filter_search_transaction,container,false);
        lv= view.findViewById(R.id.listview);
        cbparticular=  view.findViewById(R.id.cbparticular);
        cbdate=view.findViewById(R.id.cbdate);
        cbmonth=view.findViewById(R.id.cbmonth);
        cb_credit_amount=view.findViewById(R.id.cbcredit);
        cb_debit_amount=view.findViewById(R.id.cbdebit);
        btnsearch=  view.findViewById(R.id.btnsearch);
        spmonth=  view.findViewById(R.id.spmonth);

        String[] month={"January","February","March","April","May","June","July","August","September","October","November","December"};
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,month);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmonth.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.i("fragmentthree","in");
            cbdate.setOnCheckedChangeListener(itf1);
            cbmonth.setOnCheckedChangeListener(itf1);
            cb_credit_amount.setOnCheckedChangeListener(itf2);
            cb_debit_amount.setOnCheckedChangeListener(itf2);
            btnsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFilter();
                    loadData();
                }
            });
        }
        else{
            Log.i("fragmentthree","out");
        }
    }

    public void loadData(){
        helper=new TransactionOpenHelper(getContext());
        transaction_list=helper.diaplayTransaction(selection,selectionArgs);
        Log.i("list","after");
        adapter=new ListViewCustomAdapter(getContext(),R.layout.listview_row,transaction_list);
        Log.i("error","afteradapter");
        lv.setAdapter(adapter);
    }

    public String getSpinnerMonth(){

        switch (spmonth.getSelectedItem().toString())
        {
            case "January":return "01";
            case "February":return "02";
            case "March":return "03";
            case "April":return "04";
            case "May":return "05";
            case "June":return "06";
            case "July":return "07";
            case "August":return "08";
            case "September":return "09";
            case "October":return "10";
            case "November":return "11";
            case "December":return "12";
            default:return "0";
        }

    }

    public void setFilter(){
        List<String> strlist=new ArrayList<String>();

        if (cbdate.isChecked())
        {
            selection="DATE=?";
            etdate= (EditText) view.findViewById(R.id.etdate);
            strlist.add(etdate.getText().toString());
        }
        if (cbmonth.isChecked())
        {
            selection="DATE LIKE ?";
            strlist.add("___"+getSpinnerMonth()+"%");
            Log.i("spmonth",getSpinnerMonth());
        }
        if (cbparticular.isChecked())
        {
            if (selection.isEmpty())
            {
                selection="PARTICULAR=?";
            }
            else
            {
                selection+=" and PARTICULAR=?";
            }
            actvparticular= (AutoCompleteTextView) view.findViewById(R.id.actvparticular);
            strlist.add(actvparticular.getText().toString());
        }
        if (cb_credit_amount.isChecked())
        {
            if (selection.isEmpty())
            {
                selection="TYPE=?";
            }
            else
            {
                selection+=" and TYPE=?";
            }
            strlist.add("CREDIT");
        }
        if (cb_debit_amount.isChecked())
        {
            if (selection.isEmpty())
            {
                selection="TYPE=?";
            }
            else
            {
                selection+=" and TYPE=?";
            }
            strlist.add("DEBIT");
        }

        selectionArgs=strlist.toArray(new String[strlist.size()]);
    }

    CompoundButton.OnCheckedChangeListener itf1=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                if (buttonView.getId()==cbdate.getId())
                {
                    pastStatus1=cbmonth.isChecked();
                    cbmonth.setChecked(false);
                }
                else
                {
                    pastStatus1=cbdate.isChecked();
                    cbdate.setChecked(false);
                }
            }
            else
            {
                if (pastStatus1)
                {
                    if (buttonView.getId()==cbdate.getId())
                    {
                        cbmonth.setChecked(true);
                    }
                    else
                    {
                        cbdate.setChecked(true);
                    }
                }
            }
        }
    };

    CompoundButton.OnCheckedChangeListener itf2=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                if (buttonView.getId()==cb_credit_amount.getId())
                {
                    pastStatus2=cb_debit_amount.isChecked();
                    cb_debit_amount.setChecked(false);
                }
                else
                {
                    pastStatus2=cb_credit_amount.isChecked();
                    cb_credit_amount.setChecked(false);
                }
            }
            else
            {
                if (pastStatus2)
                {
                    if (buttonView.getId()==cb_credit_amount.getId())
                    {
                        cb_debit_amount.setChecked(true);
                    }
                    else
                    {
                        cb_credit_amount.setChecked(true);
                    }
                }
            }
        }
    };
}
