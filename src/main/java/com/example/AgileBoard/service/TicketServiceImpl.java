package com.example.AgileBoard.service;

import com.example.AgileBoard.model.TicketDto;
import com.example.AgileBoard.model.TypeTicket;
import com.example.AgileBoard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by User on 12/10/2017.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<TicketDto> findAllByType(TypeTicket typeTicket) {
        return ticketRepository.findByType(typeTicket);
    }

    @Override
    public List<TicketDto> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketDto findOne(Long id) throws Exception {
        if (isEmpty(id)) {
            throw new Exception("field can not be empty ");
        }
        return ticketRepository.findOne(id);
    }

    @Override
    public void save(TicketDto ticketDto) {
        TicketDto ticket = new TicketDto();
        ticket.setId(new Long(String.valueOf(UUID.fromString(ticketDto.getName()))));
        ticket.setType(ticketDto.getType());
        ticket.setDescription(ticketDto.getDescription());
        ticketRepository.save(ticket);
    }

    @Override
    public void remove(Long id) {
        ticketRepository.delete(id);
    }

    @Override
    public void create(TicketDto ticket) {
        //TODO
    }
}
