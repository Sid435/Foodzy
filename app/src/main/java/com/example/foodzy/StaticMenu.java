package com.example.foodzy;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StaticMenu extends AppCompatActivity implements StaticCategoryOptionAdaptor.CategoryClickInterface{
    private RecyclerView recyclerViewMenuMainCourse,recyclerViewMenuAppetizers,
            recyclerViewMenuEntree, recyclerViewMenuDesert,
            recyclerViewMenuBeverages, recyclerViewCategory;
    private GridLayoutManager layoutManagerMenuMainCourse, layoutManagerMenuAppetizers,
            layoutManagerMenuEntree, layoutManagerMenuDesert,
            layoutManagerMenuBeverages;
    private LinearLayoutManager layoutManagerCategory;
    private List<StaticMenuOptionsModal> userListMenuMainCourse, userListMenuAppetizers,
            userListMenuEntree, userListMenuDesert, userListMenuBeverages;
    private List<StaticCategoryOptionModal> userListCategory ;
    private StaticMenuOptionsAdaptor adaptorMenuMainCourse, adaptorMenuAppetizers, adaptorMenuEntree,
            adaptorMenuDesert, adaptorMenuBeverages;
    StaticCategoryOptionAdaptor adaptorCategory;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_menu);
        title = findViewById(R.id.idTVFoodtype);

        initDataCategory();
        initRecyclerViewCategory();

        initDataMenuMainCourse();
        initRecyclerViewMenuMainCourse();

        initDataMenuAppetizers();
        initRecyclerViewMenuAppetizers();

        initDataMenuEntree();
        initRecyclerViewMenuEntree();

        initDataMenuDesert();
        initRecyclerViewMenuDesert();

        initDataMenuBeverages();
        initRecyclerViewMenuBeverages();
    }


    private void initDataCategory() {
        userListCategory = new ArrayList<>();
        userListCategory.add(new StaticCategoryOptionModal(R.drawable.main_course, "Main Course"));
        userListCategory.add(new StaticCategoryOptionModal(R.drawable.appetizers, "Appetizers"));
        userListCategory.add(new StaticCategoryOptionModal(R.drawable.entres, "Entrées"));
        userListCategory.add(new StaticCategoryOptionModal(R.drawable.dessets,"Desserts"));
        userListCategory.add(new StaticCategoryOptionModal(R.drawable.beverages, "Beverages"));

    }
    private void initRecyclerViewCategory() {
        recyclerViewCategory = findViewById(R.id.typeFood);
        layoutManagerCategory = new LinearLayoutManager(this);

        adaptorCategory = new StaticCategoryOptionAdaptor(userListCategory, this, this::onCategoryClick);
        recyclerViewCategory.setAdapter(adaptorCategory);
        adaptorCategory.notifyDataSetChanged();
    }

    private void initDataMenuMainCourse(){
        title.setText("Main Course");
        userListMenuMainCourse = new ArrayList<>();
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.chicken_shashlic, "Chicken Shashlic", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.malai_kofta, "Malai Kofta", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.palak_paneer, "Palak Paneer", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.saag_aloo, "Saag Aloo", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.paneer_jalfrezi, "Paneer Jalfrezi", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.paneer_vindaloo, "Paneer Vindaloo", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.vegetable_biriyani, "Vegetable Biryani", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.tandoori_chicken, "Tandoori Chicken", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.lamb_meatballs, "Lamb Meatballs", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.paneer_tikka_masala, "Paneer Tikka Masala", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.palak_chicken, "Palak Chicken", 100));
        userListMenuMainCourse.add(new StaticMenuOptionsModal(R.drawable.chicken_curry, "Chicken Curry", 100));
    }
    private void initRecyclerViewMenuMainCourse(){
        recyclerViewMenuMainCourse = findViewById(R.id.foodPlate);
        layoutManagerMenuMainCourse = new GridLayoutManager(StaticMenu.this, 2);

        adaptorMenuMainCourse = new StaticMenuOptionsAdaptor(userListMenuMainCourse, getApplicationContext());
        recyclerViewMenuMainCourse.setAdapter(adaptorMenuMainCourse);
        adaptorMenuMainCourse.notifyDataSetChanged();
    }

    private void initDataMenuAppetizers() {
        title.setText("Appetizers");
        userListMenuAppetizers = new ArrayList<>();
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.appetizers, "Crescents", 100));
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.canapes, "Canapes", 100));
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.stuffed_samosa, "Stuffed Samosa", 100));
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.pita_chips, "Pita Chips", 100));

        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.chilli_paneer, "Chilli Paneer", 100));
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.medu_vada, "Medu Vada", 100));
        userListMenuAppetizers.add(new StaticMenuOptionsModal(R.drawable.stuffed_mashrooms, "Stuffed Mushroom", 100));
    }
    private void initRecyclerViewMenuAppetizers() {
        recyclerViewMenuAppetizers = findViewById(R.id.foodPlateAppetizers);
        layoutManagerMenuAppetizers = new GridLayoutManager(StaticMenu.this, 2);

        adaptorMenuAppetizers = new StaticMenuOptionsAdaptor(userListMenuAppetizers, getApplicationContext());
        recyclerViewMenuAppetizers.setAdapter(adaptorMenuAppetizers);
        adaptorMenuAppetizers.notifyDataSetChanged();
    }

    private void initDataMenuEntree() {
        title.setText("Entrées");
        userListMenuEntree = new ArrayList<>();
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.antipasto_platter, "Antipasto Platter", 100));
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.chicken_dumpling, "Chicken and Spinach Dumpling", 100));
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.crispy_bocconcini, "Crispy Bacconcini", 100));
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.chilli_prawns, "Chilli Prawns", 100));

        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.bacon_and_cheese_croquettes, "Bacon and Cheese Croquettes", 100));
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.bruschetta, "Bruschetta", 100));
        userListMenuEntree.add(new StaticMenuOptionsModal(R.drawable.steamed_dumplings, "Steamed Dumplings", 100));


    }
    private void initRecyclerViewMenuEntree() {
        recyclerViewMenuEntree = findViewById(R.id.foodPlateEntree);
        layoutManagerMenuEntree = new GridLayoutManager(StaticMenu.this, 2);

        adaptorMenuEntree = new StaticMenuOptionsAdaptor(userListMenuEntree, getApplicationContext());
        recyclerViewMenuEntree.setAdapter(adaptorMenuEntree);
        adaptorMenuEntree.notifyDataSetChanged();
    }

    private void initDataMenuDesert() {
        title.setText("Desert");
        userListMenuDesert = new ArrayList<>();
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.molten_cake, "Chocolate molten     Cake", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.chocolate_truffle, "Chocolate truffle ", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.ube_cheesecake, "Ube Cheesecake", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.allrecipes_brownies, "TikTok Brownies", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.cinnamon_cake, "Cinnamon Roll Dump Cake", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.pumpkin_pie, "Marbled Chocolate Pumpkin Pie", 100));
        userListMenuDesert.add(new StaticMenuOptionsModal(R.drawable.pecan_pie_cheesecake, "Marbled Chocolate Pumpkin Pie", 100));

    }
    private void initRecyclerViewMenuDesert() {
        recyclerViewMenuDesert = findViewById(R.id.foodPlateDesert);
        layoutManagerMenuDesert = new GridLayoutManager(StaticMenu.this, 2);

        adaptorMenuDesert = new StaticMenuOptionsAdaptor(userListMenuDesert, getApplicationContext());
        recyclerViewMenuDesert.setAdapter(adaptorMenuDesert);
        adaptorMenuDesert.notifyDataSetChanged();
    }


    private void initDataMenuBeverages() {
        title.setText("Beverages");
        userListMenuBeverages = new ArrayList<>();
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.pink_gin, "Pink Gin", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.lemon_fizz, "Lemon Fizz", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.sake_fizz_cocktail, "Sake Fizz Cocktail", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.vanilla_strawbeyy_iced_tea, "Refreshing Vanilla Strawberry Iced Tea", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.peach_smoothie, "Peach Smoothie", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.raspberry_smoothie, "Rhubarb Bellini Smoothie", 100));
        userListMenuBeverages.add(new StaticMenuOptionsModal(R.drawable.turquoise_tonic, " Turquoise Tonic", 100));



    }
    private void initRecyclerViewMenuBeverages() {
        recyclerViewMenuBeverages = findViewById(R.id.foodPlateBeverages);
        layoutManagerMenuBeverages = new GridLayoutManager(StaticMenu.this, 2);

        adaptorMenuBeverages = new StaticMenuOptionsAdaptor(userListMenuBeverages, getApplicationContext());
        recyclerViewMenuBeverages.setAdapter(adaptorMenuBeverages);
        adaptorMenuBeverages.notifyDataSetChanged();
    }


    @Override
    public void onCategoryClick(int position) {
        String category = userListCategory.get(position).getText();

        setRecyclerView(category);
    }

    private void setRecyclerView(String category) {
        if (category.equals("Main Course") ){
            recyclerViewMenuMainCourse.setVisibility(View.VISIBLE);recyclerViewMenuAppetizers.setVisibility(View.GONE);recyclerViewMenuEntree.setVisibility(View.GONE);
            recyclerViewMenuDesert.setVisibility(View.GONE);recyclerViewMenuBeverages.setVisibility(View.GONE);

            initDataMenuMainCourse();
            initRecyclerViewMenuMainCourse();
        }

        else if(category.equals("Appetizers")) {
            recyclerViewMenuMainCourse.setVisibility(View.GONE);recyclerViewMenuAppetizers.setVisibility(View.VISIBLE);recyclerViewMenuEntree.setVisibility(View.GONE);
            recyclerViewMenuDesert.setVisibility(View.GONE);recyclerViewMenuBeverages.setVisibility(View.GONE);
            initDataMenuAppetizers();
            initRecyclerViewMenuAppetizers();
        }

        else if(category.equals("Entrées")) {
            recyclerViewMenuMainCourse.setVisibility(View.GONE);recyclerViewMenuAppetizers.setVisibility(View.GONE);recyclerViewMenuEntree.setVisibility(View.VISIBLE);
            recyclerViewMenuDesert.setVisibility(View.GONE);recyclerViewMenuBeverages.setVisibility(View.GONE);
            initDataMenuEntree();
            initRecyclerViewMenuEntree();
        }

        else if(category.equals("Desserts")) {
            recyclerViewMenuMainCourse.setVisibility(View.GONE);recyclerViewMenuAppetizers.setVisibility(View.GONE);recyclerViewMenuEntree.setVisibility(View.GONE);
            recyclerViewMenuDesert.setVisibility(View.VISIBLE);recyclerViewMenuBeverages.setVisibility(View.GONE);
            initDataMenuDesert();
            initRecyclerViewMenuDesert();
        }
        else if (category.equals("Beverages")) {
            recyclerViewMenuMainCourse.setVisibility(View.GONE);recyclerViewMenuAppetizers.setVisibility(View.GONE);recyclerViewMenuEntree.setVisibility(View.GONE);
            recyclerViewMenuDesert.setVisibility(View.GONE);recyclerViewMenuBeverages.setVisibility(View.VISIBLE);
            initDataMenuBeverages();
            initRecyclerViewMenuBeverages();
        }
    }
}