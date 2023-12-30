package com.example.DataOx.Jobs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobListing {

    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    String jobPageUrl;
    String positionName;
    String urlToOrganization;
    String logo;
    String organizationTitle;
    String laborFunction;
    String address;
    Long postedDate;
    @Column(columnDefinition="text", length=10485760)
    String description;

    String tagsNames;
}
