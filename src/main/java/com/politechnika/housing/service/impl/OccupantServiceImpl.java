package com.politechnika.housing.service.impl;

import com.politechnika.housing.config.MailConfig;
import com.politechnika.housing.config.WebSecurityTokens;
import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Authorities;
import com.politechnika.housing.model.Occupant;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.model.User;
import com.politechnika.housing.repository.OccupantRepository;
import com.politechnika.housing.service.inf.AuthoritiesService;
import com.politechnika.housing.service.inf.OccupantService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;
    @Autowired
    private PremisesService premisesService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthoritiesService authoritiesService;
    @Override
    public int save(Occupant occupant) {

        Authorities authorities = new Authorities();
        authorities.setUsername(occupant.getFirstname()+occupant.getLastname());
        authorities.setAuthority(WebSecurityTokens.ROLE_OCCUPANT);
        MailConfig.configure();

        String pass = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String token = UUID.randomUUID().toString();


        User user = new User();
        user.setUsername(occupant.getFirstname()+occupant.getLastname());
        user.setPassword(passwordEncoder.encode(pass));
        user.setActivationToken(token);
        occupant.setUser(user);
        authoritiesService.save(authorities);

        int id =occupantRepository.saveAndFlush(occupant).getId();

        MailConfig.sendMail("dev312.test@gmail.com",occupant.getFirstname(),occupant.getLastname(),pass,token);

        return id;

    }

    @Override
    public Occupant get(int id) throws OccupantNotFoundException {
        return occupantRepository.findById(id).orElseThrow(() -> new OccupantNotFoundException("User id:" + id));
    }

    @Override
    public int update(Occupant occupant) {
        return occupantRepository.saveAndFlush(occupant).getId();
    }

    @Override
    public void delete(int id) {
        occupantRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addPremisesToOccupant(int premisesId, int occupantId) {
        Occupant occupant = null;
        Premises premises = null;

        try {
            occupant = get(occupantId);
        } catch (OccupantNotFoundException e) {
            e.printStackTrace();
        }

        try {
            premises = premisesService.get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (occupant != null && premises !=null && premises.getOccupant() == null) {
            Set<Premises> premisesSet = occupant.getPremises();
            premisesSet.add(premises);
            premises.setOccupant(occupant);
            occupant.setPremises(premisesSet);
            occupantRepository.save(occupant);
        }
    }

    @Override
    public void deletePremisesFromOccupant(int premisesId, int occupantId) {
        Occupant occupant = null;

        try {
            occupant = get(occupantId);
        } catch (OccupantNotFoundException e) {
            e.printStackTrace();
        }

        Set<Premises> premisesSet = occupant.getPremises();

        premisesSet.removeIf(premises -> premises.getId() == premisesId);

        occupant.setPremises(premisesSet);
        occupantRepository.save(occupant);

    }

    @Override
    public List<Occupant> getAll() {
        return occupantRepository.findAll();
    }


}
