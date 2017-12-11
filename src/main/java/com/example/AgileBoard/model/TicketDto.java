package com.example.AgileBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.deploy.security.ValidationState;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by UserDto on 12/10/2017.
 */
@Document(collection="tickets")
public class TicketDto {

    @Id
    private Long id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String name;
    private String description;
    private TypeTicket type;

    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTicket getType() {
        return type;
    }

    public void setType(TypeTicket type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TicketDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
