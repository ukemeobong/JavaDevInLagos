package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/20/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

//CLASS 1:
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<UserInformation> userData;

    public UserAdapter(List<UserInformation> userList) {

        this.userData = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.usersview_item, parent, false);
        return new UserViewHolder(view);
    }


    //This method calls the viewholder and then attaches each user in the
    // data to the recycler view and positions it to the view
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Picasso.with(context).load(userData.get(position).getAvatarUrl()).into(holder.ImageView);
        holder.usernameView.setText(userData.get(position).getLogin());
        holder.locationView.setVisibility(View.VISIBLE);

    }

    //This method returns the number of items(users) on the list
    @Override
    public int getItemCount() {
        return userData.size();
    }

    //CLASS 2:
    //This method deals with each item when clicked and navigates to the UserDetails activity
    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ImageView;
        TextView usernameView;
        TextView locationView;


        public UserViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ImageView = (ImageView) itemView.findViewById(R.id.userImage);
            usernameView = (TextView) itemView.findViewById(R.id.userTextView);
            locationView = (TextView) itemView.findViewById(R.id.locationView);


        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, UserDetails.class);
            UserInformation user = userData.get(getLayoutPosition());
            intent.putExtra("user", user);
            context.startActivity(intent);
        }
    }
}

