package com.example.customerproject.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    private Long iin;

    private String fullName;
    @Id
    private String docNum;

    private String docDetails;

    private Date birthDate;

    private boolean resident;

}
