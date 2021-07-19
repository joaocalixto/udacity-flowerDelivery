package com.udacity.flowerDelivery.repository;

import com.udacity.flowerDelivery.repository.entities.Delivery;
import com.udacity.flowerDelivery.repository.entities.Plant;
import com.udacity.flowerDelivery.repository.projection.RecipientAndPriceDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }

    public RecipientAndPriceDTO getNameAndPrice(Long deliveryId){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPriceDTO> criteria = builder.createQuery(RecipientAndPriceDTO.class);
        Root<Delivery> root = criteria.from(Delivery.class);

        Path<String> name = root.get("name");
        Path<BigDecimal> price = root.get("price");

        criteria.select(builder.construct(
                            RecipientAndPriceDTO.class,
                                root.get("delivery")
                                        .get("name"),
                                builder.sum(root.get("price"))))
                    .where(builder.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public List<Delivery> findDeliveryByName(String name){

        String deliveryFindByName = "Delivery.findByName";

        List<Delivery> listDelivery = entityManager.createNamedQuery(deliveryFindByName, Delivery.class)
                .setParameter("name", name)
                .getResultList();

        return listDelivery;
    }

    public Delivery find(Long deliveryId){
        return entityManager.find(Delivery.class, deliveryId);
    }

    public void delete(Long deliveryId){
        Delivery delivery = entityManager.find(Delivery.class, deliveryId);
        entityManager.remove(delivery);

    }
    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }
}
