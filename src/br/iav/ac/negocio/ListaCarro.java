package br.iav.ac.negocio;

public class ListaCarro {

	private Carro car;
	private ListaCarro prox;

	public ListaCarro() {
		car = new Carro();
		prox = null;
	}

	public ListaCarro(Carro car) {
		this.car = car;
		prox = null;
	}
	
	public Carro getCarro() {
		return car;
	}

	public void insertWhitoutPersist(Carro car){
		ListaCarro aux;
		if (prox == null) {
			prox = new ListaCarro(car);
		} else {
			if (prox.car.getCodigo() < car.getCodigo()) {
				prox.insertWhitoutPersist(car);
			} else {
				aux = new ListaCarro(car);
				aux.prox = prox;
				prox = aux;
			}
		}
	}

	public Carro getCarro(int i){
		if ((prox.car != null) && (i == 0)) {
			return prox.car;
		}
		if (prox.prox != null) {
			return prox.getCarro(i-1);
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
	
	public void insert(Carro car){
		ListaCarro aux;
		if (prox == null) {
			prox = new ListaCarro(car);
			car.insert();
		} else {
			if (prox.car.getCodigo() < car.getCodigo()) {
				prox.insert(car);
			}
			else {
				aux = new ListaCarro(car);
				car.insert();
				aux.prox = prox;
				prox = aux;
			}
		}
	}

	public int delete(Carro car){
		ListaCarro aux;
		if (prox == null) {
			return 0;
		} else {
			if (prox.car.getCodigo() == car.getCodigo()) {
				aux = prox.prox;
				prox = aux;
				car.delete();
				return 1;
			} else {
				return prox.delete(car);
			}
		}
	}

	public Carro search(Carro car){
		if (prox == null) {
			return null;
		}
		else {
			if (prox.car.getCodigo() == car.getCodigo()) {
				return prox.car;
			} else {
				return prox.search(car); 
			}
		}
	}
	
	public int edit(Carro car){
		if (prox == null) {
			return 0;
		}
		else {
			if (prox.car.getCodigo() == car.getCodigo()) {
				prox.car = (Carro) car.clone();
				prox.car.edit();
				return 1;
			} else {
				return prox.edit(car);
			}
		}
		
	}
	
}