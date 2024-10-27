package pe.edu.upeu.controller;

import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.edu.upeu.controllers.ProductController;
import pe.edu.upeu.entities.Message;
import pe.edu.upeu.entities.Product;
import pe.edu.upeu.services.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        // Arrange
        Product product = new Product();
        product.setId("1");
        product.setName("Test Product");
        when(productService.getProductById("1")).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Object> response = productController.getProductById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void getProductById_ShouldReturnNotFound_WhenDoesNotExist() {
        // Arrange
        when(productService.getProductById("2")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> response = productController.getProductById("2");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Message);
        assertEquals("No encontrado", ((Message) response.getBody()).getInfoMessage());
    }

    @Test
    void createProduct_ShouldReturnCreatedMessage_WhenValid() {
        // Arrange
        Product product = new Product();
        product.setName("New Product");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(productService).saveProduct(any(Product.class));

        // Act
        ResponseEntity<Message> response = productController.createProduct(product, bindingResult);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Cambié a CREATED
        assertEquals("Producto 'New Product' creado correctamente", response.getBody().getInfoMessage());
    }


    @Test
    void createProduct_ShouldReturnBadRequest_WhenInvalid() {
        // Arrange
        Product product = new Product();

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        ResponseEntity<Message> response = productController.createProduct(product, bindingResult);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Revise los campos", response.getBody().getInfoMessage());
    }
}
