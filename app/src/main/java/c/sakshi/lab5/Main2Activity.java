package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import android.content.*;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item3:
                Intent i1 = new Intent(this,ThirdActivity.class);
                this.startActivity(i1);
                return true;
            case R.id.item2:
                Intent i = new Intent(this,MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //1. display welcome message
        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        //textView2.setText("Hello " + str);
        customText(str);

        //2. get SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //3. Initiate the "notes" class variable using readNotes method implemented in DBHelper class. Use the username you
        //   got from SharedPreferences as a parameter to readNotes method.

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(str);
        //4. Create an ArrayList<String> object by iterating over notes object.

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //5. Use ListView view to display notes on screen.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        //6. Add onItemClickListener for ListView item, a note in our case.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                //Add the position of the item that was clicked on as "noteid"
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
    }

    private void customText(String s) {
        if(s.equals("Daisy") || s.equals("daisy")) {
            textView2.setText(s + " is kinda bad and should go back to studying :)");
        } else {
            textView2.setText("Welcome " + s + "!");
        }
    }
}
