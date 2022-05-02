package com.example.restapimvc.scraper.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.Objects;

@Builder
@ToString
@Getter
public class FindTarget implements CsvConvertible{
    private String companyName;
    private String jobTitle;
    private String uploadAt;
    private String url;
    private String jobType;
    private String expireIn;
    private String location;
    private String salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTarget that = (FindTarget) o;
        return companyName.equals(that.companyName) && jobTitle.equals(that.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, jobTitle);
    }

    @Override
    public String toCsvString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for(Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldValue = "";
            try {
                fieldValue = String.valueOf(field.get(this));
            } catch (IllegalAccessException e){}
            stringBuilder.append(",");
            stringBuilder.append(emptyStringIfNull(fieldValue));

        }

        return stringBuilder.toString();
    }

    public String getSearchSummary() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(companyName);
        stringBuilder.append(" ");
        stringBuilder.append(jobTitle);
        stringBuilder.append(" ");
        stringBuilder.append(jobType);
        stringBuilder.append(" ");
        stringBuilder.append(location);
        stringBuilder.append(" ");
        return stringBuilder.toString();

    }

    public String emptyStringIfNull(String s){
        if(s==null){
            return "";
        }else {
            return s.replaceAll(",","，");
        }
    }
}
