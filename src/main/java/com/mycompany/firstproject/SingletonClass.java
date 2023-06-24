/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.firstproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author o7solutions
 */
public class SingletonClass {
    Connection connection = null;
    private static SingletonClass singletonclass = null;

    private SingletonClass() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
            if(connection != null){
                String createStudent = "CREATE TABLE IF NOT EXISTS student(id INT, name VARCHAR(255), PRIMARY KEY(id))";
                PreparedStatement ps = connection.prepareStatement(createStudent);
                ps.execute();
                
                String registerTable = "CREATE TABLE IF NOT EXISTS user(id INT AUTO_INCREMENT, firstName VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255), phoneNumber VARCHAR(255), password VARCHAR(255), PRIMARY KEY(id))";
                PreparedStatement psRegister = connection.prepareStatement(registerTable);
                psRegister.execute();
            }
        }catch(SQLException sqlException){
            System.out.println("in exception "+sqlException.getMessage());
        }
    }

    public static SingletonClass getInstance() {
        if (singletonclass == null) {
            singletonclass = new SingletonClass();
        }
        return singletonclass;
    }
}
