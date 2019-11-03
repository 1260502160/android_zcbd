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
import com.sunshine.first.activity.GoodsDetailActivity;
import com.sunshine.first.activity.WaveOrderDetailsActivity;
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
    public void onBindViewHolder(@NonNull final AllIndentAdapter.ViewHolder holder, final int i) {
        final AllIndentBean.DataBean.ListBean listBean = list.get(i);
        if (listBean != null) {
            holder.tv_indent_number.setText(listBean.getOrder_no() + "");//订单编号
            Glide.with(context).load(listBean.getGoods_image()).into(holder.iv_image_indent);//商品图片
            holder.tv_descript_indent.setText(listBean.getGoods_describe() + "");//商品描述
            holder.tv_price_indent.setText(listBean.getMoney() + "");//商品单价
            holder.tv_count_indent.setText(listBean.getGoods_num() + "");//商品数量
            holder.tv_money_left_indent.setText(listBean.getOrder_money() + "");//商品总价

            final String order_status = listBean.getOrder_status();
            if ("已关闭".equals(order_status)) {
                holder.tv_time_close_indent.setText("已关闭");//关闭时间

                holder.btn_all_indent_pay.setText("再次购买");

            } else if ("待付款".equals(order_status)) {
                holder.tv_time_close_indent.setText(listBean.getPay_end_time() + "");//关闭时间
                holder.btn_all_indent_pay.setText("立即支付");

            } else if ("退款".equals(order_status)) {
                holder.tv_time_close_indent.setText("已退款");//关闭时间
                holder.btn_lookdeatil_indent.setText("查看详情");

                holder.btn_lookdeatil_indent.setVisibility(View.VISIBLE);
                holder.btn_all_indent_pay.setVisibility(View.GONE);

            } else {//已付款
                holder.tv_time_close_indent.setText("已付款");//关闭时间

                holder.btn_all_indent_pay.setText("再次购买");

            }

            holder.btn_lookdeatil_indent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//左边按钮点击 查看详情

                    Intent intent = new Intent(context, GoodsDetailActivity.class);
                    intent.putExtra("id", listBean.getGoods_id());
                    context.startActivity(intent);
                }
            });

            holder.btn_all_indent_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//右边按钮点击
                    Intent intent;
                    if ("已关闭".equals(order_status)) {
                        intent = new Intent(context, GoodsDetailActivity.class);
                        intent.putExtra("id", listBean.getGoods_id());
                        context.startActivity(intent);
                    } else if ("待付款".equals(order_status)) {
                        WaveOrderDetailsActivity.startWaveOrderDetailsActivity(context, listBean.getId());

                    } else if ("退款".equals(order_status)) {

                    } else {//已付款
                        intent = new Intent(context, GoodsDetailActivity.class);
                        intent.putExtra("id", listBean.getGoods_id());
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_indent_number)
        TextView tv_indent_number;//订单编号
        @BindView(R.id.tv_time_close_indent)
        TextView tv_time_close_indent;//时间
        @BindView(R.id.iv_image_indent)
        ImageView iv_image_indent;//商品图片
        @BindView(R.id.tv_descript_indent)
        TextView tv_descript_indent;//商品描述
        @BindView(R.id.tv_price_indent)
        TextView tv_price_indent;//商品单价
        @BindView(R.id.tv_count_indent)
        TextView tv_count_indent;//商品数量
        @BindView(R.id.tv_money_left_indent)
        TextView tv_money_left_indent;//商品总价
        @BindView(R.id.btn_lookdeatil_indent)
        Button btn_lookdeatil_indent;//左边按钮
        @BindView(R.id.btn_all_indent_pay)
        Button btn_all_indent_pay;//右边按钮

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
