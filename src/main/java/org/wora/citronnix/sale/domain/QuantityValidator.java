package org.wora.citronnix.sale.domain;

public class QuantityValidator {
    private final Double quantiteTotale;

    public QuantityValidator(Double quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public void validateQuantity(Double quantityRequested) {
        if (quantiteTotale == null || quantiteTotale <= 0) {
            throw new IllegalArgumentException("La récolte n'a plus de quantité disponible.");
        }

        if (quantityRequested > quantiteTotale) {
            throw new IllegalArgumentException("Quantité insuffisante dans la récolte.");
        }
    }

    public Double updateQuantityAfterSale(Double quantitySold) {
        return quantiteTotale - quantitySold;
    }
}

