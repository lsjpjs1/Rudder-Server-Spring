package com.example.restapimvc.util;

import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;

import java.lang.reflect.Method;

public interface EnumEntityConvertable<E extends Enum & EnumEntityConvertable>{

    //Enum -> DB에 들어갈 문자열
    String getEntityValue();

    //문자열에 해당하는 Enum 값이 맞는지 여부
    Boolean isTargetEnum(String s);


    /**
     * @param typeToken E의 타입 토큰
     * @param s s-> Enum값으로 변환할 문자열
     * @param <E> Enum과 EnumEntityConvertable의 하위타입인 타입 매개변수
     * @return s에 해당하는 Enum값
     * @throws Exception
     * @throws CustomException 404, OS_TYPE_NOT_FOUND 문자열에 해당하는 Enum 값이 없을 때
     */
    static <E extends Enum & EnumEntityConvertable> E getEnumValue(Class<E> typeToken,String s) throws Exception {
        Method valuesMethod = typeToken.getDeclaredMethod("values", null);
        E[] values = (E[]) valuesMethod.invoke(null);
        for(E enumValue : values){
            if (enumValue.isTargetEnum(s)) {
                return enumValue;
            }
        }
        throw new CustomException(ErrorCode.OS_TYPE_NOT_FOUND);
    }

}
