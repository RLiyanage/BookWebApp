/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.rnn.bookwebapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rasika
 */
public class AuthorService {
   private List <Author> authorNames; 
   
   public AuthorService(){
       authorNames = new ArrayList<>();
       
   }

   public List<Author> getAuthorList(){
       authorNames.add(new Author("Rasika"));
       authorNames.add(new Author("Sanuth"));
       authorNames.add(new Author("Binuth"));
      return authorNames; 
   }
}
