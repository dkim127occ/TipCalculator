package edu.orangecoastcollege.cs273.dkim127.tipcalculator;

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

        // define a listener for the amountEditText
        amountEditText.addTextChangedListener(new TextWatcher(amountTextChangedListener));
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
                double amount = Double.parseDouble(s.toString()) / 100.0;
                currentBill.setAmount(amount);
            }
            catch (NumberFormatException e)
            {
                amountEditText.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // do nothing
        }
    };
}
