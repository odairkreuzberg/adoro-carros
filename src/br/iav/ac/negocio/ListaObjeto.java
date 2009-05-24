package br.iav.ac.negocio;

public class ListaObjeto {
	
	private Objeto obj;
	private ListaObjeto prox;

	public ListaObjeto() {
		obj = new Objeto();
		prox = null;
	}

	public ListaObjeto(Objeto obj) {
		this.obj = obj;
		prox = null;
	}
	
	public Objeto getObjeto() {
		return obj;
	}

	public void insertWhitoutPersist(Objeto obj){
		ListaObjeto aux;
		if (prox == null) {
			prox = new ListaObjeto(obj);
		} else {
			if (prox.obj.getCodigo() < obj.getCodigo()) {
				prox.insertWhitoutPersist(obj);
			} else {
				aux = new ListaObjeto(obj);
				aux.prox = prox;
				prox = aux;
			}
		}
	}

	public Objeto getObjeto(int i){
		if ((prox.obj != null) && (i == 0)) {
			return prox.obj;
		}
		if (prox.prox != null) {
			return prox.getObjeto(i-1);
		} else {
			return null;
		}
	}

	private int listSize(int i){
		if (prox != null) {
			return prox.listSize(i+1);
		} else {
			return i;
		}
	}
	
	
	public int getSize(){
		return listSize(0);
	}
	
	public void insert(Objeto obj){
		ListaObjeto aux;
		if (prox == null) {
			prox = new ListaObjeto(obj);
			obj.insert();
		} else {
			if (prox.obj.getCodigo() < obj.getCodigo()) {
				prox.insert(obj);
			}
			else {
				aux = new ListaObjeto(obj);
				obj.insert();
				aux.prox = prox;
				prox = aux;
			}
		}
	}

	public int delete(Objeto obj){
		ListaObjeto aux;
		if (prox == null) {
			return 0;
		} else {
			if (prox.obj.getCodigo() == obj.getCodigo()) {
				aux = prox.prox;
				prox = aux;
				obj.delete();
				return 1;
			} else {
				return prox.delete(obj);
			}
		}
	}

	public Objeto search(Objeto obj){
		if (prox == null) {
			return null;
		}
		else {
			if (prox.obj.getCodigo() == obj.getCodigo()) {
				return prox.obj;
			} else {
				return prox.search(obj); 
			}
		}
	}
	
	public int edit(Objeto obj){
		if (prox == null) {
			return 0;
		}
		else {
			if (prox.obj.getCodigo() == obj.getCodigo()) {
				prox.obj = (Objeto) obj.clone();
				prox.obj.edit();
				return 1;
			} else {
				return prox.edit(obj);
			}
		}
		
	}
	
}