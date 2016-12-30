package com.getninjas.test.jeffersonalvess.getninjastest;

public class Info {
    private String label;
    private String value;

    public Info(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {

        char [] charToRemove = {'[', ']', '"', '"'};
        for (char c: charToRemove) {
            value = value.replace(String.valueOf(c), "");
        }

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
