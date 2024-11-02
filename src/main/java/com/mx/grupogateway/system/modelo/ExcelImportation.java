/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.util.List;

/**
 *
 * @author eduar
 */
public class ExcelImportation {

    private List<Project> projects;
    private List<Site> sites;
    private List<PurchaseOrder> purchaseOrders;
    private List<PurchaseOrderDetail> purchaseOrderDetails;

    /**
     * @return the projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * @return the sites
     */
    public List<Site> getSites() {
        return sites;
    }

    /**
     * @param sites the sites to set
     */
    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    /**
     * @return the purchaseOrders
     */
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    /**
     * @param purchaseOrders the purchaseOrders to set
     */
    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    /**
     * @return the purchaseOrderDetails
     */
    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    /**
     * @param purchaseOrderDetails the purchaseOrderDetails to set
     */
    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }
}
