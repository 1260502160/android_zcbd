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

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_feedback)
    EditText editFeedback;
    @BindView(R.id.icon_add)
    ImageView iconAdd;
    @BindView(R.id.text_feedback_number)
    TextView textFeedbackNumber;
    @BindView(R.id.btn_feedback_submit)
    Button btnFeedbackSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.edit_feedback, R.id.icon_add, R.id.btn_feedback_submit, R.id.text_feedback_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_feedback:
                editFeedback.addTextChangedListener(new TextWatcher());
                break;
            case R.id.icon_add:
                break;
            case R.id.btn_feedback_submit:
                break;
            case R.id.text_feedback_number:
                break;
        }
    }


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
