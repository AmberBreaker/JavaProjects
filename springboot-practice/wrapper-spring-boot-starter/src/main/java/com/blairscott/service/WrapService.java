package com.blairscott.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WrapService {

    private String prefix;

    private String suffix;

    public String wrap(String word) {
        return prefix + word + suffix;
    }

}
