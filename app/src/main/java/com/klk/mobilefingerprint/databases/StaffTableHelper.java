package com.klk.mobilefingerprint.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.klk.mobilefingerprint.constantvalues.DBConfig;
import com.klk.mobilefingerprint.models.Staff;
import com.klk.mobilefingerprint.services.TableHelperService;

import java.util.ArrayList;

public class StaffTableHelper implements TableHelperService {

    public StaffTableHelper(){}

    @Override
    public void insert(SQLiteDatabase db, ContentValues values) {
        if (values != null) {
            db.insertWithOnConflict(DBConfig.staffTable.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.close();
        }
    }

    @Override
    public ArrayList getAll(SQLiteDatabase db) {
        ArrayList<Staff> staffList = new ArrayList<>();
        Cursor cursor = db.query(DBConfig.staffTable.TABLE_NAME, null, null, null, null, null, DBConfig.staffTable.NAME);

        while(cursor.moveToNext()) {
            Staff staff = new Staff();
            staff.set_id(cursor.getInt(cursor.getColumnIndex(DBConfig.staffTable._ID)));
            staff.setName(cursor.getString(cursor.getColumnIndex(DBConfig.staffTable.NAME)));
            staff.setPhoto(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.PHOTO)));
            staff.setByteFinger1(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_1)));
            staff.setByteFinger2(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_2)));
            staff.setByteFinger3(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_3)));
            staff.setByteFinger4(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_4)));
            staff.setByteFinger5(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_5)));
            staff.setByteFinger6(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_6)));
            staff.setByteFinger7(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_7)));
            staff.setByteFinger8(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_8)));
            staff.setByteFinger9(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_9)));
            staff.setByteFinger10(cursor.getBlob(cursor.getColumnIndex(DBConfig.staffTable.BYTE_FINGER_10)));
            staffList.add(staff);
        }

        db.close();
        return staffList;
    }
}
