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
public class ProjectAssignations extends ProjectData {

    private Integer idAsignacion;
    private Date fechaAsignacion;
    private Integer id;
    private Empleado empleado;
    private String ordenCompraDt;
    private BigDecimal importe;
    private BigDecimal totalPagar;
    private String statusFacturacion;

    public ProjectAssignations(Integer Id, String projectCode,
            String projectName, String customer, String poStatus, String poNo,
            Integer poLineNo, Integer shipmentNo, String siteCode,
            String siteName, Integer itemCode, String itemDesc,
            Double requestedQty, BigDecimal dueQty, BigDecimal billedQty,
            BigDecimal unitPrice, BigDecimal lineAmount, String unit,
            String paymentTerms, String category, String biddingArea,
            Date publishDate, Empleado empleado) {
       
    }

    /**
     * @return the idAsignacion
     */
    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    /**
     * @param idAsignacion the idAsignacion to set
     */
    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    /**
     * @return the fechaAsignacion
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion the fechaAsignacion to set
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ordenCompraDt
     */
    public String getOrdenCompraDt() {
        return ordenCompraDt;
    }

    /**
     * @param ordenCompraDt the ordenCompraDt to set
     */
    public void setOrdenCompraDt(String ordenCompraDt) {
        this.ordenCompraDt = ordenCompraDt;
    }

    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the totalPagar
     */
    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    /**
     * @param totalPagar the totalPagar to set
     */
    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
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
