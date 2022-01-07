package com.example.springdemo.core.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InQueryRequest {
    List<String> firstNames;
}
