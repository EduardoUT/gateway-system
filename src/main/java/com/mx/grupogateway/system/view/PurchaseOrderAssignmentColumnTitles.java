/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.system.view;

/**
 *
 * @author eduar
 */
public enum PurchaseOrderAssignmentColumnTitles {
    ID_EMPLEADO("ID Empleado"),
    NOMBRE("Nombre"),
    APELLIDO_PATERNO("Apellido Paterno"),
    APELLIDO_MATERNO("Apellido Materno"),
    FECHA_ASIGNACION("Fecha Asignación"),
    ID_PROJECT("ID Project"),
    PO_NO("PO No"),
    IMPORTE("Importe"),
    TOTAL_PAGAR("Total"),
    STATUS("Status"),
    CUSTOMER("Customer"),
    PROJECT_NAME("Project Name"),
    PO_STATUS("PO Status"),
    PO_LINE_NO("PO Line No"),
    SITE_CODE("Site Code"),
    SITE_NAME("Site Name"),
    ITEM_DESC("Item Desc"),
    REQUESTED_QTY("Requested Qty"),
    DUE_QTY("Due Qty"),
    BILLED_QTY("Billed Qty"),
    UNIT_PRICE("Unit Price"),
    LINE_AMOUNT("Line Amount"),
    UNIT("Unit"),
    PAYMENT_TERMS("Payment Terms"),
    CATEGORY("Category"),
    PUBLISH_DATE("Publish Date");

    private final String columnTitle;

    private PurchaseOrderAssignmentColumnTitles(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    @Override
    public String toString() {
        return this.columnTitle;
    }
}
