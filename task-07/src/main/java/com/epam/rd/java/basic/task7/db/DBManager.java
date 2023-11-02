package com.epam.rd.java.basic.task7.db;

import com.epam.rd.java.basic.task7.db.entity.Team;
import com.epam.rd.java.basic.task7.db.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.rd.java.basic.task7.db.entity.DBConstants.*;


public class DBManager {

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
    }

    private String getUrl() throws DBException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new DBException("file read error", e);
        }
        String url = properties.getProperty("connection.url");
        return url;
    }

    public List<User> findAllUsers() throws DBException {
        String url = getUrl();
        List<User> res = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS);
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                res.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("findAllUsers Exception", e);
        }
        return res;
    }


    public boolean insertUser(User user) throws DBException {
        String url = getUrl();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(ADD_USER);
        ) {
            int k = 0;
            stmt.setString(++k, user.getLogin());
            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("insertUser Exception", e);
        }
        return false;
    }

    public boolean deleteUsers(User... users) throws DBException {
        String url = getUrl();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(DELETE_USERS);
        ) {
            for (User u : users) {
                stmt.setString(1, u.getLogin());
                stmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("deleteUsers Exception", e);
        }
    }

    public User getUser(String login) throws DBException {
        String url = getUrl();
        User u = new User();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(FIND_USER);
        ) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    u.setId(rs.getInt("id"));
                    u.setLogin(rs.getString("login"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("getUser Exception", e);
        }
        return u;
    }

    public Team getTeam(String name) throws DBException {
        String url = getUrl();
        Team t = new Team();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(FIND_TEAM);
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    t.setId(rs.getInt("id"));
                    t.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("getTeam Exception", e);
        }
        return t;
    }

    public List<Team> findAllTeams() throws DBException {
        String url = getUrl();
        List<Team> res = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(GET_ALL_TEAMS);
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                Team t = new Team();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                res.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("findAllUsers Exception", e);
        }
        return res;
    }

    public boolean insertTeam(Team team) throws DBException {
        String url = getUrl();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(ADD_TEAM);
        ) {
            int k = 0;
            stmt.setString(++k, team.getName());
            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("insertTeam Exception", e);
        }
        return false;
    }

    public boolean setTeamsForUser(User user, Team... teams) throws DBException {
        String url = getUrl();
        User currentUser = getUser(user.getLogin());
        List<Team> existTeams = findAllTeams();
        List<Team> income = List.of(teams);
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.prepareStatement(SET_TEAMS);
            for (Team team : income) {
                for (Team existTeam : existTeams) {
                    if (team.equals(existTeam)) {
                        team.setId(existTeam.getId());
                    }
                }
            }
            for (Team t : income) {
                int k = 0;
                stmt.setInt(++k, currentUser.getId());
                stmt.setInt(++k, t.getId());
                stmt.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DBException("setTeams Exception", e);
        } finally {
            close(con);
            close(stmt);
        }
    }

    public List<Team> getUserTeams(User user) throws DBException {
        String url = getUrl();
        User currentUser = getUser(user.getLogin());
        List<Team> res = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(GET_USER_TEAMS);) {
            stmt.setInt(1, currentUser.getId());
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    Team t = new Team();
                    t.setId(rs.getInt("id"));
                    t.setName(rs.getString("name"));
                    res.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("getUserTeams Exception", e);
        }
        return res;
    }

    public boolean deleteTeam(Team team) throws DBException {
        String url = getUrl();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(DELETE_TEAM);
        ) {
            stmt.setString(1, team.getName());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("deleteTeam Exception", e);
        }
    }

    public boolean updateTeam(Team team) throws DBException {
        String url = getUrl();
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(UPDATE_TEAM);
        ) {

            int k = 0;
            stmt.setString(++k, team.getName());
            stmt.setInt(++k, team.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("updateTeam Exception", e);
        }
    }

    private void close(AutoCloseable ac) {
        try {
            ac.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
