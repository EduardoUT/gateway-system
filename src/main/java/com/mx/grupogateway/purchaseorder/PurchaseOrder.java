/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder;

import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author eduar
 */
public class PurchaseOrder implements Serializable {

    private static final long SERIAL_VERSION_UUID = 1L;
    private PurchaseOrderDetail purchaseOrderDetail;
    private Project project;
    private Integer poLineNo;
    private BigDecimal dueQty;
    private BigDecimal billedQty;
    private String unit;
    private BigDecimal unitPrice;

    public PurchaseOrder() {
        this.purchaseOrderDetail = new PurchaseOrderDetail();
        this.project = new Project();
        this.poLineNo = 0;
        this.dueQty = new BigDecimal("0.00");
        this.billedQty = new BigDecimal("0.00");
        this.unit = "SITE";
        this.unitPrice = new BigDecimal("0.00");
    }

    public PurchaseOrder(PurchaseOrderDetail purchaseOrderDetail, Project project) {
        this();
        this.purchaseOrderDetail = purchaseOrderDetail;
        this.project = project;
    }

    public PurchaseOrder(PurchaseOrderDetail purchaseOrderDetail,
            Project project, Integer poLineNo, BigDecimal dueQty, BigDecimal billedQty,
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
     * @return the SERIAL_VERSION_UUID
     */
    public static long getSERIAL_VERSION_UUID() {
        return SERIAL_VERSION_UUID;
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
    public BigDecimal getDueQty() {
        return dueQty;
    }

    /**
     * @param dueQty the dueQty to set
     */
    public void setDueQty(BigDecimal dueQty) {
        this.dueQty = dueQty;
    }

    /**
     * @return the billedQty
     */
    public BigDecimal getBilledQty() {
        return billedQty;
    }

    /**
     * @param billedQty the billedQty to set
     */
    public void setBilledQty(BigDecimal billedQty) {
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

    @Override
    public boolean equals(Object purchaseOrder) {
        if (this == purchaseOrder) {
            return true;
        }
        if (purchaseOrder == null || getClass() != purchaseOrder.getClass()) {
            return false;
        }
        PurchaseOrder otherPurchaseOrder = (PurchaseOrder) purchaseOrder;
        boolean isSamePurchasOrderIdentifier = purchaseOrderDetail
                .getId()
                .equals(otherPurchaseOrder
                        .getPurchaseOrderDetail()
                        .getId()
                );
        boolean isSameProjectId = project
                .getId()
                .equals(otherPurchaseOrder
                        .getProject()
                        .getId()
                );
        if (isSamePurchasOrderIdentifier && isSameProjectId) {
            return true;
        }
        return purchaseOrder instanceof PurchaseOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                purchaseOrderDetail.getId(),
                project.getId()
        );
    }
}
