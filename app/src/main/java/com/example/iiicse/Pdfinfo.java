package com.example.iiicse;

public class Pdfinfo {

    String by;
    String link;
    String unit;
    String filename;

    public Pdfinfo( ) {
    }

    public Pdfinfo(String by, String link, String unit, String filename) {
        this.by = by;
        this.link = link;
        this.unit = unit;
        this.filename = filename;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
