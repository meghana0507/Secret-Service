package edu.sjsu.cmpe275.lab1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Meghana on 10/1/2015.
 */

@Aspect
public class LoggingAspect {
    private Log log = LogFactory.getLog(this.getClass());
    Storage storage;

    @Autowired
    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }

    @Before("execution(* SecretService.readSecret(..)) && args(userId,secretId)")
    public void logBeforeRead(String userId, UUID secretId)
    {
        log.info(userId + " reads the secret of ID " + secretId);
        System.out.println(log);


    }

    @Before("execution(* SecretService.shareSecret(..)) && args(userId, secretId, targetUserId)")
    public void logBeforeShare(String userId, UUID secretId, String targetUserId)
    {
        log.info(userId+" shares the secret of ID "+secretId+" with "+targetUserId);
        System.out.println(log);
        ArrayList<Secret> tempList;
        for(Map.Entry<String, ArrayList<Secret>> map: storage.getMap().entrySet())
        {
            tempList = storage.getMap().get(map.getKey());
            for(Secret li2: tempList)
            {
                if(secretId == li2.getUUID()) {
                    if (userId != li2.getuserId() && !(li2.getSharedUserList().contains(userId))) {
                        throw new UnauthorizedException("The person is trying to access the secret he neither owns nor shared with!");
                    }
                }
            }
        }
    }

    @Before("execution(* SecretService.unshareSecret(..)) && args(userId, secretId, targetUserId)")
    public void logBeforeUnshare(String userId, UUID secretId, String targetUserId)
    {
        log.info(userId+" unshares the secret of ID "+secretId+" with "+targetUserId);
        System.out.println(log);
        ArrayList<Secret> tempList;
        for(Map.Entry<String, ArrayList<Secret>> map: storage.getMap().entrySet())
        {
            tempList = storage.getMap().get(map.getKey());
            for(Secret li2: tempList)
            {
                if(secretId == li2.getUUID()) {
                    if (userId != li2.getuserId() && !(li2.getSharedUserList().contains(userId))) {
                        throw new UnauthorizedException("The person is trying to access the secret he neither owns nor shared with!");
                    }
                }
            }
        }
    }

    @After("execution(* SecretService.storeSecret(..)) && args(userId,secret)")
    public void logAfterStore(String userId, Secret secret)
    {
        log.info(userId+" creates a secret with ID "+secret.getUUID());
        System.out.println(log);
    }
}
