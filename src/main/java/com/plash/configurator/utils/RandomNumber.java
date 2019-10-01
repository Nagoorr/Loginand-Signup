package com.plash.configurator.utils;

import java.util.UUID;

public class RandomNumber
{
    public static String uniqueRandomIdentifier()
    {
        UUID idOne = UUID.randomUUID();
        String temp = String.valueOf(idOne);
        return temp;
    }
}
