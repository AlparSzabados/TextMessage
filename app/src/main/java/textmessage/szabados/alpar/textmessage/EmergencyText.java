package textmessage.szabados.alpar.textmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmergencyText extends AppCompatActivity {
    public static final SmsManager SMS_MANAGER = SmsManager.getDefault();
    public static final String DESTINATION_ADDRESS = "";

    private EditText customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_text);

        customText = (EditText) findViewById(R.id.customText);
    }

    public void onSend(View view) {
        sendMessage(view);
        clearMessage();
    }

    private void sendMessage(View view) {
        String message = getMessage(view);
        SMS_MANAGER.sendTextMessage(DESTINATION_ADDRESS, null, message, null, null);
    }

    private void clearMessage() {
        customText.setText("");
    }

    private String getMessage(View view) {
        if (view.getId() == R.id.fab) return customText.getText().toString();
        else {
            Button button = (Button) view;
            return button.getText().toString();
        }
    }
}
