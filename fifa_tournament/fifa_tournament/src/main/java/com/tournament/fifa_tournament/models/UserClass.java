package com.tournament.fifa_tournament.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor // konstruktor s atributmi
@NoArgsConstructor  // konstruktory bez atributov
@Entity
@Table(name = "users", schema = "tournaments")
public class UserClass {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userID;
        private String userName;
        private String password;
        private String role;
        @CreationTimestamp
        private LocalDateTime createdDate;
        @UpdateTimestamp
        private LocalDateTime updatedDate;

}
