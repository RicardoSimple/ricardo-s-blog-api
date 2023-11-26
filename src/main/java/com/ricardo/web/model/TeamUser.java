package com.ricardo.web.model;

import lombok.Data;

@Data
public class TeamUser extends User{
    private String teamId;

    private String role;
}
