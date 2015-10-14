package edu.sjsu.cmpe275.lab1;

/**
 * Created by Meghana on 10/1/2015.
 */

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.UUID;

public class SecretServiceTest {

    SecretService secretService;
    ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        try {
            applicationContext = new FileSystemXmlApplicationContext("file:src/main/resources/beans.xml");
            secretService = (SecretService) applicationContext.getBean("secretServiceBean");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test(expected = UnauthorizedException.class)
    public void testA() {
        System.out.println("testA");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.readSecret("Bob", aliceSecret);
    }

    @Test
    public void testB(){
        System.out.println("testB");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.readSecret("Bob",aliceSecret);
    }

    @Test
    public void testC(){
        System.out.println("testC");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", aliceSecret, "Carl");
        secretService.readSecret("Carl",aliceSecret);
    }

    @Test(expected = UnauthorizedException.class)
    public void testD(){
        System.out.println("testD");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        UUID carlSecret = secretService.storeSecret("Carl", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", carlSecret, "Alice");
    }

    @Test(expected = UnauthorizedException.class)
    public void testE(){
        System.out.println("testE");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", aliceSecret, "Carl");
        secretService.unshareSecret("Alice",aliceSecret,"Carl");
        secretService.readSecret("Carl", aliceSecret);
    }

    @Test(expected = UnauthorizedException.class)
    public void testF(){
        System.out.println("testF");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Alice", aliceSecret, "Carl");
        secretService.shareSecret("Carl", aliceSecret, "Bob");
        secretService.unshareSecret("Alice",aliceSecret, "Bob");
        secretService.readSecret("Bob",aliceSecret);
    }

    @Test
    public void testG(){
        System.out.println("testG");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", aliceSecret, "Carl");
        secretService.unshareSecret("Bob", aliceSecret,"Carl");
        secretService.readSecret("Carl",aliceSecret);
    }

    @Test(expected = UnauthorizedException.class)
    public void testH(){
        System.out.println("testH");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.unshareSecret("Carl", aliceSecret, "Bob");
    }

    @Test(expected = UnauthorizedException.class)
    public void testI(){
        System.out.println("testI");
        UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
        secretService.shareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", aliceSecret, "Carl");
        secretService.unshareSecret("Alice", aliceSecret, "Bob");
        secretService.shareSecret("Bob", aliceSecret, "Carl");
    }

    @Test
    public void testJ(){
        System.out.println("testJ");
        Secret sec = new Secret();
        secretService.storeSecret("Alice", sec);
        secretService.storeSecret("Alice", sec);
    }
}
