package com.example.sqlitebasic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{
    private List<Model> playerLists;
    private List<Model> searchList;
    private OnItemClick onItemClick;



    public void getplayerlist(List<Model> playerLists){
        this.playerLists = playerLists;
        searchList = new ArrayList<>(playerLists);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.singleCode.setText("Code: "+playerLists.get(position).getCode());
        holder.singleName.setText("Name: "+playerLists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playerLists.size();
    }

    public Filter getFilter(){
        return studentfilter;
    }


    private Filter studentfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence ch) {
            List<Model> filterUser= new ArrayList<>();
            if(ch ==null || ch.length()==0){
                filterUser.addAll(searchList);
            }
            else {
                String filterPattern= ch.toString().toLowerCase().trim();
                for (Model model:searchList){
                    if(model.getCode().toLowerCase().contains(ch) || model.getName().toLowerCase().contains(ch)){
                        filterUser.add(model);
                    }
                }

            }

            FilterResults filterResults= new FilterResults();
            filterResults.values= filterUser;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            playerLists.clear();
           playerLists.addAll((List<Model>)results.values);
            notifyDataSetChanged();
        }
    };

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView singleCode,singleName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            singleCode = itemView.findViewById(R.id.singleCodeId);
            singleName = itemView.findViewById(R.id.singleNameId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null && position != RecyclerView.NO_POSITION){
                        onItemClick.onItemClick(position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position= getAdapterPosition();
                    if(onItemClick != null && position != RecyclerView.NO_POSITION){
                        onItemClick.onLongItemClick(position);
                    }
                    return false;
                }
            });
        }
    }


    public interface OnItemClick{

        void onItemClick(int position);
        void onLongItemClick(int position);
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

  /*  public void setOnLongItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }*/

}
