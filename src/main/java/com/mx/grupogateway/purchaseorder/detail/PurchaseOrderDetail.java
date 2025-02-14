/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.detail;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author eduar
 */
public class PurchaseOrderDetail {

    private static final String DEFAULT_PO_STATUS = "No PO Status Info";
    private static final Long DEFAULT_ITEM_CODE = 0L;
    private static final String DEFAULT_ITEM_DESC = "No Item Desc";
    private static final String DEFAULT_PAYMENT_TERMS = "No Payment Terms";

    private String id;
    private String poStatus;
    private Long itemCode;
    private String itemDesc;
    private BigDecimal requestedQty;
    private BigDecimal lineAmount;
    private String paymentTerms;

    public PurchaseOrderDetail() {
        poStatus = DEFAULT_PO_STATUS;
        itemCode = DEFAULT_ITEM_CODE;
        itemDesc = DEFAULT_ITEM_DESC;
        requestedQty = new BigDecimal(BigInteger.ZERO);
        lineAmount = new BigDecimal("0.00");
        paymentTerms = DEFAULT_PAYMENT_TERMS;
    }

    public PurchaseOrderDetail(String id) {
        this();
        validateId(id);
        this.id = id;
    }

    public PurchaseOrderDetail(String id, String poStatus, Long itemCode,
            String itemDesc, BigDecimal requestedQty, BigDecimal lineAmount,
            String paymentTerms) {
        validatePurchaseOrderDetail(id, poStatus, itemCode, itemDesc, requestedQty, lineAmount, paymentTerms);
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
     * @param id the id to set
     */
    public void setId(String id) {
        validateId(id);
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
        validatPoStatus(poStatus);
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
        validateItemCode(itemCode);
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
        validateItemDesc(itemDesc);
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
        validateRequestedQty(requestedQty);
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
        validateLineAmount(lineAmount);
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
        validatePaymentTerms(paymentTerms);
        this.paymentTerms = paymentTerms;
    }

    private void validateId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException(NULL_VALUE_OR_EMPTY_MESSAGE.toString());
        }
    }

    private void validatPoStatus(String poStatus) {
        if (poStatus == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (poStatus.isEmpty()) {
            this.poStatus = DEFAULT_PO_STATUS;
        }
    }

    private void validateItemCode(Long itemCode) {
        if (itemCode == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (itemCode < 0 || itemCode > Long.MAX_VALUE) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString());
        }
    }

    private void validateItemDesc(String itemDesc) {
        if (itemDesc == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (itemDesc.isEmpty()) {
            this.itemDesc = DEFAULT_ITEM_DESC;
        }
    }

    private void validateRequestedQty(BigDecimal requestedQty) {
        if (requestedQty == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateLineAmount(BigDecimal lineAmount) {
        if (lineAmount == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validatePaymentTerms(String paymentTerms) {
        if (paymentTerms == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (paymentTerms.isEmpty()) {
            this.paymentTerms = DEFAULT_PAYMENT_TERMS;
        }
    }

    private void validatePurchaseOrderDetail(String id, String poStatus, Long itemCode,
            String itemDesc, BigDecimal requestedQty, BigDecimal lineAmount, String paymentTerms) {
        validateId(id);
        validatPoStatus(poStatus);
        validateItemCode(itemCode);
        validateItemDesc(itemDesc);
        validateRequestedQty(requestedQty);
        validateLineAmount(lineAmount);
        validatePaymentTerms(paymentTerms);
    }

    /**
     * Comparación de clase por medio de referencia, nombre de clase y
     * identificador único.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PurchaseOrderDetail otherPurchaseOrderDetail = (PurchaseOrderDetail) object;
        return Objects.equals(id, otherPurchaseOrderDetail.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
