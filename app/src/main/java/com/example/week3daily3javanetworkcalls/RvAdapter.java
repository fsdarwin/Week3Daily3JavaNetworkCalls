package com.example.week3daily3javanetworkcalls;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.week3daily3javanetworkcalls.model.User.Result;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<Result> resultArrayList;
    Intent intent;

    public RvAdapter(ArrayList<Result> resultArrayList) {
        this.resultArrayList = resultArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder viewHolder, int position) {
        Result result = resultArrayList.get(position);
        if (result != null) {
            //SET user in the viewHolder
            viewHolder.setItemUser(result);
            //DATA to display for each user
            Glide.with(viewHolder.itemView.getContext())
                    .load(result.getPicture().getThumbnail())
                    .into(viewHolder.imgImage);
            String name = result.getName().getLast()
                    + ", " + result.getName().getFirst()
                    + ", " + result.getName().getTitle();
            viewHolder.tvName.setText(name);
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //DECLARE Views
        ImageView imgImage;
        TextView tvName;
        Result itemUser;


        //INSTANTIATE Views
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgImage);
            tvName = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(this);
        }
        public void setItemUser(Result itemUser){
            this.itemUser = itemUser;
        }

        @Override
        public void onClick(View v) {
            //CREATE intent to send selected user to
            // next activity (FragmentHolder) for display
            Context context = itemView.getContext();
            intent = new Intent(context, FragmentHolder.class);
            intent.putExtra("user", itemUser);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return resultArrayList != null ? resultArrayList.size() : 0;
    }
}
