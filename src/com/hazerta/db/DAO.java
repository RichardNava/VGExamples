package com.hazerta.db;

import com.hazerta.models.VideoGame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DAO {

    private final String URL;
    public List<VideoGame> videogames = new ArrayList<>();

    public DAO(String URL) throws SQLException {
        this.URL = URL;
        videogames = findAllVideogames();
    }

    public List<VideoGame> findAllVideogames() throws SQLException {
        List<VideoGame> videogames = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL)) {
            String sql = "SELECT * FROM videogames";
            Statement st = con.createStatement();
            st.execute(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                videogames.add(new VideoGame(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)));
            }
        }
        return videogames;
    }

    public List<VideoGame> findAllVideogamesByGenre(String genre) throws SQLException {
        List<VideoGame> videogames = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL)) {
            Statement st = con.createStatement();
//            String sql = "SELECT * FROM APP.videogames WHERE videogames.genero = '"+genre+"'";
            String sql = "SELECT * FROM APP.videogames WHERE videogames.genero = " + st.enquoteLiteral(genre);

//            String sql = "SELECT * FROM APP.videogames WHERE videogames.genero = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, genre);
//            ResultSet rs = ps.executeQuery();
            st.execute(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                videogames.add(new VideoGame(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)));
            }
        }
        return videogames;
    }

    public void findAllVideogamesByGenre(String... genres) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL)) {

            String sql = "SELECT * FROM APP.videogames WHERE videogames.genero = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            for (String genre : genres) {
                ps.setString(1, genre);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1)+" "+ 
                            rs.getString(2)+ " "+
                            rs.getString(3)+ " "+
                            rs.getDouble(4)+ " "+
                            rs.getBoolean(5));
                }
            }
        }
    }

}
