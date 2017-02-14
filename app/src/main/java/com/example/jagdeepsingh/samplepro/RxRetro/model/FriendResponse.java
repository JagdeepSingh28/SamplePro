package com.example.jagdeepsingh.samplepro.RxRetro.model;

import java.util.ArrayList;

/**
 * Created by Jagdeep.Singh on 17-11-2016.
 */

public class FriendResponse {

    public String error;
    public String message;
    public ArrayList<Data> data;

    public class Data{
        public String userPassword;
        public String userEmail;
        public String _id;
    }



//    public FriendLocations friendLocations;
//
//    public FriendResponse(){
//
//    }
//
//    public class FriendLocations {
//        public Data data;
//        public class Data{
//            public ArrayList<Friend> friend = new ArrayList<>();
//            public class Friend{
//                public String friendName;
//                public String friendType;
//                public String lat;
//                public String lon;
//            }
//        }
//    }
}
