/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.excel;

import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.site.Site;
import com.mx.grupogateway.project.Project;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase contenedor de listados de las clase modelo que representan un project
 * en conjunto en el archivo Excel.
 *
 * @author eduar
 */
public class ExcelDataCell {

    private final Set<Project> projects;
    private final Set<Site> sites;
    private final Set<PurchaseOrder> purchaseOrders;
    private final Set<PurchaseOrderDetail> purchaseOrderDetails;

    public ExcelDataCell() {
        projects = new HashSet<>();
        sites = new HashSet<>();
        purchaseOrders = new HashSet<>();
        purchaseOrderDetails = new HashSet<>();
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
        projects.add(project);
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
        sites.add(site);
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
        purchaseOrders.add(purchaseOrder);
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
        purchaseOrderDetails.add(purchaseOrderDetail);
    }

    public int totalDataCell() {
        int siteSize = sites.size();
        int projectsSize = projects.size();
        int purchasOrderDetailSize = purchaseOrderDetails.size();
        int purchaseOrderSize = purchaseOrders.size();
        return siteSize + projectsSize + purchasOrderDetailSize + purchaseOrderSize;
    }
}
