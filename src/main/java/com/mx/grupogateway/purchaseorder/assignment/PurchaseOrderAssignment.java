/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.employee.Employee;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author eduar
 */
public final class PurchaseOrderAssignment {

    private Employee employee;
    private PurchaseOrder purchaseOrder;
    private Timestamp assignmentDate;
    private BigDecimal amount;
    private BigDecimal totalPayment;
    private Boolean status = false;

    public PurchaseOrderAssignment() {
    }

    /**
     * Constructor para representar una asignación de proyecto-employee.
     *
     * @param employee
     * @param purchaseOrder
     * @param assignmentDate
     * @param amount
     * @param totalPayment
     * @param status
     */
    public PurchaseOrderAssignment(Employee employee, PurchaseOrder purchaseOrder,
            Timestamp assignmentDate, BigDecimal amount,
            BigDecimal totalPayment, String status) {
        this.employee = employee;
        this.purchaseOrder = purchaseOrder;
        this.assignmentDate = assignmentDate;
        this.amount = amount;
        this.totalPayment = totalPayment;
        this.status = getStatusAsBoolean(status);
    }

    /**
     * Constructor para almacenar una nueva asignación de proyecto asociado a un
 employee.
     *
     * @param purchaseOrder
     * @param employee Datos del employee.
     */
    public PurchaseOrderAssignment(Employee employee, PurchaseOrder purchaseOrder) {
        this.employee = employee;
        this.purchaseOrder = purchaseOrder;
        setAssignmentDate(assignmentDate);
        this.amount = new BigDecimal("0.0");
        this.totalPayment = new BigDecimal("0.0");
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
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
     * @return the assignmentDate
     */
    public Timestamp getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * @param assignmentDate the assignmentDate to set
     */
    public void setAssignmentDate(Timestamp assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the totalPayment
     */
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    /**
     * @param totalPayment the totalPayment to set
     */
    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
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
        this.assignmentDate = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        //yyyy-MM-dd HH:mm:ss
        return String.format("[ID_Empleado: %s | ID_Proyecto: %d | "
                + "Fecha Asignación: %s | Importe: %d | Total Pagar: %d | "
                + "Status: %s]",
                this.getEmployee().getId(),
                this.getPurchaseOrder().getProject().getId(),
                this.assignmentDate,
                this.amount,
                this.totalPayment, this.getStatus());
    }
}
