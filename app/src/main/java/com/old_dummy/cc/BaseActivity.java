package com.old_dummy.cc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.old_dummy.cc.MainActivity.MainActivity;
import com.old_dummy.cc.MyHistoryActivity.MyHistoryActivity;
import com.old_dummy.cc.ProfileActivity.ProfileActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected LinearLayout navHome, navHistory, navProfile, navSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        navHome = findViewById(R.id.navHome);
        navHistory = findViewById(R.id.navHistory);
        navProfile = findViewById(R.id.navProfile);
        navSettings = findViewById(R.id.navSettings);

        Log.d("BaseActivity", "navHome: " + navHome);
        Log.d("BaseActivity", "navHistory: " + navHistory);
        Log.d("BaseActivity", "navProfile: " + navProfile);
        Log.d("BaseActivity", "navSettings: " + navSettings);

        if (navHome == null || navHistory == null || navProfile == null || navSettings == null) {
            throw new IllegalStateException("Bottom Navigation views are null. Check your layout file and IDs.");
        }
        navHome.setClickable(true);
        navHistory.setClickable(true);
        navProfile.setClickable(true);
        navSettings.setClickable(true);

        setupBottomNavigationBar();

        if (getLayoutResourceId() != 0) {
            getLayoutInflater().inflate(getLayoutResourceId(), findViewById(R.id.activityContent), true);
        }
    }

    protected abstract int getLayoutResourceId();

    private void setupBottomNavigationBar() {
        // Array of all navigation items
        LinearLayout[] navItems = {navHome, navHistory, navProfile, navSettings};

        // Listener for navigation item clicks
        View.OnClickListener listener = v -> {
            // Reset all items to unselected
            for (LinearLayout item : navItems) {
                ImageView icon = null;
                TextView label = null;

                if (item == navHome) {
                    icon = item.findViewById(R.id.navHomeIcon);
                    label = item.findViewById(R.id.navHomeLabel);
                } else if (item == navHistory) {
                    icon = item.findViewById(R.id.navHistoryIcon);
                    label = item.findViewById(R.id.navHistoryLabel);
                } else if (item == navProfile) {
                    icon = item.findViewById(R.id.navProfileIcon);
                    label = item.findViewById(R.id.navProfileLabel);
                } else if (item == navSettings) {
                    icon = item.findViewById(R.id.navSettingsIcon);
                    label = item.findViewById(R.id.navSettingsLabel);
                }

                if (icon != null) {
                    // Reset icon scale
                    icon.clearAnimation();
                    icon.setScaleX(1.0f);
                    icon.setScaleY(1.0f);
//                    icon.setColorFilter(Color.parseColor("#000000")); // Reset icon color
                }

                if (label != null) {
                    // Reset label color
                    label.setTextColor(Color.parseColor("#000000")); // Unselected color
                }
            }

            // Mark the clicked item as selected
            ImageView selectedIcon = null;
            TextView selectedLabel = null;
            v.setSelected(true);
            if (v == navHome) {
                selectedIcon = v.findViewById(R.id.navHomeIcon);
                selectedLabel = v.findViewById(R.id.navHomeLabel);
            } else if (v == navHistory) {
                selectedIcon = v.findViewById(R.id.navHistoryIcon);
                selectedLabel = v.findViewById(R.id.navHistoryLabel);
            } else if (v == navProfile) {
                selectedIcon = v.findViewById(R.id.navProfileIcon);
                selectedLabel = v.findViewById(R.id.navProfileLabel);
            } else if (v == navSettings) {
                selectedIcon = v.findViewById(R.id.navSettingsIcon);
                selectedLabel = v.findViewById(R.id.navSettingsLabel);
            }

            if (selectedIcon != null) {
                selectedIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.icon_scale));
                selectedIcon.setColorFilter(Color.parseColor("#FFFFFF")); // Selected icon color
            }

            if (selectedLabel != null) {
                selectedLabel.setTextColor(Color.parseColor("#FFFFFF")); // Selected label color
            }

            // Navigate to the respective activity if not already there
            if (v == navHome && !(this instanceof MainActivity)) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (v == navHistory && !(this instanceof MyHistoryActivity)) {
                startActivity(new Intent(this, MyHistoryActivity.class));
            } else if (v == navProfile && !(this instanceof ProfileActivity)) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (v == navSettings && !(this instanceof SettingsActivity)) {
                startActivity(new Intent(this, SettingsActivity.class));
            }

            // Disable transition animation
            overridePendingTransition(0, 0);
        };

        // Attach the click listener to each navigation item
        for (LinearLayout item : navItems) {
            item.setOnClickListener(listener);
        }

        // Default selection: Home
        if (this instanceof MainActivity) {
            navHome.callOnClick();
        } else if (this instanceof MyHistoryActivity) {
            navHistory.callOnClick();
        } else if (this instanceof ProfileActivity) {
            navProfile.callOnClick();
        } else if (this instanceof SettingsActivity) {
            navSettings.callOnClick();
        }
    }
}
