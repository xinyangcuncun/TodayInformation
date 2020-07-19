package com.web.god.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.web.god.jetpack.R;
import com.web.god.jetpack.User;
import com.web.god.jetpack.databinding.FragmentBlankBinding;

/**
 * Created by anson on 2020-07-12.
 */
public class BlankFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBlankBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        final User user = new User("Jeck",20);
        inflate.setUser(user);
        return inflate.getRoot();
    }
}
