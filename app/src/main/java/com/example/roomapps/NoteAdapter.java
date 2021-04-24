package com.example.roomapps;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Holder> {
    private List<Note> list = new ArrayList<>();
    private ItemListener itemListener;
    public interface ItemListener{
        void noteListener(Note note);
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_notes, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Note note = list.get(position);
        holder.titles.setText(note.getTitle());
        holder.descs.setText(note.getDescription());
        holder.proritys.setText(note.getPriority()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotes(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Note getNoteAt(int position){
        return list.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView titles, descs, proritys;

        public Holder(@NonNull final View itemView) {
            super(itemView);

            titles = itemView.findViewById(R.id.titless);
            descs = itemView.findViewById(R.id.desc);
            proritys = itemView.findViewById(R.id.prority);
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position=getAdapterPosition();
                  if (itemListener!=null&& position!=RecyclerView.NO_POSITION){
                      itemListener.noteListener(list.get(position));
                  }
              }
          });
        }
    }
}
