package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //LottieAnimationView lottieAnimationView;

    Button createnotes;

   RecyclerView recyclerView;
    TextView screen2,screen1;

    FloatingActionButton Addnotes;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // lottieAnimationView= findViewById(R.id.animation_view);

        createnotes = findViewById(R.id.createnotes);
        screen2 = findViewById(R.id.screen2);

        screen1 = findViewById(R.id.screen1);


        // lottieAnimationView.setAnimation(R.raw.notes_anmi);
        //lottieAnimationView.playAnimation();
        //  lottieAnimationView.loop(true);

        Addnotes = findViewById(R.id.Addnotes);

        LocalDB localDB = LocalDB.getDB(this);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Show();


        createnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addnotes.performClick();

            }
        });

        Addnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.notes_dialog);

                Button notesave_btn = dialog.findViewById(R.id.notesave_btn);


                EditText note_content = dialog.findViewById(R.id.note_content);
                EditText note_title = dialog.findViewById(R.id.note_title);


                notesave_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                        String title = note_title.getText().toString();

                        String content = note_content.getText().toString();

                        if (!content.equals("")) {

                            localDB.notesDao().addNote(new Notes(title, content));

                            Show();


                        } else {

                            Toast.makeText(MainActivity.this, "content is mandatory", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                dialog.show();

            }
        });

    }

    public void Show() {
        LocalDB localDB = LocalDB.getDB(this);
        ArrayList<Notes> arrnotes = (ArrayList<Notes>) localDB.notesDao().getallnotes();

        if(arrnotes.size()>0){

            vis();

            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(new NotesAdapter(MainActivity.this,arrnotes,localDB) );

        }
        else {
            screen1.setVisibility(View.VISIBLE);

            screen2.setVisibility(View.VISIBLE);

           // lottieAnimationView.setAnimation(View.VISIBLE);

            createnotes.setVisibility(View.VISIBLE);

            recyclerView.setVisibility(View.GONE);

        }

    }

    private void vis() {
         screen1.setVisibility(View.GONE);

        screen2.setVisibility(View.GONE);

       // lottieAnimationView.setAnimation(View.GONE);

        createnotes.setVisibility(View.GONE);



    }

    private void firstscrren() {

        createnotes=findViewById(R.id.createnotes);


        screen2= findViewById(R.id.screen2);

        screen1= findViewById(R.id.screen1);



    }
}