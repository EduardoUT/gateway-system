/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author eduar
 */
public class ProjectData {

    private String projectId;
    private String projectCode;
    private String projectName;
    private String customer;
    private String poStatus;
    private String poNo;
    private Integer poLineNo;
    private Integer shipmentNo;
    private String siteCode;
    private String siteName;
    private String itemCode;
    private String itemDesc;
    private Integer requestedQty;
    private BigDecimal dueQty;
    private BigDecimal billedQty;
    private BigDecimal unitPrice;
    private BigDecimal lineAmount;
    private String unit;
    private String paymentTerms;
    private String category;
    private String biddingArea;
    private Date publishDate;
    private Empleado empleado;
    private ProjectAssignations projectAssignations;

    public ProjectData(Integer Id, String projectCode, String projectName,
            String customer, String poStatus, String poNo, Integer poLineNo,
            Integer shipmentNo, String siteCode, String siteName,
            Integer itemCode, String itemDesc, Double requestedQty,
            BigDecimal dueQty, BigDecimal billedQty, BigDecimal unitPrice,
            BigDecimal lineAmount, String unit, String paymentTerms,
            String category, String biddingArea, Date publishDate,
            Empleado empleado, ProjectAssignations projectAssignations) {
        
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * @param projectCode the projectCode to set
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
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
     * @return the shipmentNo
     */
    public Integer getShipmentNo() {
        return shipmentNo;
    }

    /**
     * @param shipmentNo the shipmentNo to set
     */
    public void setShipmentNo(Integer shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    /**
     * @return the siteCode
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * @param siteCode the siteCode to set
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * @param siteName the siteName to set
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
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
    public Integer getRequestedQty() {
        return requestedQty;
    }

    /**
     * @param requestedQty the requestedQty to set
     */
    public void setRequestedQty(Integer requestedQty) {
        this.requestedQty = requestedQty;
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
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the biddingArea
     */
    public String getBiddingArea() {
        return biddingArea;
    }

    /**
     * @param biddingArea the biddingArea to set
     */
    public void setBiddingArea(String biddingArea) {
        this.biddingArea = biddingArea;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the usuario
     */
    public Empleado getUsuario() {
        return empleado;
    }

    /**
     * @return the projectAssignations
     */
    public ProjectAssignations getProjectAssignations() {
        return projectAssignations;
    }

    /**
     * @return the statusFacturacion
     */
    public String getStatusFacturacion() {
        return statusFacturacion;
    }

    /**
     * @param statusFacturacion the statusFacturacion to set
     */
    public void setStatusFacturacion(String statusFacturacion) {
        this.statusFacturacion = statusFacturacion;
    }
}
