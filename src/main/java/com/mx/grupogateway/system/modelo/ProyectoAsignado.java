/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author eduar
 */
public final class ProyectoAsignado extends Project {

    private Timestamp fechaAsignacion;
    private BigDecimal importe;
    private BigDecimal totalPagar;
    private Boolean status; //<------
    private Empleado empleado;

    /**
     * Constructor para representar este objeto en una lista.
     *
     * @param fechaAsignacion
     * @param importe
     * @param totalPagar
     * @param status
     * @param proyecto
     * @param empleado
     */
    public ProyectoAsignado(Timestamp fechaAsignacion, BigDecimal importe,
            BigDecimal totalPagar, String status, Project proyecto,
            Empleado empleado) {
        super(
                proyecto.getIdProyecto(),
                proyecto.getCustomer(),
                proyecto.getProjectName(),
                proyecto.getPoNo(),
                proyecto.getPoStatus(),
                proyecto.getPoLineNo(),
                proyecto.getSiteCode(),
                proyecto.getSiteName(),
                proyecto.getItemDesc(),
                proyecto.getRequestedQty(),
                proyecto.getDueQty(),
                proyecto.getBilledQty(),
                proyecto.getUnitPrice(),
                proyecto.getLineAmount(),
                proyecto.getUnit(),
                proyecto.getPaymentTerms(),
                proyecto.getCategory(),
                proyecto.getPublishDate()
        );
        this.empleado = empleado;
        this.fechaAsignacion = fechaAsignacion;
        this.importe = importe;
        this.totalPagar = totalPagar;
        this.status = getStatusAsBoolean(status);
    }

    /**
     * Constructor para almacenar una nueva asignación de proyecto asociado a un
     * empleado.
     *
     * @param idProyecto
     * @param poNo Clave del proyecto, puede ser una o más.
     * @param empleado Datos del empleado.
     */
    public ProyectoAsignado(Long idProyecto, String poNo, Empleado empleado) {
        super(idProyecto, poNo);
        this.empleado = empleado;
        setFechaAsignacion(fechaAsignacion);
        this.importe = new BigDecimal("0.0");
        this.totalPagar = new BigDecimal("0.0");
        this.status = false;
    }

    /**
     * @return the fechaAsignacion
     */
    public Timestamp getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion the fechaAsignacion to set
     */
    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * @return the usuario
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Tranforma el status a binario, para ser almacenado en la BD.
     *
     * @return 1 o 0
     */
    public String getStatusAsBinary() {
        return (this.status ? "1" : "0");
    }

    /**
     * Toma el estatus y lo define por defecto con 1 "false", esto al ser creada
     * una nueva asignación.
     *
     * @param status
     * @return
     */
    public boolean getStatusAsBoolean(String status) {
        return status.equals("1");
    }

    /**
     * Crea un Timestamp con la fecha y tiempo actual.
     */
    public void setLocalDateTime() {
        this.fechaAsignacion = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        //yyyy-MM-dd HH:mm:ss
        return String.format("[ID_Empleado: %s | ID_Proyecto: %d | "
                + "Fecha Asignación: %s | Importe: %d | Total Pagar: %d | "
                + "Status: %s]",
                this.getEmpleado().getIdEmpleado(),
                this.getIdProyecto(),
                this.fechaAsignacion,
                this.importe,
                this.totalPagar,
                this.status);
    }
}
