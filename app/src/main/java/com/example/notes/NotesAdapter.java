package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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
        View v = LayoutInflater.from(context).inflate(R.layout.notes_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.note_Re.setText(arrnotes.get(position).getNote_title());
        Random r = new Random();
        int red=r.nextInt(255 - 0 + 1)+0;
        int green=r.nextInt(255 - 0 + 1)+0;
        int blue=r.nextInt(255 - 0 + 1)+0;

        GradientDrawable draw = new GradientDrawable();

        draw.setColor(Color.rgb(red,green,blue));
        holder.content_Re.setBackground(draw);
        holder.cardView.setBackground(draw);
        holder.note_Re.setBackground(draw);

        holder.content_Re.setText(arrnotes.get(position).getNote_content());
        holder.cardView.setRadius(25);
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

            content_Re=itemView.findViewById(R.id. content_Re);

            cardView =itemView.findViewById(R.id. cardView);



        }
    }
}
