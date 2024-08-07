package com.web2.kloun;

public interface ProductValores {
    
    void aplicarDesconto(int percentualDesconto);
    void calcularParcelas(int parcelas);
    void aumentarPreco(double percentualAumento);
    void aumentarPreco(double valorAumento, boolean aumentoAbsoluto);
}
