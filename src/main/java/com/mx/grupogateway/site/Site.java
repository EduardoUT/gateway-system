/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.site;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import java.util.Objects;

/**
 *
 * @author eduar
 */
public class Site {

    private static final String DEFAULT_SITE_CODE = "No Site Code";
    private static final String DEFAULT_SITE_NAME = "No Site Name";
    private static final String DEFAULT_BIDDIG_AREA = "No Bidding Area";
    private static final Integer DEFAULT_SHIPMENT_NO = 0;
    private Long id;
    private String siteCode;
    private String siteName;
    private String biddigArea;
    private Integer shipmentNo;

    public Site() {
        siteCode = DEFAULT_SITE_CODE;
        siteName = DEFAULT_SITE_NAME;
        biddigArea = DEFAULT_BIDDIG_AREA;
        shipmentNo = DEFAULT_SHIPMENT_NO;
    }

    public Site(Long id) {
        this();
        validateId(id);
        this.id = id;
    }

    public Site(Long id, String siteCode, String siteName, String biddingArea, Integer shipmentNo) {
        validateSite(id, siteCode, siteName, biddingArea, shipmentNo);
        this.id = id;
        this.siteCode = siteCode;
        this.siteName = siteName;
        this.biddigArea = biddingArea;
        this.shipmentNo = shipmentNo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        validateId(id);
        this.id = id;
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
        validateSiteCode(siteCode);
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
        validateSiteName(siteName);
        this.siteName = siteName;
    }

    /**
     * @return the biddigArea
     */
    public String getBiddigArea() {
        return biddigArea;
    }

    /**
     * @param biddingArea the biddigArea to set
     */
    public void setBiddigArea(String biddingArea) {
        validateBiddingArea(biddingArea);
        this.biddigArea = biddingArea;
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
        validateShipmentNo(shipmentNo);
        this.shipmentNo = shipmentNo;
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (id <= 0 || id > Long.MAX_VALUE) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString());
        }
    }

    private void validateSiteCode(String siteCode) {
        if (siteCode == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (siteCode.isEmpty()) {
            this.siteCode = DEFAULT_SITE_CODE;
        }
    }

    private void validateSiteName(String siteName) {
        if (siteName == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (siteName.isEmpty()) {
            this.siteName = DEFAULT_SITE_NAME;
        }
    }

    private void validateBiddingArea(String biddingArea) {
        if (biddingArea == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (biddingArea.isEmpty()) {
            this.biddigArea = DEFAULT_BIDDIG_AREA;
        }
    }

    private void validateShipmentNo(Integer shipmentNo) {
        if (shipmentNo == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (shipmentNo < 0 || shipmentNo > Integer.MAX_VALUE) {
            this.shipmentNo = DEFAULT_SHIPMENT_NO;
        }
    }

    private void validateSite(Long id, String siteCode, String siteName,
            String biddingArea, Integer shipmentNo) {
        validateId(id);
        validateSiteCode(siteCode);
        validateSiteName(siteName);
        validateBiddingArea(biddingArea);
        validateShipmentNo(shipmentNo);
    }

    /**
     * Comparación por referencia, nombre de clase e identificadores únicos.
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
        Site otherSite = (Site) object;
        return Objects.equals(id, otherSite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
