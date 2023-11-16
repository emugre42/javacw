package gui;

import java.util.HashMap;

public class IDandPasswords {
    
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPasswords() {
        loginInfo.put("Tim Johnson", "tj3169");
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}
