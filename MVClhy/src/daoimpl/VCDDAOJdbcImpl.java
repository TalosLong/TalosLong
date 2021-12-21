package daoimpl;

import java.util.List;

import mvcdao.CriteriaVCD;
import mvcdao.DAO;
import domain.VCD;
import mvcdao.VCDDAO;

public class VCDDAOJdbcImpl extends DAO<VCD> implements VCDDAO{

	@Override
	public List<VCD> getAll() {
		String sql="select * from vcd";
		return getForList(sql);
	}

	@Override
	public void save(VCD vcd) {
		String sql = "insert into vcd values(?,?,?,?,?,?)";
		update(sql,vcd.getVNo(),vcd.getVRent(),vcd.getVSale(),vcd.getVStatue(),vcd.getVName(),vcd.getVDuration());		
	}

	@Override
	public VCD get(Integer id) {
		String sql = "select VNo,VRent,VSale,VStatue,VName,VDuration from vcd where VNo=?";
		return get(sql,id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from vcd where VNo=?";
		update(sql,id);
	}

	@Override
	public void update(VCD vcd) {
		String sql = "update vcd set VRent=?, VSale = ?, VStatue = ?,VName = ?,VDuration = ?"+"where VNo=?";
		update(sql,vcd.getVNo(),vcd.getVRent(),vcd.getVSale(),vcd.getVStatue(),vcd.getVName(),vcd.getVDuration(),vcd.getVNo());
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "SELECT count(VNo) FROM vcd WHERE VName = ?";
		return getForValue(sql,name);
	}

	@Override
	public List<VCD> getForListWithCriteriaVCD(CriteriaVCD cc) {
		String sql = "SELECT * FROM vcd WHERE VNo LIKE ? AND VStatue LIKE ? AND VName LIKE ? ";
		//修改了 CriteriaVCD 的 getter 方法: 使其返回的字符串中有 "%%".
		//若返回值为 null 则返回 "%%", 若不为 null, 则返回 "%" + 字段本身的值 + "%"
		return getForList(sql,cc.getVNo(),cc.getVStatue(),cc.getVName());
	}

	@Override
	public void rent(VCD vcd) {
		String sql = "update vcd set VStatue=0 where VNo=?";
		update(sql,vcd.getVNo());
	}
	
	public void returnback(VCD vcd) {
		String sql = "update vcd set VStatue=1 where VNo=?";
		update(sql,vcd.getVNo());
	}

	@Override
	public List<VCD> getWithNo(String no) {
		String sql = "select * from vcd where VNo=? and VStatue =1";
		return getForList(sql,no);
	}

}
