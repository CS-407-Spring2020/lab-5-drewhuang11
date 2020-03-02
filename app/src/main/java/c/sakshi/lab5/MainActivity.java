package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.*;

import android.widget.*;

import android.content.*;

public class MainActivity extends AppCompatActivity {

    static String usernameKey = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey, "").equals("")) {
            goToActivity2(sharedPreferences.getString(usernameKey, ""));
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    /*public void onButtonClick(View view) {
        //1. Get username and password via EditText view
        EditText field1 = (EditText) findViewById(R.id.myTextField2);
        EditText field2 = (EditText) findViewById(R.id.myTextField);
        String username = field1.getText().toString();
        String password = field2.getText().toString();
        //2. Add username to SharedPreferences object
        sharedPreferences.edit().putString("username", username).apply();
        sharedPreferences.edit().putString("password", password).apply();
    }*/

    public void clickFunction(View view) {
        /*
        //Log.i("Info", "Button pressed");
        EditText myTextField = (EditText) findViewById(R.id.myTextField2);
        String str = myTextField.getText().toString();

        //Toast.makeText(MainActivity.this, myTextField.getText().toString(), Toast.LENGTH_LONG).show();

        goToActivity2(str);
        */
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        //1. Get username and password via EditText view
        EditText field1 = (EditText) findViewById(R.id.myTextField2);
        EditText field2 = (EditText) findViewById(R.id.myTextField);
        String username = field1.getText().toString();
        String password = field2.getText().toString();
        //2. Add username to SharedPreferences object
        sharedPreferences.edit().putString("username", username).apply();
        sharedPreferences.edit().putString("password", password).apply();
        //3. Start second activity
        goToActivity2(username);
    }

    public void goToActivity2(String s) {
        Intent intent = new Intent (this, Main2Activity.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }
}
