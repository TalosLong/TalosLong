package daoimpl;

import java.util.List;

import domain.Rent;
import domain.VCD;
import mvcdao.DAO;
import mvcdao.RentDAO;

public class RentDAOJdbcImpl extends DAO<Rent> implements RentDAO{

	@Override
	public List<Rent> getAll() {
		String sql = "select * from rent";
		return getForList(sql);
	}

	@Override
	public void save(Rent rent) {
		String sql = "insert into rent values (?,?,?)";
		update(sql,rent.getRDate(),rent.getRTime(),rent.getRFair());
	}

	@Override
	public Rent get(Integer id) {
		String sql = "select * from rent where VNo=?";
		return get(sql,id);
	}
	
}
