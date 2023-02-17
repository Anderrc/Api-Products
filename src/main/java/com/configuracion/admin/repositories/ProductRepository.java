package com.configuracion.admin.repositories;

import com.configuracion.admin.models.ProductsModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<ProductsModel, Long> {

    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value="update tb_products set stock = :stock where id = :id",  nativeQuery = true)
    void updateStock(@Param("id") Long id, @Param("stock") Integer stock);


    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value="update tb_products set state = 0 where id = :id",  nativeQuery = true)
    void changeState(@Param("id") Long id);

    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "SELECT p.id, p.name,p.category, p.creation_date, p.price, p.ref, p.stock, p.weight, p.state, sum(i.cant) FROM tb_products p inner join tb_invoice_details i on p.id = i.id_product group by p.id ORDER BY SUM(i.cant) DESC LIMIT 1", nativeQuery = true)
    Iterable<ProductsModel> findBestSellingProduct();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "SELECT * FROM tb_products order by stock desc limit 1", nativeQuery = true)
    Iterable<ProductsModel> findProductMoreStock();
}
