/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.rnn.bookwebapp.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rasika
 */
public class AuthorService {

   public List<Author> getAuthorList(){     
      
      return Arrays.asList(
              new Author(1, "Mark Thaiwan",new Date()),
              new Author(2, "Stephen Tuwan",new Date()),
              new Author(3, "Gorge Twan",new Date())
      );
   }
}
