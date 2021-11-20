package com.maxtr.transport.db;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransportTime {
    private TransportType type;
    private String raceName;
    private Date date;

    public TransportTime(TransportType type, String raceName, Date date) {
        this.type = type;
        this.raceName = raceName;
        this.date = date;
    }

    public String getRaceName() {
        return raceName;
    }

    public TransportType getType() {
        return type;
    }

    public String getTypeName() {
        String name;
        switch (type) {
            case SHIP:
                name = "Корабль";
                break;
            case AIRPLANE:
                name = "Самолет";
                break;
            case TRAIN:
                name = "Поезд";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getFormatDate() {
        Format formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return formatter.format(date);
    }
}

