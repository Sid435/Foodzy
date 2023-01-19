package com.example.foodzy;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StaticMenuOptionsAdaptor extends RecyclerView.Adapter<StaticMenuOptionsAdaptor.ViewHolder> {
    private List<StaticMenuOptionsModal> UserList;
    private Context context;
    private Class<StaticMenu> mainActivity = StaticMenu.class;

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

        holder.setData(resource, text, price);
    }
    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.idIVFood);
            text = itemView.findViewById(R.id.idTVFood);
            price = itemView.findViewById(R.id.idTVFoodPrice);

        }

        public void setData(int resource, String text1, String price1) {
            image.setImageResource(resource);
            text.setText(text1);
            price.setText(price1);
        }
    }
}
