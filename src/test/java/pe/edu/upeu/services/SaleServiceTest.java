package pe.edu.upeu.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.entities.Detail;
import pe.edu.upeu.entities.Product;
import pe.edu.upeu.entities.Sale;
import pe.edu.upeu.entities.ShoppingCart;
import pe.edu.upeu.repositories.SaleRepository;
import pe.edu.upeu.security.entities.User;
import pe.edu.upeu.security.services.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;
    @Mock
    private UserService userService;
    @Mock
    private ShoppingCartService shoppingCartService;
    @Mock
    private DetailService detailService;

    @InjectMocks
    private SaleService saleService;

    private User testUser;
    private Product testProduct;
    private ShoppingCart shoppingCartItem;
    private Sale testSale;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuración de datos de prueba
        testUser = new User();
        testUser.setUserName("testUser");

        testProduct = new Product();
        testProduct.setId("product1");
        testProduct.setName("Test Product");
        testProduct.setPrice(100.00);

        shoppingCartItem = new ShoppingCart();
        shoppingCartItem.setProduct(testProduct);
        shoppingCartItem.setAmount(2);  // cantidad en el carrito

        testSale = new Sale(200.00, new Date(), testUser);  // precio total de 100 * 2
    }

    @Test
    void testCreateSale() {
        // Configurar comportamiento simulado
        when(userService.getByUserName("testUser")).thenReturn(Optional.of(testUser));
        when(shoppingCartService.getListByClient("testUser")).thenReturn(Arrays.asList(shoppingCartItem));
        when(saleRepository.save(any(Sale.class))).thenReturn(testSale);

        // Ejecutar el método que se está probando
        saleService.createSale("testUser");

        // Verificar que se guarde la venta y se cree el detalle
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(detailService, times(1)).createDetail(any(Detail.class));
        verify(shoppingCartService, times(1)).cleanShoppingCart(testUser.getId());
    }

    @Test
    void testGetSalesByClient() {
        when(saleRepository.findByClient_UserName("testUser")).thenReturn(Arrays.asList(testSale));

        List<Sale> result = saleService.getSalesByClient("testUser");

        assertEquals(1, result.size());
        assertEquals(testSale, result.get(0));
    }
}
