package com.example.AgileBoard.repository;

import com.example.AgileBoard.model.TicketDto;
import com.example.AgileBoard.model.TypeTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 12/10/2017.
 */
@Repository
public interface TicketRepository extends MongoRepository<TicketDto, Long>{
    TicketDto findByName(String name);

    List<TicketDto> findByType(TypeTicket typeTicket);

}
