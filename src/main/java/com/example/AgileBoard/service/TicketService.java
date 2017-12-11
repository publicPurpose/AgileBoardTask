package com.example.AgileBoard.service;

import com.example.AgileBoard.model.TicketDto;
import com.example.AgileBoard.model.TypeTicket;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by User on 12/10/2017.
 */
public interface TicketService {

    List<TicketDto> findAllByType(TypeTicket typeTicket);

    List<TicketDto> findAll();

    TicketDto findOne(Long id) throws Exception;

    void save(TicketDto ticket);

    void remove(Long id);

    void create(TicketDto ticket);
}
