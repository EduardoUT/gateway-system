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
public class PurchaseOrderDetail {

    private String poNo;
    private String poStatus;
    private Long itemCode;
    private String itemDesc;
    private String requestedQty;
    private BigDecimal lineAmount;
    private String paymentTerms;

    public PurchaseOrderDetail(String poNo, String poStatus, Long itemCode,
            String itemDesc, String requestedQty, BigDecimal lineAmount,
            String paymentTerms) {
        this.poNo = poNo;
        this.poStatus = poStatus;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.requestedQty = requestedQty;
        this.lineAmount = lineAmount;
        this.paymentTerms = paymentTerms;
    }

    /**
     * @return the poNo
     */
    public String getPoNo() {
        return poNo;
    }

    /**
     * @param poNo the poNo to set
     */
    public void setPoNo(String poNo) {
        this.poNo = poNo;
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
}
