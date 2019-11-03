package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.activity.PropertyChargesActivity;
import com.sunshine.first.activity.StopCarActivity;
import com.sunshine.first.bean.GrideBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 * @CreateDate: 2019-11-03 15:57
 * @Description:
 * @Version: 1.0
 */
public class GrideAdapter extends RecyclerView.Adapter<GrideAdapter.ViewHolder> {
    private Context context;
    private List<GrideBean> list = new ArrayList<>();

    public GrideAdapter(Context context, List<GrideBean> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GrideAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_gride_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        GrideBean grideBean = list.get(i);
        if (grideBean != null) {
            Glide.with(context).load(list.get(i).icon).into(viewHolder.icon_water);
            viewHolder.text_money.setText(list.get(i).name);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.get(i).name.equals("停车费")){
                        Intent intent=new Intent(context,StopCarActivity.class);
                        context.startActivity(intent);
                    }else if (list.get(i).name.equals("物业费")){
                        Intent intent=new Intent(context,PropertyChargesActivity.class);
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


       ImageView icon_water;
       TextView text_money;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon_water = itemView.findViewById(R.id.icon_water);
            text_money = itemView.findViewById(R.id.tv_water_money);

        }
    }
}
