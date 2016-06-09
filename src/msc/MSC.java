/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msc;

import java.sql.*;

/**
 *
 * @author MIHIR005
 */
public class MSC {

    public static ResultSet rs;
    public static ResultSet rs1;
    public static int r;
    public static int Arry_distance;

    static int time;
    static int cell;
    static int duration;

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        comman c = new comman();
        c.Open_con();

        //find number of record
        rs = c.number_record();
        if (rs != null) {
            if (rs.next()) {
                r = Integer.parseInt(rs.getString(1));
                System.out.println("number of record" + rs.getString(1));

            }
        }

//        Arry_distance = c.arry(8, 9);
//        System.out.println("print dd::::::::::::" + Arry_distance);
//        
        rs = c.retrive_data();

        int first_total_time = 0;
        for (int in = 0; in < r; in++) {

            if (in == 0) {
                if (rs.next()) {
                    time = Integer.parseInt(rs.getString("time"));
                    cell = Integer.parseInt(rs.getString("cell"));
                    duration = Integer.parseInt(rs.getString("duration"));
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("New Call Number::"+(in+1)+"::Time::"+time+"::Cell::"+cell+"::Duration::"+duration);
                    c.channle(cell);

                }
            } else {

                if (rs.next()) {
                    time = Integer.parseInt(rs.getString("time"));
                    cell = Integer.parseInt(rs.getString("cell"));
                    duration = Integer.parseInt(rs.getString("duration"));
                    first_total_time = time;
                    System.out.println("\n");
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("New Call Number::"+(in+1)+"::Time::"+time+"::Cell::"+cell+"::Duration::"+duration);
                    c.channle(cell);
                }
                rs1 = c.retrive_data();
                for (int j = 0; j < in; j++) {
                    if (rs1.next()) {
                        int secondcell = Integer.parseInt(rs1.getString("cell"));
                        int secondtime = Integer.parseInt(rs1.getString("time"));
                        int secondduration = Integer.parseInt(rs1.getString("duration"));
                        int sec_totaltime = secondtime + secondduration;
                        if (secondcell == cell) {
                            if (sec_totaltime < first_total_time) {
                                c.freechannle(cell);
                            }
                          
                            
                        }
                    }

                }

            }

        }

    }

}
