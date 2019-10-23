package com.sunshine.first.activity;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepairActivity extends BaseAppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.text_repair_record)
    TextView textRepairRecord;
    @BindView(R.id.relative_change)
    RelativeLayout relativeChange;
    @BindView(R.id.tv_descrip)
    TextView tvDescrip;
    @BindView(R.id.edit_descrip)
    EditText editDescrip;
    @BindView(R.id.textnumber)
    TextView textnumber;
    @BindView(R.id.re_one)
    RelativeLayout reOne;
    @BindView(R.id.icon_add_next)
    ImageView iconAddNext;
    @BindView(R.id.re_add)
    RelativeLayout reAdd;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.rela_time)
    RelativeLayout relaTime;
    @BindView(R.id.view_lianxiren)
    View viewLianxiren;
    @BindView(R.id.re_lianxiren)
    RelativeLayout reLianxiren;
    @BindView(R.id.icon_phone_next)
    ImageView iconPhoneNext;
    @BindView(R.id.re_phone)
    RelativeLayout rePhone;
    @BindView(R.id.btn_baoxiu_submit)
    Button btnBaoxiuSubmit;

    @Override
    protected void initData() {

        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editDescrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDescrip.addTextChangedListener(new TextWatcher());
            }
        });


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repairs;
    }


    class TextWatcher implements android.text.TextWatcher {


        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void afterTextChanged(Editable s) {
            textnumber = (TextView) findViewById(R.id.textnumber);
            int num = s.length();
            num = 200 - num;
            textnumber.setText(num + "/200");

        }
    }

}
