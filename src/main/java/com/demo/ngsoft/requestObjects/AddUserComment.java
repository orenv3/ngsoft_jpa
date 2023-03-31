package com.demo.ngsoft.requestObjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddUserComment (
        @NotBlank @Size(max=120) String comment,
        @NotNull Long TaskId,
        @NotNull Long userId
){
}
