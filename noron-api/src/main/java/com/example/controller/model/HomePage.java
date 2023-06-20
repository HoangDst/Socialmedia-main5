package com.example.controller.model;

import com.example.data.response.PostResponse;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class HomePage {
private List<PostResponse>postResponseList;

}
