package com.example.restapimvc.serializer;

import com.example.restapimvc.domain.School;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SchoolSerializer extends JsonSerializer<School> {
    @Override
    public void serialize(School value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("schoolId");
        gen.writeNumber(value.getSchoolId());

        gen.writeFieldName("schoolName");
        gen.writeString(value.getSchoolName());

        gen.writeFieldName("regex");
        gen.writeString(value.getRegex());

        gen.writeEndObject();
    }
}
