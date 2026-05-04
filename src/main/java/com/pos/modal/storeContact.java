package com.pos.modal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Embeddable
public class storeContact {

    private String address;
    private String phone;

    @Email(message ="Invalid email")
    private String email;

}
