package com.web.god.jetpack;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

/**
 * Created by anson on 2020-07-19.
 */
public class User extends BaseObservable {
    public String rose = "Rose";
    public ObservableField<String> name ;
    private int age;

    public User(String name,int age) {
        this.name = new ObservableField<>(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public void onClickFriend(String names) {
        name.set(names);
    }
}
