package com.company.workspace.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDetails {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "website", nullable = false)
    private String website;

    @Lob
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "founded_year", nullable = false)
    private String foundedYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}
