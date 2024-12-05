/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author eduar
 */
public class PurchaseOrderDetail {

    private String purchaseOrderDetailIdentifier;
    private String poStatus;
    private Long itemCode;
    private String itemDesc;
    private String requestedQty;
    private BigDecimal lineAmount;
    private String paymentTerms;

    public PurchaseOrderDetail() {
        this.purchaseOrderDetailIdentifier = "0000000000-00";
        this.poStatus = "NEW";
        this.itemCode = Long.MAX_VALUE;
        this.itemDesc = "No Item Desc";
        this.requestedQty = "0";
        this.lineAmount = new BigDecimal("0.00");
        this.paymentTerms = "No Payment Terms";
    }

    public PurchaseOrderDetail(String poNo) {
        this();
        this.purchaseOrderDetailIdentifier = poNo;
    }

    public PurchaseOrderDetail(String poNo, String poStatus, Long itemCode,
            String itemDesc, String requestedQty, BigDecimal lineAmount,
            String paymentTerms) {
        this.purchaseOrderDetailIdentifier = poNo;
        this.poStatus = poStatus;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.requestedQty = requestedQty;
        this.lineAmount = lineAmount;
        this.paymentTerms = paymentTerms;
    }

    /**
     * @return the purchaseOrderDetailIdentifier
     */
    public String getPurchaseOrderDetailIdentifier() {
        return purchaseOrderDetailIdentifier;
    }

    /**
     * @param purchaseOrderDetailIdentifier the purchaseOrderDetailIdentifier to
     * set
     */
    public void setPurchaseOrderDetailIdentifier(String purchaseOrderDetailIdentifier) {
        this.purchaseOrderDetailIdentifier = purchaseOrderDetailIdentifier;
    }

    /**
     * @return the poStatus
     */
    public String getPoStatus() {
        return poStatus;
    }

    /**
     * @param poStatus the poStatus to set
     */
    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    /**
     * @return the itemCode
     */
    public Long getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc the itemDesc to set
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * @return the requestedQty
     */
    public String getRequestedQty() {
        return requestedQty;
    }

    /**
     * @param requestedQty the requestedQty to set
     */
    public void setRequestedQty(String requestedQty) {
        this.requestedQty = requestedQty;
    }

    /**
     * @return the lineAmount
     */
    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    /**
     * @param lineAmount the lineAmount to set
     */
    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    /**
     * @return the paymentTerms
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * @param paymentTerms the paymentTerms to set
     */
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    /**
     *
     * @param purchaseOrderDetail
     * @return
     */
    @Override
    public boolean equals(Object purchaseOrderDetail) {
        if (this == purchaseOrderDetail) {
            return true;
        }
        if (purchaseOrderDetail == null
                || getClass() != purchaseOrderDetail.getClass()) {
            return false;
        }
        PurchaseOrderDetail otherPurchaseOrderDetail
                = (PurchaseOrderDetail) purchaseOrderDetail;
        if (purchaseOrderDetailIdentifier.equals(
                otherPurchaseOrderDetail.getPurchaseOrderDetailIdentifier())) {
            return true;
        }
        return purchaseOrderDetail instanceof PurchaseOrderDetail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseOrderDetailIdentifier);
    }
}
