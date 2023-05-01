package com.example.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {


    @NonNull


    Context context;
    ArrayList<Notes> arrnotes = new ArrayList<>();

    LocalDB localDB;
    public NotesAdapter(Context context, ArrayList<Notes> arrnotes,LocalDB localDB){
        this.context=context;
        this.arrnotes=arrnotes;
        this.localDB=localDB;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.linear_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.note_Re.setText(arrnotes.get(position).getNote_title());
        holder.cardView.setCardBackgroundColor(getRandomColorCode());
        holder.content_Re.setText(arrnotes.get(position).getNote_content());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.entry_dialog);
                Button notesave_btn = dialog.findViewById(R.id.notesave_btn);

                EditText note_title = dialog.findViewById(R.id.note_title);


                EditText note_content = dialog.findViewById(R.id.note_content);


                note_title.setText(arrnotes.get(position).getNote_title());

                note_content.setText(arrnotes.get(position).getNote_content());

                dialog.show();

                notesave_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String title = note_title.getText().toString();

                        String content = note_content.getText().toString();

                        if (!title.equals("")) {

                            localDB.notesDao().UpdateNotes(new Notes(arrnotes.get(position).getId(),title,content));





                        } else {

                            Toast.makeText(context, "content is mandatory", Toast.LENGTH_SHORT).show();

                        }
                        ((MainActivity)context).Show();

                        dialog.dismiss();


                    }
                });



            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setIcon(R.drawable.delete_24)
                        .setTitle("DELET")
                        .setMessage("Do you want delete this note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                localDB.notesDao().DleNote(new Notes(arrnotes.get(position).getId(),arrnotes.get(position).getNote_title(),arrnotes.get(position).getNote_content()));
                                ((MainActivity)context).Show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                return false;
            }
        });


    }
    @Override
    public int getItemCount() {
        return arrnotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_Re,content_Re;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            note_Re=itemView.findViewById(R.id.note_Re);

            content_Re=itemView.findViewById(R.id.content_Re);


            cardView =itemView.findViewById(R.id.cardView);



        }
    }
    public int getRandomColorCode(){

        Random random = new Random();

        return Color.argb(255, random.nextInt(256), random.nextInt(256),     random.nextInt(256));

    }
}
