package mvcdao;

import java.util.List;

import domain.VCD;

public interface VCDDAO {
	public List<VCD> getAll();
	
	public void save(VCD vcd);
	
	public VCD get(Integer id);
	
	public void delete(Integer id);
	
	public void update(VCD vcd);
	
	/**
	 * 返回和 name 相等的记录数. 
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);

	public  List<VCD> getWithNo(String no);

	public List<VCD> getForListWithCriteriaVCD(CriteriaVCD cc);
	
	public void rent(VCD vcd);
	
	public void returnback(VCD vcd);
}
