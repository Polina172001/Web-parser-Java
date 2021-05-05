package com.equestrian.clubs.service;

import com.equestrian.clubs.model.Clubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.equestrian.clubs.repository.ClubsRepository;

import java.util.List;

@Service
public class ClubsServiceImpl implements ClubsService{

    @Autowired
    ClubsRepository repository;

    @Override
    public void save(Clubs clubs) {
        repository.save(clubs);
    }

    @Override
    public void clearall(){repository.deleteAll();}

    @Override
    public boolean isExist(String clubsTitle) {
        List<Clubs> clubs = repository.findAll();
        for (Clubs n: clubs) {
            if (n.getTitle().equals(clubsTitle)) {
                return true;
            }
        }
        return false;
    }


    @Override
    @Cacheable(value = "cache")
    public List<Clubs> getAllClubs() {
        return repository.findAll();
    }
}
