package textmessage.szabados.alpar.textmessage;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class TextMessageSender extends AppCompatActivity {
    public static final SmsManager SMS_MANAGER = SmsManager.getDefault();
    private static final int CONTACT_PICKER_RESULT = 1;
    public EditText destinationAddress;
    private EditText customText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message_sender);

        customText = (EditText) findViewById(R.id.customText);
        destinationAddress = (EditText) findViewById(R.id.phone_number);
    }

    public void onClick(View view) {
        sendMessage(view);
        clearMessage();
    }

    private void sendMessage(View view) {
        String message = getMessage(view);
        String destinationAddress = this.destinationAddress.getText().toString();

        if (!Objects.equals(message, "")) {
            SMS_MANAGER.sendTextMessage(destinationAddress, null, message, null, null);
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

    public void pickPhoneNumber(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) pickContactPhoneNumber(data);
    }

    private void pickContactPhoneNumber(Intent data) {
        ContentResolver contentResolver = getContentResolver();
        try {
            Uri uri = data.getData();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);

            assert cursor != null;
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String contactId = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
            Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor phoneCursor = contentResolver.query(phoneUri, null, contactId, new String[]{id}, null);

            assert phoneCursor != null;
            while (phoneCursor.moveToNext()) {
                String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                destinationAddress.setText(phone);
            }
            cursor.close();
            phoneCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
