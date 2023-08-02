package web.member.dao.impl;

import static core.util.CommonUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.member.dao.MemberDao;
import web.member.pojo.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insert(Member member) {
		final String sql = "insert into MEMBER(USERNAME, PASSWORD, NICKNAME, ROLE_ID) " + "values(?, ?, ?, ?)";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getNickname());
			pstmt.setInt(4, member.getRoleId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteById(Integer id) {
		final String sql = "delete from MEMBER where ID = ?";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Member member) {
		final StringBuilder sql = new StringBuilder()
			.append("update MEMBER set ");
		int offset = 0;
		final String password = member.getPassword();
		if (password != null && !password.isEmpty()) {
			sql.append("PASSWORD = ?,");
			offset = 1;
		}
		sql.append("NICKNAME = ?,")
			.append("PASS = ?,")
			.append("ROLE_ID = ?,")
			.append("UPDATER = ?,")
			.append("LAST_UPDATED_DATE = NOW() ")
			.append("where USERNAME = ?");
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString())
		) {
			if (password != null && !password.isEmpty()) {
				pstmt.setString(1, member.getPassword());
			}
			pstmt.setString(1 + offset, member.getNickname());
			pstmt.setBoolean(2 + offset, member.getPass());
			pstmt.setInt(3 + offset, member.getRoleId());
			pstmt.setString(4 + offset, member.getUpdater());
			pstmt.setString(5 + offset, member.getUsername());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Member selectById(Integer id) {
		final String sql = "select * from MEMBER where ID = ?";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (
				ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setId(rs.getInt("ID"));
					member.setUsername(rs.getString("USERNAME"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setNickname(rs.getString("NICKNAME"));
					member.setPass(rs.getBoolean("PASS"));
					member.setRoleId(rs.getInt("ROLE_ID"));
					member.setCreator(rs.getString("CREATOR"));
					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
					member.setUpdater(rs.getString("UPDATER"));
					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		final String sql = "select * from MEMBER order by ID";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			List<Member> list = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("ID"));
				member.setUsername(rs.getString("USERNAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setNickname(rs.getString("NICKNAME"));
				member.setPass(rs.getBoolean("PASS"));
				member.setRoleId(rs.getInt("ROLE_ID"));
//				member.setCreator(rs.getString("CREATOR"));
//				member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
//				member.setUpdater(rs.getString("UPDATER"));
//				member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Member selectByUsername(String username) {
		final String sql = "select * from MEMBER where USERNAME = ?";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			try (
				ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setId(rs.getInt("ID"));
					member.setUsername(rs.getString("USERNAME"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setNickname(rs.getString("NICKNAME"));
					member.setPass(rs.getBoolean("PASS"));
					member.setRoleId(rs.getInt("ROLE_ID"));
					member.setCreator(rs.getString("CREATOR"));
					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
					member.setUpdater(rs.getString("UPDATER"));
					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Member selectForLogin(String username, String password) {
		final String sql = "select * from MEMBER where USERNAME = ? and PASSWORD = ?";
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			try (
				ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setId(rs.getInt("ID"));
					member.setUsername(rs.getString("USERNAME"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setNickname(rs.getString("NICKNAME"));
					member.setPass(rs.getBoolean("PASS"));
					member.setRoleId(rs.getInt("ROLE_ID"));
					member.setCreator(rs.getString("CREATOR"));
					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
					member.setUpdater(rs.getString("UPDATER"));
					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
