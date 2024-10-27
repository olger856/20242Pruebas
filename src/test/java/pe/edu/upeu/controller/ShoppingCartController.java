package pe.edu.upeu.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pe.edu.upeu.controllers.ShoppingCartController;
import pe.edu.upeu.entities.Message;
import pe.edu.upeu.entities.ShoppingCart;
import pe.edu.upeu.services.ShoppingCartService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }

    @Test
    void testGetListByClient() throws Exception {
        List<ShoppingCart> cartList = Collections.singletonList(new ShoppingCart());
        when(shoppingCartService.getListByClient(anyString())).thenReturn(cartList);

        mockMvc.perform(get("/shoppingList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(cartList.size()));

        verify(shoppingCartService, times(1)).getListByClient(anyString());
    }

    @Test
    void testCountByClient() throws Exception {
        String clientId = "client123";
        Long count = 5L;
        when(shoppingCartService.getCountByClient(clientId)).thenReturn(count);

        mockMvc.perform(get("/shoppingList/count/{client_id}", clientId))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(count)));

        verify(shoppingCartService, times(1)).getCountByClient(clientId);
    }

    @Test
    void testAddProduct() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        String json = "{\"field\":\"value\"}";  // Reemplaza con los campos de ShoppingCart

        mockMvc.perform(post("/shoppingList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Producto agregado"));

        verify(shoppingCartService, times(1)).addProduct(any(ShoppingCart.class));
    }

    @Test
    void testRemoveProduct() throws Exception {
        String itemId = "item123";

        mockMvc.perform(delete("/shoppingList/clean/{item_id}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Eliminado"));

        verify(shoppingCartService, times(1)).removeProduct(itemId);
    }
}