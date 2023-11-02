package com.epam.rd.java.basic.task7.db.entity;

public abstract class DBConstants {
    private DBConstants() {

    }

    //Query
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_ALL_TEAMS = "SELECT * FROM teams";
    public static final String GET_USER_TEAMS = "SELECT * FROM teams " +
            "INNER JOIN users_teams ON teams.id=users_teams.team_id " +
            "WHERE user_id = ?";
    public static final String FIND_USER = "SELECT * FROM users u WHERE u.login=?";
    public static final String FIND_TEAM = "SELECT * FROM teams t WHERE t.name=?";
    public static final String ADD_USER = "INSERT INTO users values (DEFAULT, ?)";
    public static final String ADD_TEAM = "INSERT INTO teams values (DEFAULT, ?)";
    public static final String SET_TEAMS = "INSERT INTO users_teams values (?, ?)";
    public static final String DELETE_USERS = "DELETE FROM users WHERE login=?";
    public static final String DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    public static final String UPDATE_TEAM = "UPDATE teams SET name =? WHERE id=?";

}
