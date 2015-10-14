package edu.sjsu.cmpe275.lab1;

/**
 * Created by Meghana on 10/1/2015.
 */

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        System.out.println("Unauthorized Exception!!!");
    }

    public UnauthorizedException(String arg0) {
        super(arg0);
        System.out.println("Unauthorized Exception: "+arg0);
    }

}
