package com.minsk24.dao.impl;

import com.minsk24.bean.Advertisement;
import com.minsk24.dao.AdvertisementDAO;
import com.minsk24.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdvertisementDAOImpl implements AdvertisementDAO {
    private String GET_ADVERTISEMENTS = "from Advertisement";

    @Override
    public Advertisement save(Advertisement advertisement) {
        Session session = SessionFactoryUtil.getSession();
        session.save(advertisement);
        session.close();
        return advertisement;
    }

    @Override
    public List<Advertisement> getAdvertisements() {
        Session session = SessionFactoryUtil.getSession();
        List<Advertisement> advertisements = session.createQuery(GET_ADVERTISEMENTS).list();
        session.close();
        return advertisements;
    }
}
