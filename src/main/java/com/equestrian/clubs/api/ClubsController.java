package com.equestrian.clubs.api;

import com.equestrian.clubs.model.Clubs;
import com.equestrian.clubs.repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.equestrian.clubs.service.ClubsService;

import java.util.List;

@Controller
public class ClubsController {
    @Autowired
    ClubsRepository clubsRepository;

    @GetMapping(value = "/clubs")

    public String clubs(Model model)
    {
        Iterable<Clubs> clubs =  clubsRepository.findAll();
        model.addAttribute("clubs",clubs);
        return "clubs";
    }
    @RequestMapping(value = "/db")
    public String db(Model model, @Param("keyword") String keyword) throws InterruptedException
    {
        Iterable<Clubs> clubs = clubsRepository.search(keyword);
        model.addAttribute("clubs",clubs);
        return "clubs";
    }
}
