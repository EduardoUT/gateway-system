/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.employee.Employee;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Boolean status;

    public PurchaseOrderAssignment() {
        assignmentDate = Timestamp.valueOf(LocalDateTime.now());
        amount = new BigDecimal("0.0");
        totalPayment = new BigDecimal("0.0");
        status = false;
    }

    /**
     * Constructor para almacenar una nueva asignación de proyecto asociado a un
     * employee.
     *
     * @param purchaseOrder
     * @param employee Datos del employee.
     */
    public PurchaseOrderAssignment(Employee employee, PurchaseOrder purchaseOrder) {
        this();
        validateEmployee(employee);
        validatePurchaseOrder(purchaseOrder);
        this.employee = employee;
        this.purchaseOrder = purchaseOrder;
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
            BigDecimal totalPayment, Boolean status) {
        validateEmployee(employee, purchaseOrder, assignmentDate, amount, totalPayment, status);
        this.employee = employee;
        this.purchaseOrder = purchaseOrder;
        this.assignmentDate = assignmentDate;
        this.amount = amount;
        this.totalPayment = totalPayment;
        this.status = status;
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
        validateEmployee(employee);
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
        validatePurchaseOrder(purchaseOrder);
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
        validateAssignmentDate(assignmentDate);
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
        validateAmount(amount);
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
        validateTotalPayment(totalPayment);
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
        validateStatus(status);
        this.status = status;
    }

    /**
     * Crea un Timestamp con la fecha y tiempo actual.
     */
    public void setLocalDateTime() {
        assignmentDate = new Timestamp(new Date().getTime());
    }

    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validatePurchaseOrder(PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateAssignmentDate(Timestamp assignmentDate) {
        if (assignmentDate == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateTotalPayment(BigDecimal totalPayment) {
        if (totalPayment == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateStatus(Boolean status) {
        if (status == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateEmployee(Employee employee, PurchaseOrder purchaseOrder,
            Timestamp assignmentDate, BigDecimal amount, BigDecimal totalPayment,
            Boolean status) {
        validateEmployee(employee);
        validatePurchaseOrder(purchaseOrder);
        validateAssignmentDate(assignmentDate);
        validateAmount(amount);
        validateTotalPayment(totalPayment);
        validateStatus(status);
    }

    @Override
    public String toString() {
        return String.format("[ID_Empleado: %s | ID_Proyecto: %d | "
                + "Fecha Asignación: %s | Importe: %d | Total Pagar: %d | "
                + "Status: %s]",
                employee.getId(),
                purchaseOrder.getProject().getId(),
                assignmentDate,
                amount,
                totalPayment,
                status
        );
    }
}
