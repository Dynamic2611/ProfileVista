package com.ankit.profilevista.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ankit.profilevista.R;
import com.ankit.profilevista.model.Address;
import com.ankit.profilevista.model.Bank;
import com.ankit.profilevista.model.Company;
import com.ankit.profilevista.model.User;
import com.ankit.profilevista.viewmodel.UserViewModel;

public class DetailActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView detailName, detailEmail;
    private TextView tvBirthDate, tvHeight, tvWeight, tvBlood, tvPhone, tvUniversity;
    private TextView tvCompanyName, tvCompanyDept, tvCompanyTitle, tvCompanyAddress;
    private TextView tvAddress, tvCityState, tvCoordinates;
    private TextView tvCardNumber, tvCardType, tvIban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Bind views
        avatar = findViewById(R.id.detailAvatar);
        detailName = findViewById(R.id.detailName);
        detailEmail = findViewById(R.id.detailEmail);

        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvBlood = findViewById(R.id.tvBlood);
        tvPhone = findViewById(R.id.tvPhone);
        tvUniversity = findViewById(R.id.tvUniversity);

        tvCompanyName = findViewById(R.id.tvCompanyName);
        tvCompanyDept = findViewById(R.id.tvCompanyDept);
        tvCompanyTitle = findViewById(R.id.tvCompanyTitle);
        tvCompanyAddress = findViewById(R.id.tvCompanyAddress);

        tvAddress = findViewById(R.id.tvAddress);
        tvCityState = findViewById(R.id.tvCityState);
        tvCoordinates = findViewById(R.id.tvCoordinates);

        tvCardNumber = findViewById(R.id.tvCardNumber);
        tvCardType = findViewById(R.id.tvCardType);
        tvIban = findViewById(R.id.tvIban);

        // Gets user ID from intent
        int id = getIntent().getIntExtra("user_id", -1);
        if (id == -1) {
            finish();
            return;
        }

        // Init ViewModel
        UserViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(UserViewModel.class);

        // Observe user from Room
        viewModel.getUserById(id).observe(this, user -> {
            if (user == null) {
                finish();
                return;
            }
            bind(user);
        });
    }

    // Binds user data to views
    private void bind(User u) {
        Glide.with(this)
                .load(u.image)
                .circleCrop()
                .placeholder(R.drawable.ic_user_placeholder)
                .error(R.drawable.ic_user_placeholder)
                .into(avatar);

        detailName.setText(safe(u.firstName) + " " + safe(u.lastName));
        detailEmail.setText(safe(u.email));

        tvBirthDate.setText("Birth date: " + safe(u.birthDate));
        tvHeight.setText("Height: " + u.height);
        tvWeight.setText("Weight: " + u.weight);
        tvBlood.setText("Blood group: " + safe(u.bloodGroup));
        tvPhone.setText("Phone: " + safe(u.phone));
        tvUniversity.setText("University: " + safe(u.university));

        Company c = u.company;
        if (c != null) {
            tvCompanyName.setText("Name: " + safe(c.name));
            tvCompanyDept.setText("Department: " + safe(c.department));
            tvCompanyTitle.setText("Title: " + safe(c.title));
            if (c.address != null) {
                tvCompanyAddress.setText(safe(c.address.address) + ", " + safe(c.address.city));
            } else {
                tvCompanyAddress.setText("—");
            }
        } else {
            tvCompanyName.setText("—");
            tvCompanyDept.setText("—");
            tvCompanyTitle.setText("—");
            tvCompanyAddress.setText("—");
        }

        Address a = u.address;
        if (a != null) {
            tvAddress.setText(safe(a.address));
            tvCityState.setText(safe(a.city) + ", " + safe(a.state) + " - " + safe(a.postalCode));
            if (a.coordinates != null) {
                tvCoordinates.setText("Lat: " + a.coordinates.lat + ", Lng: " + a.coordinates.lng);
            } else {
                tvCoordinates.setText("—");
            }
        } else {
            tvAddress.setText("—");
            tvCityState.setText("—");
            tvCoordinates.setText("—");
        }

        Bank b = u.bank;
        if (b != null) {
            tvCardNumber.setText("Card: " + safe(b.cardNumber));
            tvCardType.setText("Type: " + safe(b.cardType));
            tvIban.setText("IBAN: " + safe(b.iban));
        } else {
            tvCardNumber.setText("—");
            tvCardType.setText("—");
            tvIban.setText("—");
        }
    }

    private String safe(Object o) {
        return o == null ? "—" : o.toString();
    }
}
