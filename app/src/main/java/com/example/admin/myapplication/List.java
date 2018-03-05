package com.example.admin.myapplication;

/**
 * Created by admin on 9/4/2017.
 */

public class List {

    private String mDiseaseName;
    private String mPersonStatus;
    private int mImageResourceId;

    public List(String diseaseName,int imageResourceId){
        mDiseaseName=diseaseName;
        mImageResourceId = imageResourceId;

    }

    public String getDiseaseName(){
        return mDiseaseName;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }

}
