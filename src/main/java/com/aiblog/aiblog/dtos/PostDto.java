package com.aiblog.aiblog.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostDto(@NotBlank String name,
                      @NotBlank String title,
                      @NotBlank String category,
                      @NotBlank String content,
                      @NotBlank String author,
                      @NotBlank String image,
                      @NotBlank String snippet) {

}
