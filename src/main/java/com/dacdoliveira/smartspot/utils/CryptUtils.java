package com.dacdoliveira.smartspot.utils;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtils
{
    private static final Logger log = LoggerFactory.getLogger(CryptUtils.class);

    public CryptUtils()
    {
    }

    public static String generateBCrypt(String value)
    {
        if (Objects.isNull(value))
        {
            return value;
        }
        else
        {
            log.info("Encrypting ...");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder.encode(value);
        }
    }
    
}
