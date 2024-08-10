package com.web2.kloun.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Product extends Item implements ProductValores{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private double preco;

    @Column(name = "categoria") 
    private String categoria;

    @Column(name = "imagem")
    private String imagem;
    
    public Product(){}

    public Product(int id, String nome, String descricao, double preco, String categoria, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public double formatarValor(double valor) {
        BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.UP);
        return bd.doubleValue();
    }

    public double aplicarDesconto (double percentualDesconto) {
        double desconto = preco * (percentualDesconto / 100.0);
        double precoComDesconto = getPreco() - desconto;
        return formatarValor(precoComDesconto);
    }

    public String calcularParcelas(int parcelas) {
        double valorParcelado = (preco/parcelas);
        return "ou " + parcelas + "x de R$" + String.format("%.2f", valorParcelado);
    }


     // Sobrecarga 1: Aplica aumento percentual no preço
    public void aumentarPreco(double percentualAumento) {
        this.preco += this.preco * (percentualAumento / 100);
    }

    // Sobrecarga 2: Aplica aumento absoluto no preço
    public void aumentarPreco(double valorAumento, boolean aumentoAbsoluto) {
        if (aumentoAbsoluto) {
            this.preco += valorAumento;
        } else {
            aumentarPreco(valorAumento); // Usa o método de aumento percentual
        }
    }


}
