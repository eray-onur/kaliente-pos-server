package com.kaliente.pos.domain.useraggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "personnel")
@Table
public class Personnel extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String workEmail;
    @Column
    private String workPhoneNumber;
    @Column
    private String homeAddress;
    @Column
    private String gender;
    @Column
    private Date dateOfBirth;
    @Column
    private String countryOfBirth;
    @Column
    private String profileImagePath;

}
