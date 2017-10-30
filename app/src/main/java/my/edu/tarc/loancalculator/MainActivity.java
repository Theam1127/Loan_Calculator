package my.edu.tarc.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextVP, editTextD, editTextIR, editTextRepayment, editTextSalary;
    private TextView textViewTI, textViewLoan, textViewMP, textViewApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v){
        editTextVP = (EditText)findViewById(R.id.editTextVehiclePrice);
        editTextD = (EditText)findViewById(R.id.editTextDownpayment);
        editTextIR = (EditText)findViewById(R.id.editTextInterestRate);
        editTextRepayment = (EditText)findViewById(R.id.editTextRepayment);
        editTextSalary = (EditText)findViewById(R.id.editTextSalary);

        textViewTI = (TextView)findViewById(R.id.textViewInterest);
        textViewLoan = (TextView)findViewById(R.id.textViewLoan);
        textViewMP = (TextView)findViewById(R.id.textViewMonthPayment);
        textViewApp = (TextView)findViewById(R.id.textViewApplication);
        try {
            double vp, dp, ir, mp, salary, total_interest, total_loan;
            int repayment;
            vp = Double.parseDouble(editTextVP.getText().toString());
            dp = Double.parseDouble(editTextD.getText().toString());
            ir = Double.parseDouble(editTextIR.getText().toString()) / 100;
            salary = Double.parseDouble(editTextSalary.getText().toString());

            if (ir > 1.0)
                Toast.makeText(getApplication().getBaseContext(), "Interest Rate should not more than 100!", Toast.LENGTH_SHORT).show();
            else if (vp < dp)
                Toast.makeText(getApplication().getBaseContext(), "Vehicle price should be larger than downpayment!", Toast.LENGTH_SHORT).show();
            else {
                repayment = Integer.parseInt(editTextRepayment.getText().toString());
                total_interest = (vp - dp) * ir * (repayment / 12);
                total_loan = (vp - dp) + total_interest;
                mp = total_loan / repayment;

                textViewTI.setText(getResources().getString(R.string.total_interest) + " RM" + String.format("%.2f",total_interest));
                textViewLoan.setText(getResources().getString(R.string.total_loan) + " RM" + String.format("%.2f",total_loan));
                textViewMP.setText(getResources().getString(R.string.month_payment) + " RM" + String.format("%.2f",mp));

                if (mp > salary * .3) {
                    textViewApp.setText("Loan Application is Rejected!");
                    textViewApp.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                else {
                    textViewApp.setText("Loan Application is Accepted");
                    textViewApp.setTextColor(getResources().getColor(R.color.holoGreen));
                }
            }
        }
        catch(Exception e){
            Toast.makeText(getApplication().getBaseContext(),"Enter a value!",Toast.LENGTH_SHORT).show();
        }

    }
    public void reset(View v){
        editTextVP = (EditText)findViewById(R.id.editTextVehiclePrice);
        editTextD = (EditText)findViewById(R.id.editTextDownpayment);
        editTextIR = (EditText)findViewById(R.id.editTextInterestRate);
        editTextRepayment = (EditText)findViewById(R.id.editTextRepayment);
        editTextSalary = (EditText)findViewById(R.id.editTextSalary);

        textViewTI = (TextView)findViewById(R.id.textViewInterest);
        textViewLoan = (TextView)findViewById(R.id.textViewLoan);
        textViewMP = (TextView)findViewById(R.id.textViewMonthPayment);
        textViewApp = (TextView)findViewById(R.id.textViewApplication);

        editTextVP.setText("");
        editTextD.setText("");
        editTextIR.setText("");
        editTextRepayment.setText("");
        editTextSalary.setText("");

        textViewTI.setText("");
        textViewLoan.setText("");
        textViewMP.setText("");
        textViewApp.setText("");
    }
}
