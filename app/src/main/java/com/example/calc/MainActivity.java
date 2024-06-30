package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText operand1EditText;
    private EditText operand2EditText;
    private Spinner operatorSpinner;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        operand1EditText = findViewById(R.id.operand1EditText);
        operand2EditText = findViewById(R.id.operand2EditText);
        operatorSpinner = findViewById(R.id.operatorSpinner);
        resultTextView = findViewById(R.id.textResult);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operators_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorSpinner.setAdapter(adapter);

    }
    public void calculate(View view) {
        try {
            int operand1 = Integer.parseInt(operand1EditText.getText().toString());
            int operand2 = Integer.parseInt(operand2EditText.getText().toString());
            String operator = operatorSpinner.getSelectedItem().toString();

            int result = 0;
            switch (operator) {
                case "חיבור":
                    result = operand1 + operand2;
                    break;
                case "חיסור":
                    result = operand1 - operand2;
                    break;
                case "כפל":
                    result = operand1 * operand2;
                    break;
                case "חילוק":
                    if (operand2 == 0) {
                        Toast.makeText(this, "לא ניתן לבצע חילוק ב-0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = operand1 / operand2;
                    break;
                case "חזקה":
                    result = (int) Math.pow(operand1, operand2);
                    break;
                default:
                    Toast.makeText(this, "פעולה לא חוקית", Toast.LENGTH_SHORT).show();
                    return;
            }

            resultTextView.setText(String.valueOf(result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "נא להזין מספרים שלמים בלבד", Toast.LENGTH_SHORT).show();
        }
    }
}