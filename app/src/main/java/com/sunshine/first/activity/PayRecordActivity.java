package com.sunshine.first.activity;


import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.PagRecordBean;

import java.util.List;

import butterknife.BindView;


/**
 * 纳费记录页面
 */
public class PayRecordActivity extends BaseAppCompatActivity {
    @BindView(R.id.elv_pay_record)
    ExpandableListView elv_pay_record;
    private List<PagRecordBean.DataBean.ListBeanX> listBeanXList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_record;
    }

    @Override
    protected void initView() {
        setDefaultTitle("缴费记录");
        elv_pay_record.setGroupIndicator(null);
    }

    @Override
    protected void initData() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("page", page + "");
        hashMap.put("perpage", perpage + "");
        net(true, false).post(1, Api.GetFinanceList_URL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            PagRecordBean pagRecordBean = gson.fromJson(data, PagRecordBean.class);
            if (pagRecordBean != null && pagRecordBean.getData() != null) {
                listBeanXList = pagRecordBean.getData().getList();
                elv_pay_record.setAdapter(new PayRecordAdapter());
            }
        }
    }

    class PayRecordAdapter implements ExpandableListAdapter {

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return listBeanXList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return listBeanXList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return listBeanXList.get(groupPosition).getList().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            PayGroupHolder payGroupHolder;
            if (convertView == null) {
                payGroupHolder = new PayGroupHolder();
                convertView = LayoutInflater.from(PayRecordActivity.this).inflate(R.layout.layout_pay_record_group, parent, false);
                payGroupHolder.tv_pay_record_group = convertView.findViewById(R.id.tv_pay_record_group);
                convertView.setTag(payGroupHolder);
            } else {
                payGroupHolder = (PayGroupHolder) convertView.getTag();
            }
            payGroupHolder.tv_pay_record_group.setText(listBeanXList.get(groupPosition).getDay() + "月");
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            PayChildHolder payChildHolder;
            if (convertView == null) {
                payChildHolder = new PayChildHolder();
                convertView = LayoutInflater.from(PayRecordActivity.this).inflate(R.layout.layout_pay_record_child, parent, false);
                payChildHolder.iv_pay_record_child = convertView.findViewById(R.id.iv_pay_record_child);
                payChildHolder.tv_money_pay_record_child = convertView.findViewById(R.id.tv_money_pay_record_child);
                payChildHolder.tv_title_pay_record_child = convertView.findViewById(R.id.tv_title_pay_record_child);
                payChildHolder.tv_time_pay_record_child = convertView.findViewById(R.id.tv_time_pay_record_child);
                convertView.setTag(payChildHolder);
            } else {
                payChildHolder = (PayChildHolder) convertView.getTag();
            }
            final PagRecordBean.DataBean.ListBeanX.ListBean listBean = listBeanXList.get(groupPosition).getList().get(childPosition);
            if (listBean != null) {
                payChildHolder.tv_money_pay_record_child.setText(listBean.getMoney());
//            payChildHolder.tv_title_pay_record_child.setText(listBean.getCreated_at());
                payChildHolder.tv_time_pay_record_child.setText(listBean.getCreated_at());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PayRecordActivity.this, PayDeatilActivity.class);
                        intent.putExtra("id", listBean.getId());
                        startActivity(intent);
                    }
                });
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }


    class PayGroupHolder {
        TextView tv_pay_record_group;
    }

    class PayChildHolder {
        ImageView iv_pay_record_child;
        TextView tv_title_pay_record_child;
        TextView tv_time_pay_record_child;
        TextView tv_money_pay_record_child;
    }
}
