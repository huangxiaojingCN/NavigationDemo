package com.honey.androidarchitecturecomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvProductCount = view.findViewById(R.id.tv_productCount);

        ProfileFragmentArgs profileFragmentArgs = ProfileFragmentArgs.fromBundle(getArguments());
        int productCount = profileFragmentArgs.getProductCount();
        tvProductCount.setText("product Count: " + productCount);
    }
}
