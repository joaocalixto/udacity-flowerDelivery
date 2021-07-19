package com.udacity.flowerDelivery.service;

import com.udacity.flowerDelivery.repository.DeliveryRepository;
import com.udacity.flowerDelivery.repository.DeliveryRepositoryJPA;
import com.udacity.flowerDelivery.repository.entities.Delivery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeliveryService {
   @Autowired
   DeliveryRepository deliveryRepository;

   @Autowired
    DeliveryRepositoryJPA deliveryRepositoryJPA;

   public Delivery update(Delivery delivery){
       Delivery save = null;
       if(delivery.getId() == null){
           save = deliveryRepositoryJPA.save(delivery);
       }else{
           Delivery existing = deliveryRepositoryJPA.findById(delivery.getId()).get();
           copyNonNullProperties(delivery, existing);
           save = deliveryRepositoryJPA.save(existing);
       }

       return save;
   }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

   public Long save(Delivery delivery) {
       delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
       deliveryRepository.persist(delivery);
       return delivery.getId();
   }

   public List<Delivery> findByName(String name){
       return deliveryRepository.findDeliveryByName(name);
   }
}