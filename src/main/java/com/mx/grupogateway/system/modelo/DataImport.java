/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase contenedor de listados de las clase modelo que representan un project
 * en conjunto en el archivo Excel.
 *
 * @author eduar
 */
public class DataImport {

    private final Set<Project> projects;
    private final Set<Site> sites;
    private final Set<PurchaseOrder> purchaseOrders;
    private final Set<PurchaseOrderDetail> purchaseOrderDetails;

    public DataImport() {
        this.projects = new HashSet<>();
        this.sites = new HashSet<>();
        this.purchaseOrders = new HashSet<>();
        this.purchaseOrderDetails = new HashSet<>();
    }

    /**
     * @return the projects
     */
    public Set<Project> getProjects() {
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
    public Set<Site> getSites() {
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
    public Set<PurchaseOrder> getPurchaseOrders() {
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
    public Set<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    /**
     * @param purchaseOrderDetail the purchaseOrderDetails to set
     */
    public void addPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetails.add(purchaseOrderDetail);
    }
}
