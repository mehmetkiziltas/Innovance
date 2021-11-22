package com.innovance.entity;

public enum MoneyTypes {
    USD("Dollar", "$"),
    EUR("Euro", "£"),
    TL("Turkish Lira", "₺");

    private final String label;
    private final String symbol;

    MoneyTypes(final String label,
               final String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLabel() {
        return label;
    }
}
