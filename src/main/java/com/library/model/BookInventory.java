package com.library.model;

public class BookInventory {

    private int inventoryCount;
    private int borrowedCount;

    public BookInventory(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public boolean hasEnoughCopiesToBorrow() {
        return inventoryCount - borrowedCount > 0;
    }

    public boolean isReturnValid() {
        return borrowedCount > 0;
    }

    public void updateInventoryDuringReturn() {
        if(isReturnValid()) {
            borrowedCount--;
        }
    }

    public void updateInventory(int count) {
        this.inventoryCount += count;
    }

    public boolean isAvailable() {
        return inventoryCount > borrowedCount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }
}
