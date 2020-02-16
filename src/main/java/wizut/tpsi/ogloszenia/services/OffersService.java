/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;





/**
 *
 * @author frymu
 */
@Service
@Transactional
public class OffersService {
    
    @PersistenceContext
    private EntityManager em;
    
    public CarManufacturer getCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }
    public CarModel getModel(int id) {
        return em.find(CarModel.class, id);
    }
    public List<Offer> getOffers()
    {
        String jpql = "select o from Offer o order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }
    public Offer getOffer(int id)
    {
        return em.find(Offer.class,id);
    }
    public List<CarManufacturer> getcarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }
    public List<BodyStyle> getBodyTypes()
    {
        String jpql = "select bt from BodyType bt order by bt.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }
    public List<FuelType> getFuelTypes()
    {
        String jpql = "select ft from FuelType ft order by ft.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }       
    public List<CarModel> getCarModels(int id)
    {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", id);
        
        return query.getResultList();
    }
    public List<CarModel> getCarModels()
    {
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        
        return query.getResultList();
    }
    public List<Offer> getOffersByModel(int id)
    {
        String jpql = "select o from Offer o where o.model.id = :id order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", id);
        
        return query.getResultList();
    }
    public List<Offer> getOffersByMAnufacturer(int manufacturerId)
    {
        String jpql = "select o from Offer o where o.model.manufacturer.id = :id order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);
        
        return query.getResultList();
    }
    public Offer createOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }
}