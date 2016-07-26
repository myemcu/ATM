package com.myemcu.atm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */

// 自定义适配器(类)
public class TransactionAdapter
             extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transcation> trans; // 定义Transcation交易集合

    public TransactionAdapter(List<Transcation> trans) { //建构子,传入参数为:Transcation交易集合
        this.trans = trans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_trans, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            Log.d("TRANS", position+"");

            Transcation tran = trans.get(position);

            holder.accountTextView.setText(tran.getAccount()+"");
            holder.dateTextView.setText(tran.getDate()+"");
            holder.amountTextView.setText(tran.getAmount()+"");
            holder.typeTextView.setText(tran.getType()+"");
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    // 设计ViewHolder内部类
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView accountTextView;     // 帐户
        private final TextView dateTextView;        // 日期
        private final TextView amountTextView;      // 金额
        private final TextView typeTextView;        // 类型

        public ViewHolder(View itemView) {
            super(itemView);

            accountTextView = (TextView) itemView.findViewById(R.id.col_account);
            dateTextView    = (TextView) itemView.findViewById(R.id.col_date);;
            amountTextView  = (TextView) itemView.findViewById(R.id.col_amount);;
            typeTextView    = (TextView) itemView.findViewById(R.id.col_type);;
        }
    }
}
