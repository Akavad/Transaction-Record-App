package com.example.dell.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dell on 04-02-2018.
 */

public class TransactionEntryFragment extends Fragment {

    Button btndebit, btncredit;
    EditText etamount, etparticular;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Fragmentzero","oncreate");
        view=inflater.inflate(R.layout.transaction_entry,container,false);
        etamount=view.findViewById(R.id.etamount);
        etparticular = view.findViewById(R.id.etparticular);
        btndebit =  view.findViewById(R.id.btndebit);
        btncredit = view.findViewById(R.id.btncredit);
        btndebit.setOnClickListener(itf);
        btncredit.setOnClickListener(itf);
        return view;
    }

    View.OnClickListener itf= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (etamount.getText().toString().isEmpty()&& etparticular.getText().toString().isEmpty())
            {
                etamount.setError("Please enter amount");
                etparticular.setError("please enter particular");
            }
            else if (etamount.getText().toString().isEmpty())
            {
                etamount.setError("Please enter amount");
            }
            else if (etparticular.getText().toString().isEmpty())
            {
                etparticular.setError("please enter particular");
            }
            else
            {
                Button click=(Button)v;
                Transaction transaction=new Transaction();
                transaction.setDate(new Date().getDate());
                if (click.getId()==R.id.btncredit)
                {
                    transaction.setType("CREDIT");
                }
                else
                {
                    transaction.setType("DEBIT");
                }
                transaction.setAmount(Integer.parseInt(etamount.getText().toString()));
                transaction.setParticular(etparticular.getText().toString());
                TransactionOpenHelper helper=new TransactionOpenHelper(getContext());
                helper.insertTransaction(transaction);
                Toast.makeText(getContext(),"record added",Toast.LENGTH_LONG).show();
            }

        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.i("fragmentzero","in");
        }
        else {
            Log.i("fragmentzero","out");
        }
    }
}
