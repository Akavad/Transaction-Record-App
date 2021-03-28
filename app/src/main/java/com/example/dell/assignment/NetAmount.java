package com.example.dell.assignment;

import android.os.Bundle;

import java.util.List;

/**
 * Created by Dell on 12-02-2018.
 */

public class NetAmount {

    int debit,credit;
    int net_amount;
    List<Transaction> list;

    public NetAmount(List<Transaction> list) {
        this.list = list;
        debit=credit=net_amount=0;
    }

    public int getDebit() {
        int i=0;
        while (i<list.size()){
            if (list.get(i).getType().equals("DEBIT")){
                debit+=list.get(i).getAmount();
            }
            i++;
        }
        return debit;
    }

    public int getCredit() {
        int i=0;
        while (i<list.size()){
            if (list.get(i).getType().equals("CREDIT")){
                credit+=list.get(i).getAmount();
            }
            i++;
        }
        return credit;
    }

    public int getNetAmount() {
        int i=0;
        while (i<list.size()){
            net_amount+=list.get(i).getAmount();
            i++;
        }
        return net_amount;
    }
}
