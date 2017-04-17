package textmessage.szabados.alpar.textmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class TextMessageSender extends AppCompatActivity {
    public static final SmsManager SMS_MANAGER = SmsManager.getDefault();
    public static final String DESTINATION_ADDRESS = "6505551212";

    private EditText customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message_sender);

        customText = (EditText) findViewById(R.id.customText);
    }

    public void onClick(View view) {
        sendMessage(view);
        clearMessage();
    }

    private void sendMessage(View view) {
        String message = getMessage(view);

        if (!Objects.equals(message, "")) {
            SMS_MANAGER.sendTextMessage(DESTINATION_ADDRESS, null, message, null, null);
            getToast("Message Sent!").show();
        } else {
            getToast("No Message to Send!").show();
        }
    }

    private Toast getToast(String text) {
        return Toast.makeText(this, text, Toast.LENGTH_SHORT);
    }

    private void clearMessage() {
        customText.setText("");
    }

    private String getMessage(View view) {
        if (view.getId() == R.id.fab) return customText.getText().toString();
        else return ((Button) view).getText().toString();
    }
}
