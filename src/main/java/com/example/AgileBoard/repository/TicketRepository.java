package com.example.AgileBoard.repository;

import com.example.AgileBoard.model.TicketDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/10/2017.
 */
@Repository
public interface TicketRepository extends MongoRepository<TicketDto, Long>{
    TicketDto findByName(String name);
}
