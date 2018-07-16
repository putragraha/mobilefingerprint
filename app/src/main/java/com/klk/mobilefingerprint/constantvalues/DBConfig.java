package com.klk.mobilefingerprint.constantvalues;

public class DBConfig {

    public static final String DB_NAME = "com.klk.mobilefingerprint";
    public static final int DB_VERSION = 1;

    public class staffTable {
        public static final String TABLE_NAME = "staff";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String PHOTO = "photo";
        public static final String BYTE_FINGER_1 = "byte_finger_1";
        public static final String BYTE_FINGER_2 = "byte_finger_2";
        public static final String BYTE_FINGER_3 = "byte_finger_3";
        public static final String BYTE_FINGER_4 = "byte_finger_4";
        public static final String BYTE_FINGER_5 = "byte_finger_5";
        public static final String BYTE_FINGER_6 = "byte_finger_6";
        public static final String BYTE_FINGER_7 = "byte_finger_7";
        public static final String BYTE_FINGER_8 = "byte_finger_8";
        public static final String BYTE_FINGER_9 = "byte_finger_9";
        public static final String BYTE_FINGER_10 = "byte_finger_10";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
                _ID + " INTEGER, " +
                NAME + " TEXT NOT NULL, " +
                PHOTO + " BLOB, " +
                BYTE_FINGER_1 + " BLOB, " +
                BYTE_FINGER_2 + " BLOB, " +
                BYTE_FINGER_3 + " BLOB, " +
                BYTE_FINGER_4 + " BLOB, " +
                BYTE_FINGER_5 + " BLOB, " +
                BYTE_FINGER_6 + " BLOB, " +
                BYTE_FINGER_7 + " BLOB, " +
                BYTE_FINGER_8 + " BLOB, " +
                BYTE_FINGER_9 + " BLOB, " +
                BYTE_FINGER_10 + " BLOB " +
                ");";
    }
}
