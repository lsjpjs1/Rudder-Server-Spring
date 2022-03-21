package com.example.restapimvc.util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;

public class RandomNumber {
    private static final Random random = new Random();
    private static final int VERIFICATION_CODE_SIZE = 6;

    public static String generateVerificationCode() {
        String verificationCode = "";
        for(int i=0; i<VERIFICATION_CODE_SIZE; i++) {
            verificationCode = verificationCode + random.nextInt(9);
        }
        return verificationCode;
    }
}
