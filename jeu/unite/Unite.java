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

package unite;

import java.util.List;
import java.util.Random;

import jeu.Alignement;
import jeu.Cellule;
import jeu.Coord;
import jeu.ObjectMap;
import jeu.Societe;
import jeu.Vecteur;

/** Classe qui définis les Unités , chaque Unité (hors batiment) hérite de cette classe.
 * 
 * @author Nicolas, fayej
 *
 */
public class Unite extends ObjectMap {
	
	protected int vie;
	 /**
     * Represente l'objet ou l'unite doit se dirige lorsqu'il se deplace.
     * Si l'unite ne se dirige pas vers un objet, indique null.
     * Verifier souvent l'etat de l'objet pour savoir
     * si il ne s'est pas deplace ou n'a pas ete detruit.
     */
    protected ObjectMap objetCible;
    /**
     * Represente le point ou l'unite doit se dirige lorsqu'elle doit faire un mouvement.
     * Si l'unite n'est pas en mouvement, cette valeur n'est pas utilise.
     * La positionCible n'est pas forcement egale a la position de l'objetCible.
     */
    protected Coord positionCible;
    /**
     * Represente le vecteur de deplacement de l'unite.
     * Cet attribut permet d'evite de recalculer le vecteur de deplacement
     * lorsque la positionCible ne change pas.
     * Si l'unite n'est pas en mouvement, indique Vecteur.NULL.
     */
    protected Vecteur deplacement;
    protected Cellule forum;
    
    public Unite (Cellule c, Alignement a, int v){
    	super(c, a);
    	this.vie = v;
    	this.forum = c;
    	//this.curent.personne.add(this);
    }
    
    public void activationgeneral(){
    	

        	
        	this.curent.personne.add(this);
        	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.ALIGNEMENT[this.al.numero]);
    	
    }


	protected void rapproche(Cellule cible){
		if(!this.curent.personne.isEmpty()){
		
		this.curent.personne.remove(this);
			
		}
		if (Math.abs(this.curent.coord.x -cible.coord.x) >= Math.abs(this.curent.coord.y - cible.coord.y)) {
			
		if (this.curent.coord.x != cible.coord.x){ 
		
			if (this.curent.coord.x> cible.coord.x){
				this.curent = this.curent.env.getCellule(this.curent.coord.x-1, this.curent.coord.y);
			}
			else if (this.curent.coord.x< cible.coord.x) this.curent = this.curent.env.getCellule(this.curent.coord.x+1, this.curent.coord.y);
		}
		}
		else if (this.curent.coord.y != cible.coord.y){  if (this.curent.coord.y> cible.coord.y){
				this.curent = this.curent.env.getCellule(this.curent.coord.x, this.curent.coord.y-1);
				
			}
			else if (this.curent.coord.y< cible.coord.y){ this.curent = this.curent.env.getCellule(this.curent.coord.x, this.curent.coord.y+1);}
		
		}
		this.coord = curent.coord;
		this.curent.personne.add(this);
		}
	protected Cellule laplusproche(List<Cellule> ressource){
		Cellule cel = ressource.get(0);
		int plusproche = ressource.get(0).coord.distance(this.curent.coord);
		for (int i =1 ; i<ressource.size() ; i++){
			if(ressource.get(i).coord.distance(this.curent.coord)< plusproche){
				cel = ressource.get(i);
				plusproche = ressource.get(i).coord.distance(this.curent.coord);
			}
		}
		return cel;
	}
	protected int nbrAlt(){
		Random r = new Random();
		int valeur = r.nextInt(3)-1;
		return (valeur);
	}
	
	protected void deplacement_aleatoire(){
		if(!this.curent.personne.isEmpty()){
			this.curent.personne.remove(this);
			}
		this.curent = curent.env.getCellule(curent.coord.x+(nbrAlt()), curent.coord.y+(nbrAlt()));
		this.coord = curent.coord;
		this.curent.personne.add(this);
	}
	
	public static final Coord HAUT = new Coord(0, -1);
	public static final Coord GAUCHE = new Coord(-1, 0);
	public static final Coord DROITE = new Coord(1, 0);
	public static final Coord BAS = new Coord(0, 1);
	
	/**
	 * Methode permmetant de deplacer l'unite d'une case.
	 * @param coordonnees, utilisez l'une des 4 constantes HAUT, GAUCHE, DROITE, BAS
	 * @return false si l'unite ne peux pas se deplace
	 */
	public boolean move (Coord c){
		Cellule ce = this.curent.env.getCellule(this.coord.add(c));
		if(ce == null)
			return false;
		else{
			this.curent.personne.remove(this);
			this.curent = ce;
			this.coord = this.curent.coord;
			this.curent.personne.add(this);
			return true;
		}
	}
	
	
	public int distance (Coord c){
		return this.coord.distance(c);
	}
	
	public int distance (Cellule c){
		return distance(c.coord);
	}
	
    /**
     * Ordonne a l'unite de se diriger vers un objet cible.
     * Dans le cas ou l'objet se deplace, la position devra etre redefinis
     * TODO La verfication devra surement se faire dans une autre methode.
     * @param o objetCible
     */
    public void SeDirige (ObjectMap o){
            //TODO a faire
    }
	
    @Override
    public void end(){
    	this.curent.personne.remove(this);
    }
    public int getvie(){
    	return this.vie;
    }
    public void setvie(int var){ //méthode pour donner l'acces a la variable de vie (pour l'hopital ou une attaque)
    	this.vie = this.vie+var;
    }
}