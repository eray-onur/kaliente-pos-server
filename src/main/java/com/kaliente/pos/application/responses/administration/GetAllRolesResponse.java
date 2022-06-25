package com.kaliente.pos.application.responses.administration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetAllRolesResponse {
    ArrayList<String> titles;
}
