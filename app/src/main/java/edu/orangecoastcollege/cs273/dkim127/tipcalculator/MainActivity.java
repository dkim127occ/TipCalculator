package edu.orangecoastcollege.cs273.dkim127.tipcalculator;

import java.text.NumberFormat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipPercentTextView;
    private TextView tipAmountTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    // associate the controller with the needed model
    private RestaurantBill currentBill = new RestaurantBill();

    // formatter for currency and percent
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        tipPercentTextView = (TextView) findViewById(R.id.tipPercentTextView);
        tipAmountTextView = (TextView) findViewById(R.id.tipAmountTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        // instantiate formatter

        // fetch default value for tip percent
        currentBill.setTipPercent(percentSeekBar.getProgress()/100.0);
        tipPercentTextView.setText(percent.format(currentBill.getTipPercent()));

        // define a listener for the amountEditText
        amountEditText.addTextChangedListener(amountTextChangedListener);

        // define a listener for the percentSeekBar
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // update the model
                currentBill.setTipPercent(progress/100.0);

                // update the percent view
                tipPercentTextView.setText(percent.format(currentBill.getTipPercent()));

                // update the views
                updateViews();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });
    }


    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // try to get the amount from amountEditText
            try
            {
                if (s.length() > 0) {
                    double amount = Double.parseDouble(s.toString()) / 100.0;
                    currentBill.setAmount(amount);
                }
                else
                {
                    currentBill.setAmount(0);
                }
            }
            catch (NumberFormatException e)
            {
                amountEditText.setText("");
                amountTextView.setText("");
                currentBill.setAmount(0);
            }
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // do nothing
        }
    };

    private void updateViews()
    {
        // set the bill amount
        amountTextView.setText(currency.format(currentBill.getAmount()));

        // set the tip amount
        tipAmountTextView.setText(currency.format(currentBill.getTipAmount()));

        // set the total amount
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));
    }


}
