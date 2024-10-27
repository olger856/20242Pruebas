package pe.edu.upeu.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.entities.ShoppingCart;
import pe.edu.upeu.repositories.ShoppingCartRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;


    @Test
    void testGetListByClient() {
        String clientUsername = "testUser";
        List<ShoppingCart> expectedCartList = Collections.singletonList(new ShoppingCart());

        when(shoppingCartRepository.findByClient_UserName(clientUsername)).thenReturn(expectedCartList);

        List<ShoppingCart> result = shoppingCartService.getListByClient(clientUsername);

        assertEquals(expectedCartList, result);
        verify(shoppingCartRepository, times(1)).findByClient_UserName(clientUsername);
    }

    @Test
    void testAddProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCartService.addProduct(shoppingCart);

        verify(shoppingCartRepository, times(1)).save(shoppingCart);
    }

    @Test
    void testRemoveProduct() {
        String itemId = "item123";

        shoppingCartService.removeProduct(itemId);

        verify(shoppingCartRepository, times(1)).deleteById(itemId);
    }

    @Test
    void testGetCountByClient() {
        String clientId = "client123";
        Long expectedCount = 5L;

        when(shoppingCartRepository.countByClient_Id(clientId)).thenReturn(expectedCount);

        Long result = shoppingCartService.getCountByClient(clientId);

        assertEquals(expectedCount, result);
        verify(shoppingCartRepository, times(1)).countByClient_Id(clientId);
    }
}
