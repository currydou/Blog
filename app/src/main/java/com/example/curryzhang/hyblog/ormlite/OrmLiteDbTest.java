package com.example.curryzhang.hyblog.ormlite;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.curryzhang.hyblog.ormlite.bean.User;
import com.example.curryzhang.hyblog.ormlite.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by curry.zhang on 4/18/2017.
 */

public class OrmLiteDbTest extends AndroidTestCase {

    public void testAddUser() {

        User u1 = new User("zhy", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            helper.getUserDao().create(u1);
            u1 = new User("zhy2", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy3", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy4", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy5", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy6", "2B青年");
            helper.getUserDao().create(u1);

            testList();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testDeleteUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            helper.getUserDao().deleteById(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(3);
            helper.getUserDao().update(u1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("TAG", users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
//        ctx = getActivity().getApplicationContext();
    }

}