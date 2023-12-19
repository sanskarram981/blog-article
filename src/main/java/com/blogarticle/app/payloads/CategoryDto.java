package com.blogarticle.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int id;
    private String title;
    private String description;
}
