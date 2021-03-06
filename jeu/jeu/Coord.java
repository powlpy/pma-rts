/*
* Copyright 2013-2014 Jérémie Faye, Nicolas Poelen, Roman Lopez, Alexis Delannoy
*
* This program is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free
* Software Foundation, either version 3 of the License, or (at your option) any
* later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package jeu;

import java.awt.Dimension;


/**
* Classe representant des coordonnees
*
* @author Nicolas
*/
public class Coord {
        /**
         * coordonnees du point
         */
        public int x;
        public int y;
        
        /**
         * valeur representant la marge d'erreur pour verifie l'egalite entre 2 point
         * commune a tous les Coord, en cas d'utilisation de plusieurs epsilon, utilise compare()
         */
        public static double EPSILON = 0.5;
        
        public static final Coord NULL = new Coord(0, 0);
        
        /**
         * Constructeur par valeur
         * @param px coordonnee x
         * @param py coordonnee y
         */
        public Coord (int px, int py){
                this.x = px;
                this.y = py;
        }
        
        /**
         * Constructeur par copie
         * @param coord attributs simplement copies
         */
        public Coord (Coord coord){
                this.x = coord.x;
                this.y = coord.y;
        }
        
        /**
         * getter de x
         * @return x
         */
        public double getX (){
                return this.x;
        }
        
        /**
         * getter de y
         * @return y
         */
        public double getY (){
                return this.y;
        }
        
        /**
         * setter de x et y
         * un setter unique est inutile, les attributs etant publiques
         * @param px nouvelle valeur de x
         * @param py nouvelle valeur de y
         */
        public void set (int px, int py){
                this.x = px;
                this.y = py;
        }
        
        /**
         * addition du Coord courant avec un autre Coord
         * @param c additionneur
         * @return l'addition des 2 coordonnes
         */
        public Coord add (Coord c){
                return new Coord(this.x + c.x, this.y + c.y);
        }
        
        /**
         * soustraction de Coord courant avec un autre Coord
         * @param c soustrayeur
         * @return la soustraction des 2 coordonnes
         */
        public Coord sous (Coord c){
                return new Coord(this.x - c.x, this.y - c.y);
        }
        
        /**
         * addition du Coord courant avec un autre Coord ou le resultat est enregistre dans l'objet courant
         * @param c additionneur
         */
        public void addEgal (Coord c){
                this.x += c.x;
                this.y += c.y;
        }
        
        /**
         * soustraction du Coord courant avec un autre Coord ou le resultat est enregistre dans l'objet courant
         * @param c soustrayeur
         */
        public void souEgal (Coord c){
                this.x -= c.x;
                this.y -= c.y;
        }
        
        public Coord division(int d){
        	return new Coord(this.x / d, this.y / d);
        }
        
        public void divEgale(int d){
        	this.x /= d;
        	this.y /= d;
        }
        
        /**
         * @return l'inverse du coord
         */
        public Coord inverse (){
        	return new Coord(-this.x, -this.y);
        }
        
        /**
         * this = this.inverse()
         */
        public void inverseEgale (){
        	this.x = -this.x;
        	this.y = -this.y;
        }
        
        /**
         * verifie l'egalite entre 2 Coord
         * prend en compte une marge d'erreur en utilisant l'attribut EPSILON
         * @param c Coord a comparer
         * @return true si les 2 Coord ont meme valeur de x et y, false sinon
         */
        public boolean equal (Coord c){
                /*return this.x > (c.x - EPSILON) && this.x < (c.x + EPSILON) &&
                         this.y > (c.y - EPSILON) && this.y < (c.y + EPSILON);*/
        	return this.x == c.x && this.y == c.y;
        }
        
        /**
         * Compare le rapprochement entre 2 Coord similaire a equal
         * en indiquant une autre valeur a la place d'EPSILON
         * attention cette methode considere un perimetre carre,
         * utiliser plutot distance() pour evaluer un perimetre
         * @param c Coord a comparer
         * @param e EPSILON indique la marge d'erreur
         * @return true si les 2 Coord ont des valeurs suffisament proche pour x et y, false sinon
         */
        public boolean compare (Coord c, float e){
                return this.x > (c.x - e) && this.x < (c.x + e) &&
                         this.y > (c.y - e) && this.y < (c.y + e);
        }
        
        public Dimension dim(){
        	return new Dimension (this.x , this.y);
        }
        /**
         * distance entre 2 points
         * @param c second Coord
         * @return la distance entre 2 points
         */
        /*
        public double distance (Coord c){  // /!\ Probl�me ici , un vecteur est d�finis avec deux cordoon�e x et y , hors ici on lui donne 4 coordonn�es
                Vecteur v = new Vecteur(this, c);
                return v.norme();
        }
        */
        public  Coord multiple(int i){
        	return(new Coord(this.x*i , this.y*i)); 
        }
        
        public int distance(Coord b){
        	return (Math.abs(this.x-b.x)+Math.abs(this.y-b.y));
        }
        public String toString ( ){
            return "Cordonnee :"+this.x +" ," +this.y;  // --- Ici completer avec les infos de ta classe que tu souhaite afficher
        }
        
}