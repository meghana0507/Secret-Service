package edu.sjsu.cmpe275.lab1;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Meghana on 10/1/2015.
 */

public class SecretService{
   // private HashMap<String, ArrayList<Secret>> secretList = new HashMap<String, ArrayList<Secret>>();
    @Autowired
    Storage storage;

    public UUID storeSecret(String userId, Secret secret){
        UUID uuid = UUID.randomUUID();
        secret.setUUID(uuid);
        secret.setUserId(userId);
        ArrayList<Secret> secretList2;
        if(storage.getMap().containsValue(userId))
            secretList2 = storage.getMap().get(userId);
        else
            secretList2 = new ArrayList<Secret>();
        secretList2.add(secret);
        storage.store(userId, secretList2);
        return secret.getUUID();
    }

    public Secret readSecret(String userId, UUID secretId){
        ArrayList<Secret> tempList;
        Secret returnSecret=null;

        for(Map.Entry<String, ArrayList<Secret>> map: storage.getMap().entrySet())
        {
            tempList = storage.getMap().get(map.getKey());
            for(Secret li2: tempList)
            {
                if(secretId == li2.getUUID()) {
                    if (userId == li2.getuserId() || li2.getSharedUserList().contains(userId)) {
                        returnSecret = li2;
                        break;
                    }
                }
            }
        }
        return returnSecret;
    }

    public void shareSecret(String userId, UUID secretId, String targetUserId){
        Secret shareObj = readSecret(userId, secretId);
        if((shareObj!=null) && (targetUserId != shareObj.getuserId()) && (!(shareObj.getSharedUserList().contains(targetUserId))))
            shareObj.addToSharedUserList(targetUserId);
    }

    public void unshareSecret(String userId, UUID secretId, String targetUserId){
        Secret unShareObj = readSecret(userId, secretId);
        if(userId == unShareObj.getuserId() && (targetUserId != unShareObj.getuserId()))
            unShareObj.getSharedUserList().remove(targetUserId);

    }
}


