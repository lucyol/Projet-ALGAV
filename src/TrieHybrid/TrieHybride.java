package TrieHybrid;

import Tools.*;

import java.util.ArrayList;

import ArbreBriandais.Briandais;
import Interfaces.IBriandais;
import Interfaces.ITrieHybride;

public class TrieHybride implements ITrieHybride {

	

	private char l;/* premiere lettre du premier mot de l'arbre */
	private int valeur = -1; 
	private ITrieHybride pere;
	private ITrieHybride fils_gauche;
	private ITrieHybride fils_central;
	private ITrieHybride fils_droit;
	
	/* CONSTRUCTEURS */

	/* constructeur vide */
	public TrieHybride() {
		this.pere = null;
		this.fils_central = null;
		this.fils_droit = null;
		this.fils_gauche = null;
	}

	/* constructeur param�tr�s utilis� pour l'ajout */
	public TrieHybride(String mot) {
		if (mot.isEmpty())
			new TrieHybride();
		else {
			l = mot.charAt(0);
			this.fils_droit = null;
			this.fils_gauche = null;
			if (mot.length() == 1) {
				/* un mot d'une longueur 1, lettre= le mot, fils central = null */
				setIsMot();
				this.fils_central = null;
			} else {
				/* sinon recursivit� sur le mot moins le premier element */
				this.fils_central = new TrieHybride(mot.substring(1));
				((TrieHybride) fils_central).setPere(this);
			}
		}
	}

	/* constructeur avec la cl� ( fusion , suppression) */
	public TrieHybride(char lettre, ITrieHybride fils_central,
			ITrieHybride fils_gauche, ITrieHybride fils_droit) {
		this.l = lettre;
		this.fils_central = fils_central;
		this.fils_gauche = fils_gauche;
		this.fils_droit = fils_droit;
		if (fils_central != null)
			((TrieHybride) fils_central).setPere(this);
		if (fils_gauche != null)
			((TrieHybride) fils_gauche).setPere(this);
		if (fils_droit != null)
			((TrieHybride) fils_droit).setPere(this);
	}

	/* constructeur avc cl�,valeur ( equilibre, suppresion) */
	public TrieHybride(char lettre, int valeur, ITrieHybride fils_central,
			ITrieHybride fils_gauche, ITrieHybride fils_droit) {
		this.l = lettre;
		this.fils_central = fils_central;
		this.fils_gauche = fils_gauche;
		this.fils_droit = fils_droit;
		if (fils_central != null)
			((TrieHybride) fils_central).setPere(this);
		if (fils_gauche != null)
			((TrieHybride) fils_gauche).setPere(this);
		if (fils_droit != null)
			((TrieHybride) fils_droit).setPere(this);
		this.valeur = valeur;
	}
	
	/* GETTERS / SETTERS */

	@Override
	public void setFilsGauche(ITrieHybride arbre) {
		if (fils_gauche != null)
			((TrieHybride) fils_gauche).setPere(this);
		this.fils_gauche = arbre;

	}

	@Override
	public void setFilsDroit(ITrieHybride arbre) {
		if (fils_droit != null)
			((TrieHybride) fils_droit).setPere(arbre);
		this.fils_droit = arbre;

	}

	@Override
	public void setFilsCentral(ITrieHybride arbre) {
		if (fils_central != null)
			((TrieHybride) fils_central).setPere(this);
		this.fils_central = arbre;

	}

	@Override
	public void setIsMot() {
		valeur = '\0';
	}

	@Override
	public void setnotnode() {
		valeur = -1;
	}

	@Override
	public boolean IsMot() {
		return valeur != -1;
	}

	@Override
	public int getValeur() {
		return valeur;
	}
	
	@Override
	public boolean isEmpty() {
		return fils_central == null && fils_droit == null
				&& fils_gauche == null && valeur == -1;
	}

	@Override
	public boolean isTrieHybride() {
		return true;
	}
	
	@Override
	public char getKey() {
		return l;
	}

	@Override
	public ITrieHybride getPere() {
		return this.pere;
	}

	@Override
	public ITrieHybride getFilsCentrale() {
		return this.fils_central;
	}

	@Override
	public ITrieHybride getFilsGauche() {
		return this.fils_gauche;
	}

	@Override
	public ITrieHybride getFilsDroit() {
		return this.fils_droit;
	}

	@Override
	public void setPere(ITrieHybride pere) {
		this.pere = pere;
	}
	@Override
	public ITrieHybride add(String mot) {
		FileRead f = new FileRead();
		return (ITrieHybride) ajoutDansTrie(f.toWord(mot));
	}

	
	/*
	 * FONCTIONS COMPLEXES
	 * 
	 * */
	private ITrieHybride ajoutDansTrie(String mot) {
		if (isEmpty()) {
			return new TrieHybride(mot);
		} else {
			if (!mot.isEmpty()) {
				if (this.l < mot.charAt(0)) {
					if (fils_droit == null) {
						fils_droit = new TrieHybride(mot);
						((TrieHybride) fils_droit).setPere(this);
					} else
						((TrieHybride) fils_droit).ajoutDansTrie(mot);
					return this;
				} else {
					if (this.l > mot.charAt(0)) {
						if (fils_gauche == null) {
							fils_gauche = new TrieHybride(mot);
							((TrieHybride) fils_gauche).setPere(this);
						} else
							((TrieHybride) fils_gauche).ajoutDansTrie(mot);
						return this;
					} else {
						if (mot.length() == 1) {
							setIsMot();
							return this;
						}
						if (fils_central == null) {
							fils_central = new TrieHybride(mot.substring(1));
							((TrieHybride) fils_central).setPere(this);
						}

						else
							((TrieHybride) fils_central).ajoutDansTrie(mot
									.substring(1));
						return this;
					}
				}
			} else {
				return this;
			}
		}
	}

	/* Fusion de deux triehybrid */
	public static ITrieHybride Fusion(ITrieHybride premier, ITrieHybride second) {
		if (premier == null)
			return second;
		else if (second == null)
			return premier;
		else {
			if (premier.getKey() < second.getKey()) {
				if (second.getFilsGauche() == null) {
					second.setFilsGauche(premier);
					return second;
				} else {
					return new TrieHybride(second.getKey(),
							second.getFilsCentrale(), Fusion(premier,
									second.getFilsGauche()),
							second.getFilsDroit());
				}
			} else if (premier.getKey() > second.getKey()) {
				if (second.getFilsDroit() == null) {
					second.setFilsDroit(premier);
					return second;
				} else {
					return new TrieHybride(second.getKey(),
							second.getFilsCentrale(), second.getFilsGauche(),
							Fusion(premier, second.getFilsDroit()));
				}
			} else {
				ITrieHybride result = new TrieHybride(
						second.getKey(),
						Fusion(second.getFilsCentrale(),
								premier.getFilsCentrale()),
						Fusion(second.getFilsGauche(), premier.getFilsGauche()),
						Fusion(null, null));
				if (second.IsMot() || premier.IsMot())
					result.setIsMot();
				return result;
			}
		}
	}

	/* marche bien ! */
	public boolean search(String mot) {
		ArrayList<String> r = listWords();
		boolean b = false;
		if (mot.isEmpty())
			return b;
		if (r == null)
			b = false;
		else
			for (int i = 0; i <= r.size() - 1; i++) {
				if (r.get(i).equals(mot))
					b = true;
			}
		return b;
	}

	/*
	 * si mot est present alors compter recursivement depuis ttes les branches
	 * du trie
	 */
	@Override
	public int countWords() {

		if (isEmpty())
			return 0;
		else if (IsMot())
			return 1
					+ ((fils_gauche != null) ? fils_gauche.countWords() : 0)
					+ ((fils_central != null) ? fils_central.countWords() : 0)
					+ ((fils_droit != null) ? fils_droit.countWords() : 0);

		else
			return ((fils_gauche != null) ? fils_gauche.countWords() : 0)
					+ ((fils_central != null) ? fils_central.countWords() : 0)
					+ ((fils_droit != null) ? fils_droit.countWords() : 0);
	}

	@Override
	public ArrayList<String> listWords() {
		return ListeMot("");
	}

	@Override
	public ArrayList<String> ListeMot(String value) {
		ArrayList<String> liste = new ArrayList<String>();
		if (!isEmpty()) {
			if (fils_gauche != null)
				liste.addAll(fils_gauche.ListeMot(value));
			if (IsMot()) {
				liste.add(value + l);
				if (fils_central != null)
					liste.addAll(fils_central.ListeMot(value + l));
			} else {
				if (fils_central != null)
					liste.addAll(fils_central.ListeMot(value + l));
			}
			if (fils_droit != null)
				liste.addAll(fils_droit.ListeMot(value));
			return liste;
		} else

			return liste;

	}


	@Override
	public ITrieHybride delete(String mot) {
		TrieHybride t = new TrieHybride('\0', 0, null, null, null);
		if (!isEmpty()) {
			if (!mot.isEmpty())
				if (mot.charAt(0) < l) {

					if (fils_gauche != null) {
						t = new TrieHybride(l, valeur, fils_central,
								(ITrieHybride) fils_gauche.delete(mot),
								fils_droit);
					}
					return t;
				} else if (mot.charAt(0) > l) {
					if (fils_droit != null) {
						t = new TrieHybride(l, valeur, fils_central,
								fils_gauche,
								(ITrieHybride) fils_droit.delete(mot));
					}
					return t;
				} else {
					if (fils_central == null)
						return Fusion(fils_gauche, fils_droit);
					// union
					else {
						ITrieHybride filsApresSupression = (ITrieHybride) fils_central
								.delete(mot.substring(1));
						if (filsApresSupression == null)
							return Fusion(fils_gauche, fils_droit);
						// otherwise we can delete the node
						else {
							ITrieHybride tree = new TrieHybride(l,
									filsApresSupression, fils_gauche,
									fils_droit);
							if (IsMot())
								tree.setnotnode();
							// we have a word's representation in this
							// node so can not delete the node i.e we
							// have to keep the first letter of the
							// word
							return tree;
						}
					}
				}
			else {
				return null;

			}
		}

		else {
			return null;
		}
	}

	@Override
	public int countNull() {
		int x = 0;
		if (fils_gauche == null) {
			x++;
		} else {
			x += fils_gauche.countNull();
		}

		if (fils_central == null) {
			x++;
		} else {
			x += fils_central.countNull();
		}

		if (fils_droit == null) {
			x++;
		} else {
			x += fils_droit.countNull();
		}
		return x;
	}

	@Override
	public int height() {
		int h1 = ((fils_central != null) ? fils_central.height() : 0);
		int h2 = ((fils_gauche != null) ? fils_gauche.height() : 0);
		int h3 = ((fils_droit != null) ? fils_droit.height() : 0);
		int maxH1H2 = ((h1 > h2) ? h1 : h2);
		return 1 + ((h3 > maxH1H2) ? h3 : maxH1H2);
	}

	@Override
	public float averageDepth() {
		return (float) sommeProfondeur(0) / (float) nombreNoeuds();
	}

	@Override
	public int countPrefix(String mot) {

		if (isEmpty())
			return 0;
		if (mot.length() == 1)
			return countWords();
		if (mot.isEmpty()) {
			return countWords();

		} else {
			char c = mot.charAt(0);
			String s = mot.substring(1);

			if (c < l)
				return ((fils_gauche != null) ? fils_gauche.countPrefix(mot) : 0);
			else if (c > l)
				return ((fils_droit != null) ? fils_droit.countPrefix(mot) : 0);
			else
				return ((fils_central != null) ? fils_central.countPrefix(s) : 0);
		}

	}
	public boolean Recherche(String mot) {
		if (mot.isEmpty()) {
			return false;
		} else {
			if (l == mot.charAt(0)) {
				if (fils_central != null)
					return fils_central.Recherche(mot.substring(1));
				else {
					if (mot.length() == 1 && IsMot())
						return true;
					else
						return false;
				}
			} else {
				if (l < mot.charAt(0)) {
					if (fils_droit != null)
						return fils_droit.Recherche(mot);
					else
						return false;
				} else {
					if (fils_gauche != null)
						return fils_gauche.Recherche(mot);
					else
						return false;
				}
			}
		}
	}
	

	@Override
	public int nombreNoeuds() {
		int nbr=1;
		
	if(fils_gauche!=null)
		nbr=nbr+fils_gauche.nombreNoeuds();
	if(fils_droit!=null)
		nbr=nbr+fils_droit.nombreNoeuds();
	if(fils_central!=null)
		nbr=nbr+fils_central.nombreNoeuds();
	return nbr;
		
	}

	@Override
	public int sommeProfondeur(int profondeur) {
		return profondeur
				+ (fils_gauche != null ? fils_gauche
						.sommeProfondeur(profondeur + 1) : 0)
				+ (fils_central != null ? fils_central
						.sommeProfondeur(profondeur + 1) : 0)
				+ (fils_droit != null ? fils_droit
						.sommeProfondeur(profondeur + 1) : 0);
	}

	public static int hauteurSansFilsCentrale(ITrieHybride trie) {
		if (trie == null)
			return 0;
		return 1 + Math.max(hauteurSansFilsCentrale(trie.getFilsGauche()),
				hauteurSansFilsCentrale(trie.getFilsDroit()));
	}

	public static boolean isEquilibre(ITrieHybride trie) {
		if (trie == null)
			return true;
		else {
			if (trie.isEmpty())
				return true;
			else {
				if (isEquilibre(trie.getFilsCentrale())
						&& isEquilibre(trie.getFilsGauche())
						&& isEquilibre(trie.getFilsDroit())
						&& Math.abs(hauteurSansFilsCentrale(trie.getFilsDroit())
								- hauteurSansFilsCentrale(trie.getFilsGauche())) <= 1)
					return true;
				else
					return false;
			}
		}
	}

	// Just had a rough idea. This has to be reviewed
	// does not work i.e see where the method rotation is called

	public static ITrieHybride ajoutEquillibre(ITrieHybride trie, String mot) {
		FileRead f = new FileRead();
		String word = f.toWord(mot);
		return ajoutEq(trie, f.toWord(mot));
	}

	public static ITrieHybride ajoutEq(ITrieHybride trie, String mot) {
		//creation d'un trie hybride avc ajout d'un mot
				if (trie == null)
					return new TrieHybride(mot);
				else { // pareil
					if (trie.isEmpty())
						return new TrieHybride(mot);
					else {// retourne le trie car pas d'ajout
						if (mot.isEmpty()) {
							return trie;
						}
					}
					// c= la premiere lettre du mot et la valeur par defaut -1
					final char c = trie.getKey();
					int v = trie.getValeur();
					
					if (c == mot.charAt(0)) {
						// la lettre represente un mot et pas de changement ans le trie
						if (mot.length() == 1)
							v = 1;
						// ajout dans le fils central 
						return new TrieHybride(c, v, ajoutEq(trie.getFilsCentrale(),
								mot.substring(1)), trie.getFilsGauche(),
								trie.getFilsDroit());
						//sinon , ajout equilibre ds fils gauche
					} else if (c > mot.charAt(0)) {
						int nb = hauteurDG(trie.getFilsGauche() != null ? trie
								.getFilsGauche().getFilsDroit() : null);
						ITrieHybride newTrie = new TrieHybride(c, v,
								trie.getFilsCentrale(), ajoutEq(trie.getFilsGauche(),
										mot), trie.getFilsDroit());
						//si desequilibrage rotation
						if (hauteurDG(newTrie.getFilsGauche())
								- hauteurDG(newTrie.getFilsDroit()) > 1) {
							// Rotation
							if (hauteurDG(newTrie.getFilsGauche()
									.getFilsDroit()) - nb > 0) {
								//rotation gauche
								newTrie = RotationGauche(newTrie,
										newTrie.getFilsGauche());
							}
							//rotation droite
							newTrie = RotationDroit(newTrie, newTrie);
						}
						return newTrie;

					} else {
						int nb = hauteurDG(trie.getFilsDroit() != null ? trie
								.getFilsDroit().getFilsGauche() : null);
						ITrieHybride newTrie = new TrieHybride(c, v,
								trie.getFilsCentrale(), trie.getFilsGauche(), ajoutEq(
										trie.getFilsDroit(), mot));
						if (hauteurDG(newTrie.getFilsDroit())
								- hauteurDG(newTrie.getFilsGauche()) > 1) {
							if (hauteurDG(newTrie.getFilsDroit()
									.getFilsGauche()) - nb > 0) {
							// rotation droit
								newTrie = RotationDroit(newTrie, newTrie.getFilsDroit());
							}

							//rotation gauche
							newTrie = RotationGauche(newTrie, newTrie);
						}
						return newTrie;
					}
				}
	}
	
	public static int hauteurDG(ITrieHybride trie) {
		if (trie == null)
			return 0;
		return 1 + Math.max(hauteurDG(trie.getFilsGauche()),
				hauteurDG(trie.getFilsDroit()));
	}


	@Override
	public void affiche(String value) {
		if (!isEmpty()) {
			if (fils_gauche != null)
				fils_gauche.affiche(value);
			if (IsMot())
				System.out.println(value + l);
			if (fils_central != null)
				fils_central.affiche(value + l);
			if (fils_droit != null)
				fils_droit.affiche(value);
		}
	}

	

	// C.f Introduction to Algorithms 3rd Edition by THOMAS H. CORMEN
	// page 313
	public static ITrieHybride RotationGauche(ITrieHybride T, ITrieHybride x) {
		ITrieHybride y = x.getFilsDroit();
		x.setFilsDroit(y.getFilsGauche()); // fils droit de x reçois fils gauche de y
		if (y.getFilsGauche() != null)
			y.getFilsGauche().setPere(x); // link les parents de x to y
		y.setPere(x.getPere());
		if (x.getPere() == null) {
			T = y;
		}// return??
		else if (x == x.getPere().getFilsGauche()) {
			x.getPere().setFilsGauche(y);
		} else {
			x.getPere().setFilsDroit(y);

		}
		y.setFilsGauche(x); // x a la gauche de y 
		x.setPere(y); // linx parents de y to x
		return T;
	}

	// C.f Introduction to Algorithms 3rd Edition by THOMAS H. CORMEN
	// page 313
	public static ITrieHybride RotationDroit(ITrieHybride T, ITrieHybride x) {
		ITrieHybride y = x.getFilsGauche();
		x.setFilsGauche(y.getFilsDroit());
		if (y.getFilsDroit() != null)
			y.getFilsDroit().setPere(x);
		y.setPere(x.getPere());
		if (x.getPere() == null)
			T = y;
		else if (x == x.getPere().getFilsDroit())
			x.getPere().setFilsDroit(y);
		else
			x.getPere().setFilsGauche(y);
		y.setFilsDroit(x);
		x.setPere(y);
		return T;
	}
	
	
	/*Fonction permettant une visualisation d'un trie hybribe grace a GNUPLOT*/
	public void plotTrie(){	
		tmpPlot(this, 0, 0); 
	}
	
	private static void tmpPlot(ITrieHybride Trie ,int X, int Y){
		 if(Trie == null)
			    return ;
		 
			  System.out.println(Trie.getKey()+" "+X+" "+Y);
			  tmpPlot(Trie.getFilsGauche(), X - (widthL(Trie.getFilsCentrale()) + widthR(Trie.getFilsGauche()) + 1) * 10, Y + 10);
			  
			  System.out.println(Trie.getKey()+" "+X+" "+Y);
			  tmpPlot(Trie.getFilsCentrale(), X, Y + 10);
			  
			  System.out.println(Trie.getKey()+" "+X+" "+Y);
			  
			  tmpPlot(Trie.getFilsDroit(), X + (widthR(Trie.getFilsCentrale()) + widthL(Trie.getFilsDroit())+ 1) * 10, Y +10);
			  System.out.println();
	}
	
	private static int width (ITrieHybride iTrieHybride){
	  if (iTrieHybride == null) return 0;
	  else
	    {
	      if (iTrieHybride.getFilsGauche() == null && iTrieHybride.getFilsCentrale() == null && iTrieHybride.getFilsDroit() == null) 
	    	  return 1;
	      else 
	    	  return (width(iTrieHybride.getFilsGauche()) + width(iTrieHybride.getFilsCentrale()) + width(iTrieHybride.getFilsDroit()));
	    }
	}

	private static int widthR(ITrieHybride iTrieHybride){
	  if (iTrieHybride == null) return 0;
	  else return widthR(iTrieHybride.getFilsCentrale()) + width(iTrieHybride.getFilsDroit());
	}

	private static int widthL(ITrieHybride iTrieHybride){
	  if (iTrieHybride == null) return 0;
	  else return widthL(iTrieHybride.getFilsCentrale()) + width(iTrieHybride.getFilsDroit());
	}
	 
}
