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
public class ProyectoAsignacion extends Proyecto {

    private Date fechaAsignacion;
    private String ordenCompraDt;
    private BigDecimal importe;
    private BigDecimal totalPagar;
    private final Empleado empleado;

    public ProyectoAsignacion(Date fechaAsignacion, String ordenCompraDt,
            BigDecimal importe, BigDecimal totalPagar, Proyecto proyecto,
            Empleado empleado) {
        super(proyecto.getIdProyecto(), proyecto.getProjectCode(),
                proyecto.getProjectName(), proyecto.getCustomer(),
                proyecto.getPoStatus(), proyecto.getPoNo(),
                proyecto.getPoLineNo(), proyecto.getShipmentNo(),
                proyecto.getSiteCode(), proyecto.getSiteName(),
                proyecto.getItemCode(), proyecto.getItemDesc(),
                proyecto.getRequestedQty(), proyecto.getDueQty(),
                proyecto.getBilledQty(), proyecto.getUnitPrice(),
                proyecto.getLineAmount(), proyecto.getUnit(),
                proyecto.getPaymentTerms(), proyecto.getCategory(),
                proyecto.getBiddingArea(), proyecto.getPublishDate());
        this.fechaAsignacion = fechaAsignacion;
        this.ordenCompraDt = ordenCompraDt;
        this.importe = importe;
        this.totalPagar = totalPagar;
        this.empleado = empleado;
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
     * @return the usuario
     */
    public Empleado getEmpleado() {
        return empleado;
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
}
