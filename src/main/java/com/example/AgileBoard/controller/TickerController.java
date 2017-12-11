package com.example.AgileBoard.controller;

import com.example.AgileBoard.model.TicketDto;
import com.example.AgileBoard.model.TypeTicket;
import com.example.AgileBoard.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 12/10/2017.
 */
@RestController
@RequestMapping("/agile/tickets")
@CrossOrigin("*")
public class TickerController {

    private static final Logger logger = LoggerFactory.getLogger(TickerController.class);

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    public List<TicketDto> getAllTickets() {
        //TODO Sorting
       // Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ticketService.findAll();
    }

   /* @GetMapping()
    public List<TicketDto> getAllTicketsByType(TypeTicket typeTicket) {
        //Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ticketService.findAllByType(typeTicket);
    }*/

    @GetMapping(value = "{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable(value = "id") Long id) throws Exception {
        TicketDto newTicket = ticketService.findOne(id);

        if (newTicket == null) {
            logger.info("getting newPost with id " + id + " not found");
            return new ResponseEntity<TicketDto>(newTicket, HttpStatus.NOT_FOUND);
        }

        logger.info("getPostById id " + id);
        return new ResponseEntity<TicketDto>(newTicket, HttpStatus.OK);
    }

    @RequestMapping(value = "/create")
    public ResponseEntity<Void> createNewTicket(@RequestBody TicketDto ticket, UriComponentsBuilder ucBuilder) {

        ticketService.create(ticket);

        logger.info("Created new ticket id " + ticket.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/agile/tickets/{id}").buildAndExpand(ticket.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody TicketDto ticketDetails) throws Exception {
        TicketDto ticket = ticketService.findOne(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        ticket.setName(ticketDetails.getName());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setType(ticketDetails.getType());

        ticketService.save(ticket);

        TicketDto updateTicket = ticketService.findOne(ticket.getId());
        return ResponseEntity.ok(updateTicket);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(value = "id") Long id) throws Exception {
        logger.info("deleting with id " + id);
        TicketDto target = ticketService.findOne(id);

        if (target == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        ticketService.remove(target.getId());
        logger.info("remove targetPost " + target.getId());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //TODO change TicketType

   /* @PatchMapping(value = "{id}")
    public ResponseEntity<TicketDto> updateTicketType(@PathVariable(value = "id") Long id,
                                                  @Valid @RequestBody TicketDto ticketDetails) {
        TicketDto ticket = ticketService.findOne(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        ticket.setName(ticketDetails.getName());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setType(ticketDetails.getType());

        ticketService.save(ticket);

        TicketDto updateTicket = ticketService.findOne(ticket.getId());
        return ResponseEntity.ok(updateTicket);
    }*/

}
