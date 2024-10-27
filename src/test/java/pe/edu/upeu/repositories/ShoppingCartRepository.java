package pe.edu.upeu.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.upeu.entities.Product;
import pe.edu.upeu.entities.ShoppingCart;
import pe.edu.upeu.security.entities.User;
import pe.edu.upeu.security.repositories.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        // Configurar y guardar un usuario de prueba
        user = new User();
        user.setId("client1");  // Verifica que el ID sea único
        user.setUserName("testUser");
        userRepository.save(user);

        // Configurar y guardar un producto de prueba
        product = new Product();
        product.setId("product1");  // Verifica que el ID sea único
        product.setName("Test Product");
        product.setCategory("Electronics");
        product.setPrice(100.00);
        productRepository.save(product);

        // Crear y guardar elementos en el carrito
        ShoppingCart cartItem1 = new ShoppingCart();
        cartItem1.setId("item1");  // Verifica que el ID sea único
        cartItem1.setClient(user);
        cartItem1.setProduct(product);
        cartItem1.setAmount(1);
        shoppingCartRepository.save(cartItem1);

        ShoppingCart cartItem2 = new ShoppingCart();
        cartItem2.setId("item2");  // Verifica que el ID sea único
        cartItem2.setClient(user);
        cartItem2.setProduct(product);
        cartItem2.setAmount(2);
        shoppingCartRepository.save(cartItem2);
    }

    @Test
    void testFindByClient_Id() {
        List<ShoppingCart> result = shoppingCartRepository.findByClient_Id("client1");
        assertEquals(2, result.size());
    }

    @Test
    void testFindByClient_UserName() {
        List<ShoppingCart> result = shoppingCartRepository.findByClient_UserName("testUser");
        assertEquals(2, result.size());
    }

    @Test
    void testCountByClient_Id() {
        Long count = shoppingCartRepository.countByClient_Id("client1");
        assertEquals(2, count);
    }

    @Test
    void testDeleteByClient_Id() {
        shoppingCartRepository.deleteByClient_Id("client1");
        List<ShoppingCart> result = shoppingCartRepository.findByClient_Id("client1");
        assertTrue(result.isEmpty());
    }
}
