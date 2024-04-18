package com.demo.fileprocessing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long fileId;

    @Column(name = "name")
    private String fileName;

    @Column(name = "type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private byte[] file;
}
