package io.basereport.handlers;

import java.util.Map;

public class Request {
    private Map<String, String>[] fieldMaps;

    public Request(Map<String, String>[] fieldMaps) {
        this.fieldMaps = fieldMaps;
    }

    public Map<String, String>[] getFieldMaps() {
        return fieldMaps;
    }

    public void setFieldMaps(Map<String, String>[] fieldMaps) {
        this.fieldMaps = fieldMaps;
    }

}
