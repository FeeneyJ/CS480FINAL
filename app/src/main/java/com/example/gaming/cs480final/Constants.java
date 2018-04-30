package com.example.gaming.cs480final;


public class Constants {
   // used for SQLLite implementation
    public static final String DATABASE_NAME = "zoo.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Customer Masurements";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "Phone number";
    public static final String KEY_ID = "id integer primary key autoincrement";
    public static final String KEY_NECK ="neck";
    public static final String KEY_SLEEVE = "sleeve";
    public static final String KEY_WAIST = "waist";
    public static final String KEY_OUT = "Outseam";
    public static final String KEY_CHEST = "Chest";
    public static final String CREATE_TABLE = "CREATE TABLE animals ("
            + Constants.KEY_ID + "," + Constants.KEY_NAME + " text,"
            + " integer);";
}
