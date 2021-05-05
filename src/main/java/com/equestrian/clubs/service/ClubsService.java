package com.equestrian.clubs.service;

import com.equestrian.clubs.model.Clubs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubsService {
    public void save(Clubs clubs);  //сохраняем
    public void clearall(); // удаление
    public boolean isExist(String clubsTitle); //проверяем на наличие
    public List<Clubs> getAllClubs();  //сбор данных
}
