// DAO(Data Access Object)

package dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//SqlSession 객체 생성
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);			// auto commit0111111111111111111
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public int insert(MemberDTO member) throws Exception {
		int result = 0;
		SqlSession session = getSession();
		result = session.insert("insert", member);
		System.out.println("result = " + result);
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = getConnection();
//			String sql = "insert into member0609 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, member.getId());
//			pstmt.setString(2, member.getPasswd());
//			pstmt.setString(3, member.getName());
//			pstmt.setString(4, member.getJumin1());
//			pstmt.setString(5, member.getJumin2());
//			pstmt.setString(6, member.getMailid());
//			pstmt.setString(7, member.getDomain());
//			pstmt.setString(8, member.getTel1());
//			pstmt.setString(9, member.getTel2());
//			pstmt.setString(10, member.getTel3());
//			pstmt.setString(11, member.getPhone1());
//			pstmt.setString(12, member.getPhone2());
//			pstmt.setString(13, member.getPhone3());
//			pstmt.setString(14, member.getPost());
//			pstmt.setString(15, member.getAddress());
//			pstmt.setString(16, member.getGender());
//			pstmt.setString(17, member.getHobby());
//			pstmt.setString(18, member.getIntro());
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
//		}
			
		return result;
	}
	
	// ID 중복검사
	public int idcheck(String id) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		MemberDTO member = session.selectOne("idcheck",id);
		if(member != null) {					// 중복 ID
			result = 1;
		}else {							// 사용 가능한 ID
			result = -1;
		}
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = getConnection();
//			
//			String sql = "select * from member0609 where id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {					// 중복 ID
//				result = 1;
//			}else {							// 사용 가능한 ID
//				result = -1;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
//		}
		
		return result;
	}
	
	// 로그인(회원 인증)
	public int memberAuth(String id, String passwd) throws Exception {
		int result = 0;
		SqlSession session = getSession();
		MemberDTO member = session.selectOne("idcheck", id);
		if(member != null) {	// 중복ID, id 인증 성공
			if(member.getPasswd().equals(passwd)) {	// passwd 인증 성공
				result = 1;	   	// 회원인증 성공
			}else {
				result = -1;	// 회원인증 성공
			}			
		}	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = getConnection();
//			String sql = "select * from member0609 where id=? and passwd=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, passwd);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {			// 인증 성공
//				result = 1;
//			}else {					// 인증 실패
//				result = -1;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
//		}
		return result;
	}
	
	// 회원 정보 구하기
	public MemberDTO getMember(String id) throws Exception{
//		MemberDTO member = new MemberDTO();
		SqlSession session = getSession();
		MemberDTO member = session.selectOne("idcheck", id);
		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = getConnection();
//			String sql = "select * from member0609 where id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				member.setId(rs.getString("id"));
//				member.setPasswd(rs.getString("passwd"));
//				member.setName(rs.getNString("name"));
//				member.setJumin1(rs.getString("jumin1"));
//				member.setJumin2(rs.getString("jumin2"));
//				member.setMailid(rs.getString("mailid"));
//				member.setDomain(rs.getString("domain"));
//				member.setTel1(rs.getString("tel1"));
//				member.setTel2(rs.getString("tel2"));
//				member.setTel3(rs.getString("tel3"));
//				member.setPhone1(rs.getString("phone1"));
//				member.setPhone2(rs.getString("phone2"));
//				member.setPhone3(rs.getString("phone3"));
//				member.setPost(rs.getString("post"));
//				member.setAddress(rs.getString("address"));
//				member.setGender(rs.getString("gender"));
//				member.setHobby(rs.getString("hobby"));
//				member.setIntro(rs.getString("intro"));
//				member.setRegister(rs.getTimestamp("register"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
//		}
		return member;
	}
	
	// 회원정보 수정
	public int update(MemberDTO member) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = session.update("update",member);
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = getConnection();
//			String sql = "update member0609 set name=?, jumin1=?, jumin2=?,"
//					+ " mailid=?, domain=?, tel1=?, tel2=?, tel3=?,"
//					+ " phone1=?, phone2=?, phone3=?, post=?, address=?,"
//					+ " gender=?, hobby=?, intro=? where id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, member.getName());
//			pstmt.setString(2, member.getJumin1());
//			pstmt.setString(3, member.getJumin2());
//			pstmt.setString(4, member.getMailid());
//			pstmt.setString(5, member.getDomain());
//			pstmt.setString(6, member.getTel1());
//			pstmt.setString(7, member.getTel2());
//			pstmt.setString(8, member.getTel3());
//			pstmt.setString(9, member.getPhone1());
//			pstmt.setString(10, member.getPhone2());
//			pstmt.setString(11, member.getPhone3());
//			pstmt.setString(12, member.getPost());
//			pstmt.setString(13, member.getAddress());
//			pstmt.setString(14, member.getGender());
//			pstmt.setString(15, member.getHobby());
//			pstmt.setString(16, member.getIntro());
//			pstmt.setString(17, member.getId());
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}			
//		}
		return result;
	}
	// 회원탈퇴
	public int delete(String id) throws Exception {
		int result = 0;
		SqlSession session = getSession();
		result = session.delete("delete", id);
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = getConnection();
//			String sql = "delete from member0609 where id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			result = pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
//			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}			
//		}
		return result;
	}
	
	

}
