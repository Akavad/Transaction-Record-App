package com.example.dell.assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 09-02-2018.
 */

public class MonthlyTransactionFragment extends Fragment {

    List<Transaction> list;
    ListViewCustomAdapter adapter;
    TransactionOpenHelper helper;
    View view;
    TextView tvnetamount;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Fragmenttwo","oncreate");
        view=inflater.inflate(R.layout.daily_monthlytransaction,container,false);
        lv=  view.findViewById(R.id.listview);
        tvnetamount=  view.findViewById(R.id.tvnetamount);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.i("fragmenttwo","in");
            loadData();
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    //Bundle bundle=(Bundle)parent.getAdapter().getItem(position);
                    //Log.i("delete",String.valueOf(bundle.getInt("ID")));
                    int transaction_id=list.get(position).getId();
                    showDeleteDialog(transaction_id);
                    return true;
                }
            });
            tvnetamount.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    NetAmount amount=new NetAmount(list);
                    int credit=amount.getCredit();
                    int debit=amount.getDebit();
                    tvnetamount.setText(credit+" credit"+"     "+debit+" debit");
                    return false;
                }
            });
        }
        else {
            Log.i("fragmenttwo","out");
        }
    }

    public void loadData(){
        Log.i("ankit","yes");
        helper=new TransactionOpenHelper(getContext());
        String selection="DATE LIKE ?";
        String[] selectionArgs={"___"+new Date().getDate().substring(3,10)+"%"};
        Log.i("month",selectionArgs[0]);
        list=helper.diaplayTransaction(selection,selectionArgs);
        //String[] arr={"ironman","spiderman","captain america","thor","roger","natasha","hulk","doctor strange","wolverine"};
        adapter=new ListViewCustomAdapter(getContext(),R.layout.listview_row,list);
        lv.setAdapter(adapter);

    }

    public void showDeleteDialog(final int transaction_id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete this transaction ?");
        builder.setCancelable(false);
        builder.setTitle("Delete Transaction");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                helper.deleteTransaction(transaction_id);
                loadData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
