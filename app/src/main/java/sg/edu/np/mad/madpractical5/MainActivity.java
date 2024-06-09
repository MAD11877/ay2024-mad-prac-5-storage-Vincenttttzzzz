package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        //User user = new User("John Doe", "MAD Developer", 1, false);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("name");
        tvName.setText(name);
        tvDescription.setText(description);

        User user = dbHandler.getUser(name);

        if (user.getFollowed()){
            btnFollow.setText("Unfollow");
        }
        else {
            btnFollow.setText("Follow");
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.followed) {
                    user.followed = true;
                    btnFollow.setText("Followed");
                    dbHandler.updateUser(user);
                    Toast.makeText(MainActivity.this,"Unfollowed", Toast.LENGTH_SHORT).show();
                } else {
                    user.followed = false;
                    btnFollow.setText("Follow");
                    dbHandler.updateUser(user);
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        });




//
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Do something when login is pressed.
//                EditText etUsername = findViewById(R.id.etUsername);
//                EditText etPassword = findViewById(R.id.etPassword);
//                String username = etUsername.getText().toString();
//                String password = etPassword.getText().toString();
//                User user = new User(1, "", "");
//                user = dbHandler.getUser(username);
//
//                if (username.equals(user.getName()) && password.equals(user.getPassword())){
//                    Toast.makeText(getApplicationContext(), "User Authenticated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
//                    intent.putExtra("name", user.getName());
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Invalid User!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Do something when Register is pressed.
//                EditText etUsername = findViewById(R.id.etUsername);
//                EditText etPassword = findViewById(R.id.etPassword);
//                String username = etUsername.getText().toString();
//                String password = etPassword.getText().toString();
//
//                User user = new User(1, "", "");
//                user = dbHandler.getUser(username);
//                if (username.equals(user.getName())){
//                    user.setPassword(password);
//                    dbHandler.updateUser(user);
//                    Toast.makeText(getApplicationContext(), "Update an existing user.", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    user.setName(username);
//                    user.setPassword(password);
//                    dbHandler.addUser(user);
//                    Toast.makeText(getApplicationContext(), "Create a new user.", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }
}