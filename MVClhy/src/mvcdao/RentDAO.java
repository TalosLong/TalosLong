package mvcdao;

import java.util.List;

import domain.Rent;
import domain.VCD;

public interface RentDAO {
public List<Rent> getAll();
	
	public void save(Rent rent);
	
	public Rent get(Integer id);

}
