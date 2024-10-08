package com.web2.kloun.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web2.kloun.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ProductRepository {
    @PersistenceContext

    public EntityManager em;

    public List<Product> findAll() {
        String sql = "SELECT * FROM produto";
        Query q = em.createNativeQuery(sql, Product.class);
        List<Product> products = q.getResultList();
        return products;
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM produto WHERE id = :id";
        Query q = em.createNativeQuery(sql, Product.class);
        q.setParameter("id", id);
        Product product = (Product) q.getSingleResult();
        return product;
    }

    public List<Product> findByName(String nome) {
        String sql = "SELECT * FROM produto WHERE nome LIKE :search ";
        Query q = em.createNativeQuery(sql, Product.class);
        q.setParameter("search", "%" + nome + "%");
        List<Product> products = q.getResultList();
        return products;
    }

    @Transactional
    public void save(Product product) {
        String sql = "INSERT INTO produto (nome, descricao, preco, categoria, imagem) VALUES (:nome, :descricao, :preco, :categoria, :imagem)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("nome", product.getNome());
        query.setParameter("descricao", product.getDescricao());
        query.setParameter("preco", product.getPreco());
        query.setParameter("imagem", product.getImagem());
        query.setParameter("categoria", product.getCategoria());
        query.executeUpdate();
    }

    @Transactional
    public void update(Product product) {
        String sql = "UPDATE produto SET nome = :nome, descricao = :descricao, preco = :preco, categoria = :categoria, imagem = :imagem WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", product.getId());
        query.setParameter("nome", product.getNome());
        query.setParameter("descricao", product.getDescricao());
        query.setParameter("preco", product.getPreco());
        query.setParameter("imagem", product.getImagem());
        query.setParameter("categoria", product.getCategoria());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String sql = "DELETE FROM produto WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}