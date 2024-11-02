/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.math.BigDecimal;

/**
 *
 * @author eduar
 */
public class PurchaseOrder {

    private PurchaseOrderDetail purchaseOrderDetail;
    private Project project;
    private Integer poLineNo;
    private String dueQty;
    private String billedQty;
    private String unit;
    private BigDecimal unitPrice;

    public PurchaseOrder(PurchaseOrderDetail purchaseOrderDetail,
            Project project, Integer poLineNo, String dueQty, String billedQty,
            String unit, BigDecimal unitPrice) {
        this.purchaseOrderDetail = purchaseOrderDetail;
        this.project = project;
        this.poLineNo = poLineNo;
        this.dueQty = dueQty;
        this.billedQty = billedQty;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    /**
     * @return the purchaseOrderDetail
     */
    public PurchaseOrderDetail getPurchaseOrderDetail() {
        return purchaseOrderDetail;
    }

    /**
     * @param purchaseOrderDetail the purchaseOrderDetail to set
     */
    public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetail = purchaseOrderDetail;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the poLineNo
     */
    public Integer getPoLineNo() {
        return poLineNo;
    }

    /**
     * @param poLineNo the poLineNo to set
     */
    public void setPoLineNo(Integer poLineNo) {
        this.poLineNo = poLineNo;
    }

    /**
     * @return the dueQty
     */
    public String getDueQty() {
        return dueQty;
    }

    /**
     * @param dueQty the dueQty to set
     */
    public void setDueQty(String dueQty) {
        this.dueQty = dueQty;
    }

    /**
     * @return the billedQty
     */
    public String getBilledQty() {
        return billedQty;
    }

    /**
     * @param billedQty the billedQty to set
     */
    public void setBilledQty(String billedQty) {
        this.billedQty = billedQty;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
