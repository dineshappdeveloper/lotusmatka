package com.old_dummy.cc;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Adapters.MenuAdapter;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.MainActivity.MainContract;
import com.old_dummy.cc.Models.MenuItemModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity implements MenuAdapter.OnMenuItemClickListener {
    TextInputEditText inputPersonName, inputMobileNumber, inputEmail;
    ProgressBar progressBar;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    RecyclerView navigationRecyclerView;
    List<MenuItemModel> menuItems;
    MainContract.Presenter presenter;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_setting;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        intIDs();
    }



    private void intIDs() {
        menuItems = new ArrayList<>();
        navigationRecyclerView = findViewById(R.id.navigation_recycler_view);
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressBar);
        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);

        updateUserStatus("2");

    }

    private void updateUserStatus(String accountStatus) {
        if (accountStatus.equals("2")){
            menuItems.clear();
            menuItems.add(new MenuItemModel("Home", R.drawable.outline_home_24));
            menuItems.add(new MenuItemModel("Profile", R.drawable.baseline_perm_identity_24));
            menuItems.add(new MenuItemModel("Contact Us", R.drawable.outline_contact_phone_24));
            menuItems.add(new MenuItemModel("Share With Friends", R.drawable.baseline_share_24));
            menuItems.add(new MenuItemModel("Privacy Policy", R.drawable.outline_gpp_maybe_24));
            menuItems.add(new MenuItemModel("Rate App", R.drawable.outline_star_border_24));
            menuItems.add(new MenuItemModel("Change Password", R.drawable.baseline_password_24));
            menuItems.add(new MenuItemModel("Logout", R.drawable.baseline_logout_24));
            setNavigationMenu(menuItems);

        }else{
            menuItems.clear();
            menuItems.add(new MenuItemModel("Home", R.drawable.outline_home_24));
            menuItems.add(new MenuItemModel("Profile", R.drawable.baseline_perm_identity_24));
            menuItems.add(new MenuItemModel("Wallet", R.drawable.outline_add_business_24));
            menuItems.add(new MenuItemModel("My History", R.drawable.baseline_history_24));
            menuItems.add(new MenuItemModel("Game Rates", R.drawable.baseline_currency_bitcoin_24));
            menuItems.add(new MenuItemModel("How To Play", R.drawable.baseline_play_circle_outline_24));
            menuItems.add(new MenuItemModel("Contact Us", R.drawable.outline_contact_phone_24));
            menuItems.add(new MenuItemModel("Share With Friends", R.drawable.baseline_share_24));
            menuItems.add(new MenuItemModel("Privacy Policy", R.drawable.outline_gpp_maybe_24));
            menuItems.add(new MenuItemModel("Rate App", R.drawable.outline_star_border_24));
            menuItems.add(new MenuItemModel("Change Password", R.drawable.baseline_password_24));
            menuItems.add(new MenuItemModel("Logout", R.drawable.baseline_logout_24));
            setNavigationMenu(menuItems);
        }
    }

    void setNavigationMenu(List<MenuItemModel> menuItemList){
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MenuAdapter menuAdapter = new MenuAdapter(menuItemList, this);
        navigationRecyclerView.setAdapter(menuAdapter);
    }





    @Override
    protected void onPause() {
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


        @Override
    public void onMenuItemClick(String menuItem) {
        // Handle the menu item clicks here
        // For example, you can replace fragments, start new activities, etc.
        switch (menuItem) {
            case "Profile":
                presenter.profile(SettingsActivity.this);
                break;
            case "Wallet":
                presenter.funds(SettingsActivity.this);
                break;
            case "My History":
                presenter.history(SettingsActivity.this,200);
                break;
            case "Game Rates":
                presenter.gameRates(SettingsActivity.this,1);
                break;
            case "How To Play":
                presenter.gameRates(SettingsActivity.this,2);
                break;
            case "Contact Us":
                presenter.contactUs(SettingsActivity.this);
                break;
            case "Share With Friends":
                presenter.shareWithFriends(SettingsActivity.this);
                break;
            case "Privacy Policy":
                presenter.privacyPolicy(this);
                break;
            case "Rate App":
                presenter.rateApp(SettingsActivity.this);
                break;
            case "Change Password":
                presenter.changePassword(SettingsActivity.this);
                break;
            case "Logout":
                presenter.logout(SettingsActivity.this);
                break;        }
    }
}