package com.web2.kloun.model;

public interface ProductValores {

    double aplicarDesconto(double percentualDesconto);

    String calcularParcelas(int parcelas);

    void aumentarPreco(double percentualAumento);

    void aumentarPreco(double valorAumento, boolean aumentoAbsoluto);
}
