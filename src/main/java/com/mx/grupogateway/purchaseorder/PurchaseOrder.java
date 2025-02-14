/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase PurchaseOrder con clase interna estática PurchaseOrderBuilder para su
 * construcción de una nueva instancia acorde al contexto de las propiedades que
 * el usuario requiera.
 *
 * Toda la lógica de validación se centraliza en el PurchaseOrderBuilder.
 *
 * Ejemplo de creación de nueva instancia de PurchaseOrder:
 *
 * PurchaseOrder purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
 * .withProject(someProject).build();
 *
 * @see PurchaseOrderBuilder
 * @author eduar
 */
public class PurchaseOrder {

    private static final String DEFAULT_UNIT = "No Unit Info";
    private final PurchaseOrderDetail purchaseOrderDetail;
    private final Project project;
    private final Integer poLineNo;
    private final BigDecimal dueQty;
    private final BigDecimal billedQty;
    private final String unit;
    private final BigDecimal unitPrice;

    private PurchaseOrder(PurchaseOrderBuilder purchaseOrderBuilder) {
        purchaseOrderDetail = purchaseOrderBuilder.purchaseOrderDetail;
        project = purchaseOrderBuilder.project;
        poLineNo = purchaseOrderBuilder.poLineNo;
        dueQty = purchaseOrderBuilder.dueQty;
        billedQty = purchaseOrderBuilder.billedQty;
        unit = purchaseOrderBuilder.unit;
        unitPrice = purchaseOrderBuilder.unitPrice;
    }

    /**
     * Clase Builder para la construcción de un objeto PurchaseOrder.
     */
    public static class PurchaseOrderBuilder {

        private PurchaseOrderDetail purchaseOrderDetail;
        private Project project;
        private Integer poLineNo;
        private BigDecimal dueQty;
        private BigDecimal billedQty;
        private String unit;
        private BigDecimal unitPrice;

        public PurchaseOrderBuilder withPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
            validatePurchaseOrderDetail(purchaseOrderDetail);
            this.purchaseOrderDetail = purchaseOrderDetail;
            return this;
        }

        public PurchaseOrderBuilder withProject(Project project) {
            validateProject(project);
            this.project = project;
            return this;
        }

        public PurchaseOrderBuilder withPoLineNo(Integer poLineNo) {
            validatePoLineNo(poLineNo);
            this.poLineNo = poLineNo;
            return this;
        }

        public PurchaseOrderBuilder withDueQty(BigDecimal dueQty) {
            validateDueQty(dueQty);
            this.dueQty = dueQty;
            return this;
        }

        public PurchaseOrderBuilder withBilledQty(BigDecimal billedQty) {
            validateBilledQty(billedQty);
            this.billedQty = billedQty;
            return this;
        }

        public PurchaseOrderBuilder withUnit(String unit) {
            validateUnit(unit);
            this.unit = unit;
            return this;
        }

        public PurchaseOrderBuilder withUnitPrice(BigDecimal unitPrice) {
            validateUnitPrice(unitPrice);
            this.unitPrice = unitPrice;
            return this;
        }

        public PurchaseOrder build() {
            return new PurchaseOrder(this);
        }

        private void validatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
            if (purchaseOrderDetail == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
        }

        private void validateProject(Project project) {
            if (project == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
        }

        private void validatePoLineNo(Integer poLineNo) {
            if (poLineNo == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
            if (poLineNo < 0 || poLineNo > Integer.MAX_VALUE) {
                throw new IllegalArgumentException(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString());
            }
        }

        private void validateDueQty(BigDecimal dueQty) {
            if (dueQty == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
        }

        private void validateBilledQty(BigDecimal billedQty) {
            if (billedQty == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
        }

        private void validateUnit(String unit) {
            if (unit == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
            if (unit.isEmpty()) {
                this.unit = DEFAULT_UNIT;
            }
        }

        private void validateUnitPrice(BigDecimal unitPrice) {
            if (unitPrice == null) {
                throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
            }
        }
    }

    /**
     * @return the purchaseOrderDetail
     */
    public PurchaseOrderDetail getPurchaseOrderDetail() {
        return purchaseOrderDetail;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @return the poLineNo
     */
    public Integer getPoLineNo() {
        return poLineNo;
    }

    /**
     * @return the dueQty
     */
    public BigDecimal getDueQty() {
        return dueQty;
    }

    /**
     * @return the billedQty
     */
    public BigDecimal getBilledQty() {
        return billedQty;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Comparación por referencia, nombre de clase y composición por
     * identificadores únicos entre PurchaseOrderDetail y Project.
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
        PurchaseOrder otherPurchaseOrder = (PurchaseOrder) object;
        boolean isSamePurchasOrderId = Objects.equals(
                purchaseOrderDetail.getId(),
                otherPurchaseOrder.getPurchaseOrderDetail().getId()
        );
        boolean isSameProjectId = Objects.equals(
                project.getId(), otherPurchaseOrder.getProject().getId()
        );
        return isSamePurchasOrderId && isSameProjectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                purchaseOrderDetail.getId(),
                project.getId()
        );
    }
}
