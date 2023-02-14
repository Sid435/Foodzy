package com.example.foodzy;
import android.app.AlertDialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StaticMenuOptionsAdaptor extends RecyclerView.Adapter<StaticMenuOptionsAdaptor.ViewHolder> {
    private List<StaticMenuOptionsModal> UserList;
    private Context context;
    private Class<StaticMenu> mainActivity = StaticMenu.class;
    DatabaseReference ref1;
    public StaticMenuOptionsAdaptor(List<StaticMenuOptionsModal> userList, Context context) {
        UserList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_food_menu_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StaticMenuOptionsModal temp2 = UserList.get(position);


        int resource = UserList.get(position).getImage();
        String text = UserList.get(position).getText();
        String price = String.valueOf(UserList.get(position).getPrice());
        ArrayList <String> a1 = new ArrayList<>();
        holder.setData(resource, text, price);
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.star.setImageResource(R.drawable.filled_star);
                 ref1 = FirebaseDatabase.getInstance().getReference().child("FAVOURITES");
                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(text.toUpperCase())){
                            System.out.println("contains");
                            ref1.child(text.toUpperCase()).removeValue();
                            Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            System.out.println("not contained");
                            ref1.child(text.toUpperCase()).setValue(price);
                            Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.static_menu_food_ingridients,null);
                TextView tv = dialogView.findViewById(R.id.idTVDescription);
                tv.setText(temp2.getDesc().toString());
                tv.setMovementMethod(new ScrollingMovementMethod());
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text, price;
        private ImageView star;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.favImage);
            text = itemView.findViewById(R.id.fav_name);
            price = itemView.findViewById(R.id.fav_price);
            star = itemView.findViewById(R.id.star);
        }

        public void setData(int resource, String text1, String price1) {
            image.setImageResource(resource);
            text.setText(text1);
            price.setText(price1);
        }

    }
}
