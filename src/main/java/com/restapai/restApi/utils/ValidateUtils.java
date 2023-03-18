package com.restapai.restApi.utils;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Builder
public class ValidateUtils {
    private Object value;
    private boolean required;
    private Integer maxLength;
    private String fieldName;
    private String regex;
    private boolean onlyNumber;
    private boolean isInteger;
    private Long max;
    private Long min;
    private String message;


    private  final String ONLY_NUMBER = "[0-9]+";
    public Map validate(){
        Map<String, String> errors= new HashMap<>();
        //Check field is required
        if (required && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            errors.put(fieldName, fieldName +"Deve ser preenchido!");
        }

        //check max length of field
        if (!ObjectUtils.isEmpty(maxLength)
                && !ObjectUtils.isEmpty(value)
                && value.toString().length()>maxLength.intValue()
                && !ObjectUtils.isEmpty(fieldName)){
            errors.put(fieldName, fieldName +" Este campo deve ter 0 " + maxLength+ "e caracter");
        }

        // check field name is only number
        if (onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher= patternOnlyNumber.matcher(value.toString());
            if (!matcher.matches()){
                errors.put(fieldName, fieldName + "Deve ser preenchido apenas por numeros!");
            }
        }

        //Check fiel name is Integer
        if (isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            try {
                Integer.parseInt(value.toString());
            }catch (Exception e){
                errors.put(fieldName, fieldName + "Deve ter numeros inteiros ");
            }
        }


        //check field name is integer
        if (!ObjectUtils.isEmpty(max) && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(min)&& !ObjectUtils.isEmpty(fieldName)){
            try {
                Long valueLong = Long.valueOf(value.toString());
                if (valueLong< min || valueLong>max){
                    errors.put(fieldName, fieldName + "Deve estar no intervalo de " + max + "e " + min);
                }
            }catch (Exception e){
                errors.put(fieldName, fieldName + "Tipo inválido");
            }
        }

return errors;
    }
}
