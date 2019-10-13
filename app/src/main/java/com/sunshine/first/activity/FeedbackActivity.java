package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseAppCompatActivity{

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.icon_add)
    ImageView iconAdd;
    @BindView(R.id.text_feedback_number)
    TextView textFeedbackNumber;
    @BindView(R.id.btn_feedback_submit)
    Button btnFeedbackSubmit;
    private EditText editFeedback;

    @Override
    protected void initData() {
        editFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFeedback.addTextChangedListener(new TextWatcher());
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        editFeedback = (EditText) get(R.id.edit_feedback);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advice;
    }

   /* @OnClick({R.id.icon_back, R.id.edit_feedback, R.id.icon_add, R.id.btn_feedback_submit, R.id.text_feedback_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_feedback:
                break;
            case R.id.icon_add:
                break;
            case R.id.btn_feedback_submit:
                break;
            case R.id.text_feedback_number:
                break;
        }
    }*/


    class TextWatcher implements android.text.TextWatcher {


        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void afterTextChanged(Editable s) {
            textFeedbackNumber = (TextView) findViewById(R.id.text_feedback_number);
            int num = s.length();
            num = 200 - num;
            textFeedbackNumber.setText(num + "/200");

        }
    }
}
