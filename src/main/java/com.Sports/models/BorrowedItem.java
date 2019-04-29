package com.Sports.models;

import java.time.LocalDate;

public class BorrowedItem {

    private int itemId;
    private LocalDate date;

    public BorrowedItem(int itemId, LocalDate date) {
        this.itemId = itemId;
        this.date = date;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
