/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.employee.Empleado;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author eduar
 */
public final class PurchaseOrderAssignment {

    private Empleado empleado;
    private PurchaseOrder purchaseOrder;
    private Timestamp fechaAsignacion;
    private BigDecimal importe;
    private BigDecimal totalPagar;
    private Boolean status = false;

    public PurchaseOrderAssignment() {
    }

    /**
     * Constructor para representar una asignación de proyecto-empleado.
     *
     * @param empleado
     * @param purchaseOrder
     * @param fechaAsignacion
     * @param importe
     * @param totalPagar
     * @param status
     */
    public PurchaseOrderAssignment(Empleado empleado, PurchaseOrder purchaseOrder,
            Timestamp fechaAsignacion, BigDecimal importe,
            BigDecimal totalPagar, String status) {
        this.empleado = empleado;
        this.purchaseOrder = purchaseOrder;
        this.fechaAsignacion = fechaAsignacion;
        this.importe = importe;
        this.totalPagar = totalPagar;
        this.status = getStatusAsBoolean(status);
    }

    /**
     * Constructor para almacenar una nueva asignación de proyecto asociado a un
     * empleado.
     *
     * @param purchaseOrder
     * @param empleado Datos del empleado.
     */
    public PurchaseOrderAssignment(Empleado empleado, PurchaseOrder purchaseOrder) {
        this.empleado = empleado;
        this.purchaseOrder = purchaseOrder;
        setFechaAsignacion(fechaAsignacion);
        this.importe = new BigDecimal("0.0");
        this.totalPagar = new BigDecimal("0.0");
    }

    /**
     * @return the empleado
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
     * @return the purchaseOrder
     */
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Tranforma el status a binario, para ser almacenado en la BD.
     *
     * @return 1 o 0
     */
    public String getStatusAsBinary() {
        return (this.getStatus() ? "1" : "0");
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
                this.getPurchaseOrder().getProject().getProjectId(),
                this.fechaAsignacion,
                this.importe,
                this.totalPagar, this.getStatus());
    }
}
