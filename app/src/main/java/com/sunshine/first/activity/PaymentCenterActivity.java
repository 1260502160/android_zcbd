package com.sunshine.first.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentCenterActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_pay_record)
    TextView textPayRecord;
    @BindView(R.id.recycle_choose_home)
    RecyclerView recycleChooseHome;
    @BindView(R.id.gridview)
    GridView gridView;
    private ArrayList<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private GridView gridview;



    protected void initData() {
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        gridview = findViewById(R.id.gridview);

        String[] from={"img","text"};

        int[] to={R.id.img,R.id.text};

        adapter=new SimpleAdapter(this, dataList, R.layout.gridview_item, from, to);

        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(PaymentCenterActivity.this);
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });

        //图标
        int icno[] = { R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
        //图标下的文字
        String name[]={"水费","电费","燃气费","固话","宽带","有线电视","停车费","物业费","暖气费"};
        dataList = new ArrayList<>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_center;
    }


}
