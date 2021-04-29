package com.oob.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.MODULE)
public class Image {
    @Id
    private Integer postId;

    private String imageName;
}
