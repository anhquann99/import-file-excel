package com.example.importfileexcel.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class Student {
    @Id
    private String studentCode;
    private String className;
    private String fullName;
    private Date birthday;
    private String sex;
    private String placeOfBirth;
    private String nation;
    private String household;
    private String phone;
    private String totalPointFirstGrade;
    private String totalPointSecondGrade;
    private String totalPointThirdGrade;
    private String totalPointFourthGrade;
    private String totalPointFifthGrade;
    private String totalPointGrade;
    private String priorityPoint;
    private String totalPoint;
    private String note;
}

