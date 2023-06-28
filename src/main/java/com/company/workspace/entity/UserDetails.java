package com.company.workspace.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {
    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "registration_date", nullable = false)
    private String registrationDate;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "cv_pdf", nullable = false)
    private String cvPdf;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}