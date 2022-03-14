package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "title1", 100, "author1", 400, 2020);
    private Book book2 = new Book(2, "title1", 110, "author2", 300, 2021);
    private Smartphone smartphone1 = new Smartphone(3, "phone1", 2000, "manufacturer1");
    private Smartphone smartphone2 = new Smartphone(4, "phone2", 2000, "manufacturer1");
    private Product product1 = new Product(5, "item2", 500);

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }

    @Test
    public void removeByIdIfExist() {
        Product[] expected = new Product[]{smartphone2, smartphone1, book1};
        repository.removeById(2);
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdIfNotExist() {
        assertThrows(NotFoundException.class, () -> repository.removeById(5));
    }
}