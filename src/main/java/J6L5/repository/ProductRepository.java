package J6L5.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import J6L5.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager entityManager;

    public List<Product> index() {
        return entityManager.createNativeQuery("SELECT * FROM products", Product.class).getResultList();
    }

    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    public void save(Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
    }

    public void update(Product product) {

        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Product uProduct = entityManager.find(Product.class, product.getId());
        uProduct.setName(product.getName());
        uProduct.setCost(product.getCost());
        transaction.commit();
    }

    public boolean delete(int id) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        int result = entityManager.createNativeQuery("DELETE FROM products WHERE id = " + id).executeUpdate();
        transaction.commit();
        return result > 0;

    }

    public boolean isEmpty() {
        return entityManager.createNativeQuery("SELECT * FROM products", Product.class).getResultList() == null;
    }


}
