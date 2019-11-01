package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.activity.IntentDetailActivity;
import com.sunshine.first.activity.OrderCloseActivity;
import com.sunshine.first.activity.OrderPaidActivity;
import com.sunshine.first.activity.OrderRefundActivity;
import com.sunshine.first.bean.AllIndentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全部订单
 */
public class AllIndentAdapter extends RecyclerView.Adapter<AllIndentAdapter.ViewHolder> {
    private Context context;
    private List<AllIndentBean.DataBean.ListBean> list = new ArrayList<>();

    public AllIndentAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<AllIndentBean.DataBean.ListBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllIndentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AllIndentAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_all_indent, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllIndentAdapter.ViewHolder viewHolder, int i) {
        final AllIndentBean.DataBean.ListBean listBean = list.get(i);
        if (listBean != null) {
            viewHolder.tvDescript.setText(listBean.getGoods_describe());
            viewHolder.tvCount.setText(listBean.getGoods_num() + "");
            viewHolder.tvPrice.setText(listBean.getMoney() + "");
            Glide.with(context).load(listBean.getGoods_image()).into(viewHolder.iconAllIndent);
            viewHolder.btnLookdeatilndent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String order_status = listBean.getOrder_status();
                    Intent intent;
                    if ("已关闭".equals(order_status)) {
                        intent = new Intent(context, OrderCloseActivity.class);
                    } else if ("待付款".equals(order_status)) {
                        intent = new Intent(context, IntentDetailActivity.class);
                    } else if ("退款".equals(order_status)) {
                        intent = new Intent(context, OrderRefundActivity.class);
                    } else {//已付款
                        intent = new Intent(context, OrderPaidActivity.class);
                    }
                    intent.putExtra("id", listBean.getId());
                    context.startActivity(intent);
                }
            });
            viewHolder.btn_all_indent_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.startActivity(new Intent(context,));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_descript)
        TextView tvDescript;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.icon_all_indent)
        ImageView iconAllIndent;
        @BindView(R.id.btn_lookdeatil_indent)
        Button btnLookdeatilndent;
        @BindView(R.id.btn_all_indent_pay)
        Button btn_all_indent_pay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
