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

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_feedback)
    EditText editFeedback;
    @BindView(R.id.icon_add)
    ImageView iconAdd;
    @BindView(R.id.btn_feedback_submit)
    Button btnFeedbackSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.edit_feedback, R.id.icon_add, R.id.btn_feedback_submit})
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
        }
    }
}
