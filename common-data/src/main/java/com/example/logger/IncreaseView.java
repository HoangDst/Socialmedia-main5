package com.example.logger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonDeserialize(as= IncreaseView.class)
public class IncreaseView {
    private String userAccount;
    private Integer postId;
}
