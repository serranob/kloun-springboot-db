package com.web2.kloun;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/produtos")
    public String listarProdutos(Model model){

        List<Product> productsList = productRepository.findAll();
        model.addAttribute("productsList", productsList);
        return "dashboard.html";
    }

    @GetMapping("/detalhesProduto")
    public String detalhesProduto (Model model, @RequestParam ("id") int id){
        Product findProduct = productRepository.findById(id);
        model.addAttribute("prod", findProduct);
        return "ver_produto01.html";
    }

    @GetMapping("/cadastrarProduto")
    public String cadastrarProduto(){
        return "cadastrar_produto.html";
    }

    @PostMapping("/armazenarProduto")
    public String armazenarProduto(Model model, @RequestParam int id, @RequestParam String nome, @RequestParam String descricao, @RequestParam double preco, @RequestParam String categoria, @RequestParam String imagem) {
        Product saveProduct = new Product();
        saveProduct.setNome(nome);
        saveProduct.setDescricao(descricao);
        saveProduct.setPreco(preco);
        saveProduct.setCategoria(descricao);
        saveProduct.setImagem(imagem);

        productRepository.save(saveProduct);
        return "redirect:/produtos";
    }

    @GetMapping("/atualizarProduto")
    public String atualizarProduto (Model model, @RequestParam ("id") int id){
        Product findProduct = productRepository.findById(id);
        model.addAttribute("prod", findProduct);
        return "atualizar_produto.html";
    }

    @PostMapping("/salvarAtualizacaoProduto")
    public String salvarAtualizacaoProduto (Model model, @RequestParam String nome, @RequestParam String descricao, @RequestParam double preco, @RequestParam String categoria, @RequestParam String imagem){
        Product updateProduct = new Product();
        updateProduct.setNome(nome);
        updateProduct.setDescricao(descricao);
        updateProduct.setPreco(preco);
        updateProduct.setCategoria(categoria);
        updateProduct.setImagem(imagem);

        productRepository.update(updateProduct);
        return "redirect:/produtos";
    }
    
}
