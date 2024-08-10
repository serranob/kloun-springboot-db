package com.web2.kloun.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web2.kloun.model.Product;
import com.web2.kloun.repository.ProductRepository;
import com.web2.kloun.service.FileStorageService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping("/home")
    public String home () {
        return "index.html";
    }
    
    @GetMapping("/shop")
    public String shop(Model model){
        List<Product> productsList = productRepository.findAll();
        model.addAttribute("productsList", productsList);
        return "/produto/shop";
    }
    
    
    @GetMapping("/produtos")
    public String listarProdutos(Model model){
        List<Product> productsList = productRepository.findAll();
        model.addAttribute("productsList", productsList);
        return "produto/dashboard";
    }

    @GetMapping("/detalhesProduto")
    public String detalhesProduto (Model model, @RequestParam ("id") int id){
        Product findProduct = productRepository.findById(id);
        model.addAttribute("prod", findProduct);
        return "produto/ver_produto01";
    }

    @GetMapping("/cadastrarProduto")
    public String cadastrarProduto(){
        return "produto/cadastrar_produto";
    }

    @PostMapping("/armazenarProduto")
    public String armazenarProduto(Model model, @RequestParam String nome, @RequestParam String descricao, @RequestParam double preco, @RequestParam String categoria, @RequestParam MultipartFile imagem) {
        Product saveProduct = new Product();
        if(!imagem.isEmpty()) {
            String imageName = fileStorageService.store(imagem);
            saveProduct.setImagem(imageName);
        }
        saveProduct.setNome(nome);
        saveProduct.setDescricao(descricao);
        saveProduct.setPreco(preco);
        saveProduct.setCategoria(categoria);
        productRepository.save(saveProduct);
        return "redirect:/produtos";
    }

    @GetMapping("/atualizarProduto")
    public String atualizarProduto (Model model, @RequestParam int id){
        Product findProduct = productRepository.findById(id);
        model.addAttribute("prod", findProduct);
        return "/produto/atualizar_produto";
    }

    @PostMapping("/salvarAtualizacaoProduto")
    public String salvarAtualizacaoProduto (Model model, @RequestParam String nome, @RequestParam String descricao, @RequestParam double preco, @RequestParam String categoria, @RequestParam MultipartFile imagem, @RequestParam int id){
        Product updateProduct = new Product();
        if(!imagem.isEmpty()) {
            String imageName = fileStorageService.store(imagem);
            updateProduct.setImagem(imageName);
        }
        updateProduct.setId(id);
        updateProduct.setNome(nome);
        updateProduct.setDescricao(descricao);
        updateProduct.setPreco(preco);
        updateProduct.setCategoria(categoria);

        productRepository.update(updateProduct);
        return "redirect:/produtos";
    }

    @GetMapping("/deletarProduto")
    public String deletarProduto(@RequestParam ("id") int id) {
        productRepository.delete(id);
        return "redirect:/produtos";
    }
    
    @PostMapping("/buscarProdutos")
    public String buscarProdutos(Model model, @RequestParam String nome) {
        List<Product> productsList = productRepository.findByName(nome);
        model.addAttribute("produtos", productsList);
        return "/produto/buscar_produtos.html";
    }
    
    
}
