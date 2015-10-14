package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meghana on 10/6/2015.
 */

public class Storage {

    private HashMap<String, ArrayList<Secret>> secretList;
    public Storage(){
        secretList = new HashMap<String, ArrayList<Secret>>();
    }

    public void store(String userId, ArrayList<Secret> li)
    {
        secretList.put(userId, li);
    }

    public HashMap<String, ArrayList<Secret>> getMap()
    {
        return secretList;
    }

}
