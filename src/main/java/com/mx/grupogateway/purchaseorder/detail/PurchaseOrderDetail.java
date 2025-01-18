/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.detail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author eduar
 */
public class PurchaseOrderDetail {

    private String id;
    private String poStatus;
    private Long itemCode;
    private String itemDesc;
    private BigDecimal requestedQty;
    private BigDecimal lineAmount;
    private String paymentTerms;

    public PurchaseOrderDetail() {
        this.id = "0000000000-00";
        this.poStatus = "NEW";
        this.itemCode = Long.MAX_VALUE;
        this.itemDesc = "No Item Desc";
        this.requestedQty = new BigDecimal(BigInteger.ZERO);
        this.lineAmount = new BigDecimal("0.00");
        this.paymentTerms = "No Payment Terms";
    }

    public PurchaseOrderDetail(String poNo) {
        this();
        this.id = poNo;
    }

    public PurchaseOrderDetail(String id, String poStatus, Long itemCode,
            String itemDesc, BigDecimal requestedQty, BigDecimal lineAmount,
            String paymentTerms) {
        this.id = id;
        this.poStatus = poStatus;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.requestedQty = requestedQty;
        this.lineAmount = lineAmount;
        this.paymentTerms = paymentTerms;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to
 set
     */
    public void setId(String id) {
        this.id = id;
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
    public BigDecimal getRequestedQty() {
        return requestedQty;
    }

    /**
     * @param requestedQty the requestedQty to set
     */
    public void setRequestedQty(BigDecimal requestedQty) {
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
        if (id.equals(otherPurchaseOrderDetail.getId())) {
            return true;
        }
        return purchaseOrderDetail instanceof PurchaseOrderDetail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
