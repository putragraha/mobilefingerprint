package com.klk.mobilefingerprint.data;

import com.klk.mobilefingerprint.models.Staff;

import java.util.ArrayList;

public class GlobalData {

    public static ArrayList<Staff> StaffList = new ArrayList<>();
    private static GlobalData instance;

    public static GlobalData getInstance() {
        if(null == instance) {
            instance = new GlobalData();
        }
        return instance;
    }

    public void loadStaffData(){
        for (int i = 0; i < 5; i++) {
            Staff staff = new Staff();
            staff.set_id(1081012380);
            staff.setName("Seseorang dengan Nama " + i);
            StaffList.add(staff);
        }
    }
}
