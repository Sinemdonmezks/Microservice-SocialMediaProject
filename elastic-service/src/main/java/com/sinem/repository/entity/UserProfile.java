package com.sinem.repository.entity;

import com.sinem.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "userprofile")
public class UserProfile implements Serializable {
    @Id
    String id;
    Long authid;
    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Long created;
    Long updated;
    Status status;
}
