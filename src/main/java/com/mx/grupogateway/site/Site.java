/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.site;

import java.util.Objects;

/**
 *
 * @author eduar
 */
public class Site {

    private Long siteId;
    private String siteCode;
    private String siteName;
    private String biddigArea;
    private Integer shipmentNo;

    public Site() {
        this.siteId = Long.MAX_VALUE;
        this.siteCode = "No Site";
        this.siteName = "No Name Site";
        this.biddigArea = "No Bidding Area";
        this.shipmentNo = Integer.MAX_VALUE;
    }

    public Site(Long siteId) {
        this();
        this.siteId = siteId;
    }

    public Site(Long siteId, String siteCode, String siteName, String biddingArea, Integer shiplmentNo) {
        this.siteId = siteId;
        this.siteCode = siteCode;
        this.siteName = siteName;
        this.biddigArea = biddingArea;
        this.shipmentNo = shiplmentNo;
    }

    /**
     * @return the siteId
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
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
     * @return the biddigArea
     */
    public String getBiddigArea() {
        return biddigArea;
    }

    /**
     * @param biddigArea the biddigArea to set
     */
    public void setBiddigArea(String biddigArea) {
        this.biddigArea = biddigArea;
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
     *
     * @param site
     * @return
     */
    @Override
    public boolean equals(Object site) {
        if (this == site) {
            return true;
        }
        if (site == null || getClass() != site.getClass()) {
            return false;
        }
        Site otherSite = (Site) site;
        if (siteId.equals(otherSite.getSiteId())) {
            return true;
        }
        return site instanceof Site;
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId);
    }

}
