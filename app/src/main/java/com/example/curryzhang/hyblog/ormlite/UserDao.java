package com.example.curryzhang.hyblog.ormlite;

import android.content.Context;

import com.example.curryzhang.hyblog.ormlite.bean.User;
import com.example.curryzhang.hyblog.ormlite.db.DatabaseHelper;

import java.sql.SQLException;

/**
 * Created by curry.zhang on 4/18/2017.
 */
public class UserDao {
    private Context context;

    public UserDao(Context context) {
        this.context = context;
    }

    public void add(User user) {
        try {
            DatabaseHelper.getHelper(context).getUserDao().create(user);
        } catch (SQLException e) {
        }
    }//......

}