package com.example.jagdeepsingh.samplepro.dagger2.database;

import com.example.jagdeepsingh.samplepro.dagger2.model.User;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public interface DataService {

    User getUser();
    void storeUser(User user);
}
