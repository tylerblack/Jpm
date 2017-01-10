package com.example.tyler.jpm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Tyler on 1/9/2017.
 */
public class DetailActivity extends AppCompatActivity {

    TextView mState;
    TextView mLocType;
    TextView mLabel;
    TextView mAddress;
    TextView mCity;
    TextView mZip;
    TextView mBank;
    TextView mDistance;
    TextView mPhone;

    AtmJson mAtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        mAtm = (AtmJson)getIntent().getSerializableExtra(AtmJson.class.getSimpleName());
        mState = (TextView)findViewById(R.id.state);
        mLocType = (TextView) findViewById(R.id.locType);
        mLabel = (TextView) findViewById(R.id.label);
        mAddress = (TextView) findViewById(R.id.address);
        mCity = (TextView) findViewById(R.id.city);
        mZip = (TextView) findViewById(R.id.zip);
        mBank = (TextView) findViewById(R.id.bank);
        mDistance = (TextView) findViewById(R.id.distance);
        mPhone = (TextView) findViewById(R.id.phone);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (mAtm == null){
            mState.setText("Sorry, no detailed information is available at this time");
        }
        else {
            mState.setText(mAtm.state);
            mLocType.setText(mAtm.locType);
            mLabel.setText(mAtm.label);
            mAddress.setText(mAtm.address);
            mCity.setText((mAtm.city));
            mZip.setText(mAtm.zip);
            mBank.setText(mAtm.bank);
            mDistance.setText(String.valueOf(mAtm.distance));
            mPhone.setText(mAtm.phone);
        }
    }
}
