/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.system.view;

/**
 *
 * @author eduar
 */
public enum PurchaseOrderColumnTitles {
    ID_PROJECT("ID"),
    PROJECT_CODE("Project Code"),
    PROJECT_NAME("Project Name"),
    CUSTOMER("Customer"),
    PO_STATUS("PO Status"),
    PO_NO("PO No"),
    PO_LINE_NO("PO Line No"),
    SHIPMENT_NO("Shipment No"),
    SITE_CODE("Site Code"),
    SITE_NAME("Site Name"),
    ITEM_CODE("Item Code"),
    ITEM_DESCRIPTION("Item Description"),
    REQUESTED_QTY("Requested Qty"),
    DUE_QTY("Due Qty"),
    BILLED_QTY("Billed Qty"),
    UNIT_PRICE("Unit Price"),
    LINE_AMOUNT("Line Amount"),
    UNIT("Unit"),
    PAYMENT_TERMS("Payment Terms"),
    CATEGORY("Category"),
    BIDING_AREA("Biding Area"),
    PUBLISH_DATE("Publish Date");

    private final String columnTitle;

    private PurchaseOrderColumnTitles(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    @Override
    public String toString() {
        return this.columnTitle;
    }
}
