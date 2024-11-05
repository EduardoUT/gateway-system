/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase contenedor de listados de las clase modelo que representan un project
 * en conjunto en el archivo Excel.
 *
 * @author eduar
 */
public class DataImport {

    private final List<Project> projects;
    private final List<Site> sites;
    private final List<PurchaseOrder> purchaseOrders;
    private final List<PurchaseOrderDetail> purchaseOrderDetails;

    public DataImport() {
        this.projects = new ArrayList<>();
        this.sites = new ArrayList<>();
        this.purchaseOrders = new ArrayList<>();
        this.purchaseOrderDetails = new ArrayList<>();
    }

    /**
     * @return the projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @param project the projects to set
     */
    public void addProject(Project project) {
        this.projects.add(project);
    }

    /**
     * @return the sites
     */
    public List<Site> getSites() {
        return sites;
    }

    /**
     * @param site the sites to set
     */
    public void addSite(Site site) {
        this.sites.add(site);
    }

    /**
     * @return the purchaseOrders
     */
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    /**
     * @param purchaseOrder the purchaseOrders to set
     */
    public void addPurchaseOrders(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.add(purchaseOrder);
    }

    /**
     * @return the purchaseOrderDetails
     */
    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    /**
     * @param purchaseOrderDetail the purchaseOrderDetails to set
     */
    public void addPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetails.add(purchaseOrderDetail);
    }
}
