package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
    @BindView(R.id.text_feedback_number)
    TextView textFeedbackNumber;
    @BindView(R.id.btn_feedback_submit)
    Button btnFeedbackSubmit;
    @BindView(R.id.edit_feedback)
    EditText editFeedback;

    @Override
    protected void initData() {
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advice;
    }


    class TextWatcher implements android.text.TextWatcher {


        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void afterTextChanged(Editable s) {
            String content = editFeedback.getText().toString();
          /*  int num = s.length();
            num = 200 - num;*/
            textFeedbackNumber.setText(content.length() + "/200");

        }
    }
}
