package com.minsk24.dao.entitymanagerimpl;

import com.minsk24.bean.Advertisement;
import com.minsk24.dao.AdvertisementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AdvertisementDAOImpl implements AdvertisementDAO {
    @Autowired
    private EntityManager em;
    private String GET_ADVERTISEMENTS = "from Advertisement";

    @Override
    public Advertisement save(Advertisement advertisement) {
        em.persist(advertisement);
        return advertisement;
    }

    @Override
    public List<Advertisement> getAdvertisements() {
        List<Advertisement> advertisements = em.createQuery(GET_ADVERTISEMENTS).getResultList();
        return advertisements;
    }
}
