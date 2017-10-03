/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.rnn.bookwebapp.model;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rasika
 */
public class AuthorService {

   private IAuthorDao authorDao;
   
    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }
   
    public List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {
       
        return authorDao.getListOfAuthors();
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }
   
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
       
        IAuthorDao dao = new AuthorDao(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin",
            new MySqlDataAccess("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin")
        );
       
        AuthorService authorService =
                new AuthorService(dao);
       
        List<Author> list = authorService.getAuthorList();
       
        for(Author a: list) {
            System.out.println(a.getAuthorId() + ", "
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}

