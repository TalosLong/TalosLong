package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.VCD;

import daoimpl.VCDDAOJdbcImpl;
import domain.VCD;
import mvcdao.VCDDAO;

class VCDDAOJdbcImplTest {
	private VCDDAO vcddao = new VCDDAOJdbcImpl();

	@Test
	public void testSave() {
		VCD vcd = new VCD("100","110","10","10","10","100");
		vcddao.save(vcd);
	}
//	
//	@Test
//	public void testGetAll() {
//		List<VCD> vcd = vcddao.getAll();
//		System.out.println(vcd); 
//	}
	
//	@Test
//	public void testget() {
//		VCD vcd = vcddao.get(10);
//		System.out.println(vcd.getVNo());
//	}
	
//	@Test
//	public void test() {
//		
//	}
}
