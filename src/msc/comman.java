/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msc;

import java.sql.*;
import java.util.*;

/**
 *
 * @author MIHIR005
 */
public class comman {

    Connection conn;
    Statement st;
    ResultSet rs;
    int record;

    public Connection Open_con() {

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "msc";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("succesfully connected");
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return conn;
    }

    public void Close_con() throws SQLException {
        conn.close();
    }

    public ResultSet retrive_data() throws SQLException {

        String sql = "select * from input";
        st = conn.createStatement();
        rs = st.executeQuery(sql);

        return rs;
    }

    public ResultSet number_record() throws SQLException {
        String sql = "select count(*) as record from input";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;

    }

    public void channle(int cell) throws SQLException {

        int dupli_cell = 0;
        if (cell == 1 || cell == 2 || cell == 3) {
            dupli_cell = 1;
        }
        if (cell == 4 || cell == 5 || cell == 6) {
            dupli_cell = 2;
        }
        if (cell == 7 || cell == 8 || cell == 9) {
            dupli_cell = 3;
        }
        String sql = "select c1 from clusterinfo" + dupli_cell;
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        int i = 1;
        int temp_channle;
        int count = 1;
        int channle_all = 0;
        ResultSet rs1;
        ResultSet rs2;
        int first_distance = 0;
        int second_distance = 0;

        if (rs != null) {

            while (rs.next()) {

                temp_channle = Integer.parseInt(rs.getString(1));
                if (temp_channle == 0) {

                    if (cell == 1 || cell == 2 || cell == 3) {

                        int first_cell = 0;
                        String cellcheck = "select cell_number from clusterinfo2 where channel_number=" + count;
                        st = conn.createStatement();
                        rs1 = st.executeQuery(cellcheck);
                        if (rs1 != null) {
                            if (rs1.next()) {
                                first_cell = Integer.parseInt(rs1.getString("cell_number"));

                            }
                        }
//                        int second_cell = 0;
//                        String cellcheck2 = "select cell_number from clusterinfo3 where channel_number=" + count;
//                        st = conn.createStatement();
//                        rs2 = st.executeQuery(cellcheck2);
//                        if (rs2 != null) {
//                            if (rs2.next()) {
//                                second_cell = Integer.parseInt(rs2.getString("cell_number"));
//
//                            }
//                        }

                        double result = 0;
                        double result1 = 0;
                        System.out.println("firstcell" + first_cell);
                        if (first_cell != 0) {

                            first_distance = arry(cell, first_cell);
                            int temp_first = (first_distance / 1000);
                            result = Math.log(Math.pow(temp_first, 4)) * .43429 * 10;
                        } else {
                            result = 35;
                        }
//                        if (second_cell != 0) {
//                            second_distance = arry(cell, first_cell);
//                            int temp_second = (second_distance / 1000);
//                            result = Math.log(Math.pow(temp_second, 4)) * .43429 * 10;
//                        }
//
//                        if (first_cell != 0 && second_cell != 0) {
//                            int temp_first = (first_distance / 1000);
//                            int temp_second = (second_distance / 1000);
//                            result1 = (Math.pow(temp_first, 4) * Math.pow(temp_second, 4)) / (Math.pow(temp_first, 4) + Math.pow(temp_second, 4));
//                            result = Math.log(result1) * .43429 * 10;
//                        }
                        if (result > 22) {
                            try {
                                String sql1 = "update clusterinfo1 set c1=1,cell_number='" + cell + "',channel_number='" + count + "' where c0=" + count;
                                st = conn.createStatement();
                                channle_all = st.executeUpdate(sql1);
                                if (channle_all > 0) {
                                    System.out.print("::accpted channel::" + count + ":SIR::" + result + "dB");

                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("error 1" + e);
                            }
                        } else {
                            System.out.println("call rejected");
                        }
                    }

                    if (cell == 4 || cell == 5 || cell == 6) {

                        int first_cell = 0;
                        String cellcheck = "select cell_number from clusterinfo3 where channel_number=" + count;
                        st = conn.createStatement();
                        rs1 = st.executeQuery(cellcheck);
                        if (rs1 != null) {
                            if (rs1.next()) {
                                first_cell = Integer.parseInt(rs1.getString("cell_number"));

                            }
                        }
//                        int second_cell = 0;
//                        String cellcheck2 = "select cell_number from clusterinfo3 where channel_number=" + count;
//                        st = conn.createStatement();
//                        rs2 = st.executeQuery(cellcheck2);
//                        if (rs2 != null) {
//                            if (rs2.next()) {
//                                second_cell = Integer.parseInt(rs2.getString("cell_number"));
//
//                            }
//                        }

                        double result = 0;
                        double result1 = 0;
                        if (first_cell != 0) {
                            first_distance = arry(cell, first_cell);
                            int temp_first = (first_distance / 1000);
                            result = Math.log(Math.pow(temp_first, 4)) * .43429 * 10;
                        } else {
                            result = 35;
                        }
//                        if (second_cell != 0) {
//                            second_distance = arry(cell, first_cell);
//                            int temp_second = (second_distance / 1000);
//                            result = Math.log(Math.pow(temp_second, 4)) * .43429 * 10;
//                        }
//
//                        if (first_cell != 0 && second_cell != 0) {
//                            int temp_first = (first_distance / 1000);
//                            int temp_second = (second_distance / 1000);
//                            result1 = (Math.pow(temp_first, 4) * Math.pow(temp_second, 4)) / (Math.pow(temp_first, 4) + Math.pow(temp_second, 4));
//                            result = Math.log(result1) * .43429 * 10;
//                        }
                        if (result > 22) {
                            try {
                                String sql2 = "update clusterinfo2 set c1=1,cell_number='" + cell + "',channel_number='" + count + "' where c0=" + count;
                                st = conn.createStatement();
                                channle_all = st.executeUpdate(sql2);
                                if (channle_all > 0) {
                                    System.out.print("accpted channel::" + count + ":SIR::" + result + "dB");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("error2" + e);
                            }
                        } else {
                            System.out.println("..call rejected..");
                        }
                    }

                    if (cell == 7 || cell == 8 || cell == 9) {

                        int first_cell = 0;
                        String cellcheck = "select cell_number from clusterinfo1 where channel_number=" + count;
                        st = conn.createStatement();
                        rs1 = st.executeQuery(cellcheck);
                        if (rs1 != null) {
                            if (rs1.next()) {
                                first_cell = Integer.parseInt(rs1.getString("cell_number"));

                            }
                        }
//                        int second_cell = 0;
//                        String cellcheck2 = "select cell_number from clusterinfo2 where channel_number=" + count;
//                        st = conn.createStatement();
//                        rs2 = st.executeQuery(cellcheck2);
//                        if (rs2 != null) {
//                            if (rs2.next()) {
//                                second_cell = Integer.parseInt(rs2.getString("cell_number"));
//
//                            }
//                        }

                        double result = 0;
                        double result1 = 0;
                        if (first_cell != 0) {
                            first_distance = arry(cell, first_cell);
                            int temp_first = (first_distance / 1000);
                            result = Math.log(Math.pow(temp_first, 4)) * .43429 * 10;
                        } else {
                            result = 35;
                        }
//                        if (second_cell != 0) {
//                            second_distance = arry(cell, first_cell);
//                            int temp_second = (second_distance / 1000);
//                            result = Math.log(Math.pow(temp_second, 4)) * .43429 * 10;
//                        }
//
//                        if (first_cell != 0 && second_cell != 0) {
//                            int temp_first = (first_distance / 1000);
//                            int temp_second = (second_distance / 1000);
//                            result1 = (Math.pow(temp_first, 4) * Math.pow(temp_second, 4)) / (Math.pow(temp_first, 4) + Math.pow(temp_second, 4));
//                            result = Math.log(result1) * .43429 * 10;
//                        }
                        if (result > 22) {

                            try {
                                String sql3 = "update clusterinfo3 set c1=1,cell_number='" + cell + "',channel_number='" + count + "' where c0=" + count;
                                st = conn.createStatement();
                                channle_all = st.executeUpdate(sql3);
                                if (channle_all > 0) {
                                    System.out.print("accpted channel::" + count + ":SIR::" + result + "dB");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("error3" + e);
                            }
                        }
                    } else {
                        System.out.println("..call rejected");
                    }

                }
                count++;
            }

        }
    }

    public void freechannle(int cell) throws SQLException {

        int dupli_cell = 0;
        if (cell == 1 || cell == 2 || cell == 3) {
            dupli_cell = 1;
        }
        if (cell == 4 || cell == 5 || cell == 6) {
            dupli_cell = 2;
        }
        if (cell == 7 || cell == 8 || cell == 9) {
            dupli_cell = 3;
        }
        String sql = "select c1 from clusterinfo" + dupli_cell;
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        int i = 1;
        int temp_channle;
        int count = 1;
        int channle_all = 0;

        if (rs != null) {

            while (rs.next()) {

                temp_channle = Integer.parseInt(rs.getString(1));
                if (temp_channle == 1) {

                    if (cell == 1 || cell == 2 || cell == 3) {

                        try {
                            String sql1 = "update clusterinfo1 set c1=0,cell_number=0,channel_number=0 where c0=" + count;
                            st = conn.createStatement();
                            channle_all = st.executeUpdate(sql1);
                            if (channle_all > 0) {
                                // System.out.println("free channle: c1:" + count);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("error 1" + e);
                        }

                    }

                    if (cell == 4 || cell == 5 || cell == 6) {
                        try {
                            String sql2 = "update clusterinfo2 set c1=0,cell_number=0,channel_number=0 where c0=" + count;
                            st = conn.createStatement();
                            channle_all = st.executeUpdate(sql2);
                            if (channle_all > 0) {
                                //  System.out.println("free channle: c2:" + count);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("error2" + e);
                        }
                    }

                    if (cell == 7 || cell == 8 || cell == 9) {
                        try {
                            String sql3 = "update clusterinfo3 set c1=0,cell_number=0,channel_number=0 where c0=" + count;
                            st = conn.createStatement();
                            channle_all = st.executeUpdate(sql3);
                            if (channle_all > 0) {
                                // System.out.println("free channle: c3:" + count);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("error3" + e);
                        }
                    }

                } else {
                    // System.out.println(">>>>>>>>>>>>>channle is empty...");
                }
                count++;
            }
            if (channle_all == 0) {

            }

        }
    }

    public int arry(int i, int j) {
        int[][] data = new int[10][10];
        data[0][1] = 0;
        data[0][2] = 0;
        data[0][3] = 0;
        data[0][4] = 0;
        data[0][5] = 0;
        data[0][6] = 0;
        data[0][7] = 0;
        data[0][8] = 0;
        data[0][9] = 0;
        data[1][1] = 0;
        data[1][2] = 2000;
        data[1][3] = 2000;
        data[1][4] = 4000;
        data[1][5] = 6000;
        data[1][6] = 4000;
        data[1][7] = 4000;
        data[1][8] = 6000;
        data[1][9] = 6000;
        data[2][1] = 0;
        data[2][2] = 0;
        data[2][3] = 2000;
        data[2][4] = 2000;
        data[2][5] = 4000;
        data[2][6] = 2000;
        data[2][7] = 4000;
        data[2][8] = 4000;
        data[2][9] = 6000;
        data[3][1] = 0;
        data[3][2] = 0;
        data[3][3] = 0;
        data[3][4] = 4000;
        data[3][5] = 4000;
        data[3][6] = 2000;
        data[3][7] = 2000;
        data[3][8] = 4000;
        data[3][9] = 4000;
        data[4][1] = 0;
        data[4][2] = 0;
        data[4][3] = 0;
        data[4][4] = 0;
        data[4][5] = 2000;
        data[4][6] = 2000;
        data[4][7] = 4000;
        data[4][8] = 4000;
        data[4][9] = 6000;
        data[5][1] = 0;
        data[5][2] = 0;
        data[5][3] = 0;
        data[5][4] = 0;
        data[5][5] = 0;
        data[5][6] = 2000;
        data[5][7] = 4000;
        data[5][8] = 2000;
        data[5][9] = 4000;
        data[6][1] = 0;
        data[6][2] = 0;
        data[6][3] = 0;
        data[6][4] = 0;
        data[6][5] = 0;
        data[6][6] = 0;
        data[6][7] = 2000;
        data[6][8] = 2000;
        data[6][9] = 4000;
        data[7][1] = 0;
        data[7][2] = 0;
        data[7][3] = 0;
        data[7][4] = 0;
        data[7][5] = 0;
        data[7][6] = 0;
        data[7][7] = 0;
        data[7][8] = 2000;
        data[7][9] = 2000;
        data[8][1] = 0;
        data[8][2] = 0;
        data[8][3] = 0;
        data[8][4] = 0;
        data[8][5] = 0;
        data[8][6] = 0;
        data[8][7] = 0;
        data[8][8] = 0;
        data[8][9] = 2000;
        data[9][1] = 0;
        data[9][2] = 0;
        data[9][3] = 0;
        data[9][4] = 0;
        data[9][5] = 0;
        data[9][6] = 0;
        data[9][7] = 0;
        data[9][8] = 0;
        data[9][9] = 0;

        return data[i][j];
    }
}
