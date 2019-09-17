package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePassActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_old_pass)
    EditText editOldPass;
    @BindView(R.id.edit_new_pass)
    EditText editNewPass;
    @BindView(R.id.edit_sure_new_pass)
    EditText editSureNewPass;
    @BindView(R.id.btn_sure_updaate)
    Button btnSureUpdaate;
    @BindView(R.id.btn_pass_back)
    Button btnPassBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.edit_old_pass, R.id.edit_new_pass, R.id.edit_sure_new_pass, R.id.btn_sure_updaate, R.id.btn_pass_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_old_pass:
                break;
            case R.id.edit_new_pass:
                break;
            case R.id.edit_sure_new_pass:
                break;
            case R.id.btn_sure_updaate:
                break;
            case R.id.btn_pass_back:
                break;
        }
    }
}
