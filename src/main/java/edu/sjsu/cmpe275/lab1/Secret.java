package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Meghana on 10/1/2015.
 */

public class Secret {
    private UUID uuid;
    private String userId;
    private ArrayList<String> sharedUserList;

    public Secret(){
        sharedUserList = new ArrayList<String>();
    }


    public void setUserId(String userId) { this.userId = userId; }
    public void setUUID(UUID uuid){ this.uuid = uuid; }
    public UUID getUUID() { return uuid; }
    public String getuserId() { return userId; }
    public void addToSharedUserList(String userId)
    {
        this.sharedUserList.add(userId);
    }

    public ArrayList<String> getSharedUserList(){ return sharedUserList; }

}
