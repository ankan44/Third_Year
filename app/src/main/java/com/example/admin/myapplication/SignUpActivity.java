package com.example.admin.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 4;
    public String contactID;     // contacts unique ID
    EditText username;
    public String number;
    EditText email;
    Spinner blood_group;
    Spinner sex;
    private Uri uriContact;
    String contactNumber;
    String username1;
    String blood_group1;
    String sex1;
    String email1;


    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Safely_User_Data");
    //private MessageAdapter mMessageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            username = (EditText) findViewById(R.id.username);
            email = (EditText) findViewById(R.id.email);
            //contactNumber = account.userNumber.toString();

            //get the spinner from the xml.
            blood_group = (Spinner) findViewById(R.id.spinner2);
//create a list of items for the spinner.
            String[] items = new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
            blood_group.setAdapter(adapter);
            //get the spinner from the xml.
            sex = (Spinner) findViewById(R.id.spinner1);
//create a list of items for the spinner.
            String[] genders = new String[]{"Male", "Female", "Others"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
//set the spinners adapter to the previously created one.
            sex.setAdapter(adapter2);
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    PhoneNumber phoneNumber = account.getPhoneNumber();
                    contactNumber = phoneNumber.toString();
                }

                @Override
                public void onError(final AccountKitError error) {
                    String toastMessage = error.getErrorType().getMessage();
                    Toast.makeText(SignUpActivity.this, toastMessage, Toast.LENGTH_LONG).show();
                }
            });


        }


    public void onClickSelectContact(View btnSelectContact) {
        if (ActivityCompat.checkSelfPermission(SignUpActivity.this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            return;
        }
        // using native contacts selection
        // Intent.ACTION_PICK = Pick an item from the data, returning what was selected.
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();

            retrieveContactName();
            retrieveContactNumber();

        }
    }

    private void retrieveContactNumber() {

        String contactNumber = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        Log.d(TAG, "Contact Phone Number: " + contactNumber);
        Button button = (Button) findViewById(R.id.button);
        button.setText(contactNumber);
        number = contactNumber;
    }

    private void retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }
        username1 = username.getText().toString();
        blood_group1 = blood_group.getSelectedItem().toString();
        sex1 = sex.getSelectedItem().toString();
        cursor.close();
        email1 = email.getText().toString();
        Log.d(TAG, "Contact Name: " + contactName);


        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                intent.putExtra("name", username.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("blood", blood_group.getSelectedItem().toString());
                intent.putExtra("sex", sex.getSelectedItem().toString());
                intent.putExtra("number", number);
                setData();
                startActivity(intent);
                // TODO: Send data to database.
                DatabaseStuff datainput1 = new DatabaseStuff(username.getText().toString(), email.getText().toString(), sex.getSelectedItem().toString(), blood_group.getSelectedItem().toString(), number, contactNumber);
                mMessageDatabaseReference.push().setValue(datainput1);


            }
        });

    }

    private void setData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",username.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.putString("blood", blood_group.getSelectedItem().toString());
        editor.putString("sex", sex.getSelectedItem().toString());

        editor.commit();

        Toast.makeText(this,"Data was saved successfully",Toast.LENGTH_LONG).show();

    }


}




