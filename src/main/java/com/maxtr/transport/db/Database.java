package com.maxtr.transport.db;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class Database {
    private static final LinkedList<TransportTime> transportDB = new LinkedList<TransportTime>();

    public void add(TransportTime transportTime) {
        transportDB.add(transportTime);
        transportDB.sort(Comparator.comparing(TransportTime::getDate));
    }

    public LinkedList<TransportTime> getAll() {
        return transportDB;
    }

    public LinkedList<TransportTime> getAll(TransportType transportType) {
        LinkedList<TransportTime> filtered = new LinkedList<>();

        for (TransportTime transportTime : transportDB) {
            if (transportTime.getType() == transportType) {
                filtered.add(transportTime);
            }
        }

        return filtered;
    }

    public LinkedList<TransportTime> filter(String raceName) {
        LinkedList<TransportTime> filtered = new LinkedList<>();

        for (TransportTime transportTime : transportDB) {
            if (transportTime.getRaceName().toLowerCase().contains(raceName.toLowerCase())) {
                filtered.add(transportTime);
            }
        }

        return filtered;
    }

    public void remove(int id) {
        transportDB.remove(id);
    }
}
