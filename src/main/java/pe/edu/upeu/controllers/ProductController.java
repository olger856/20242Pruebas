package pe.edu.upeu.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.entities.Message;
import pe.edu.upeu.entities.Product;
import pe.edu.upeu.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product_id") String productId) {
        Optional<Product> productOptional = this.productService.getProductById(productId);
        if (productOptional.isEmpty())
            return new ResponseEntity<>(new Message("No encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<List<Product>> getBestProducts() {
        return new ResponseEntity<>(this.productService.getBestPriceProducts(), HttpStatus.OK);
    }

    @GetMapping("/related/{category}/{product_id}")
    public ResponseEntity<Object> getRelatedProduct(@PathVariable("category") String category, @PathVariable("product_id") String productId) {
        return new ResponseEntity<>(this.productService.getRelatedProducts(category, productId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        }
        Product savedProduct = this.productService.saveProduct(product); // Captura el producto guardado
        return new ResponseEntity<>(new Message("Producto '" + savedProduct.getName() + "' creado correctamente"), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message("Actualizado correctamente"), HttpStatus.OK);
    }
}
