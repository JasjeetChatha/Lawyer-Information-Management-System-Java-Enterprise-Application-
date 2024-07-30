package ca.sheridancollege.chatjasj.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ca.sheridancollege.chatjasj.beans.User;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepository {
	private NamedParameterJdbcTemplate jdbc;

	public User findUserByUsername(String username) {
	    MapSqlParameterSource parameter = new MapSqlParameterSource();
	    String query = "SELECT * FROM SEC_USER WHERE userName = :un";
	    parameter.addValue("un", username);
	    ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameter,
	            new BeanPropertyRowMapper<>(User.class));
	    if (users.size() > 0)
			return users.get(0);
		jdbc.update(query, parameter);
		return null;
	}


	public List<String> getRolesById(long userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName " + "FROM user_role, sec_role WHERE "
				+ "user_role.roleId=sec_role.roleId and userId=:id";
		parameters.addValue("id", userId);
		ArrayList<String> roles = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String) row.get("roleName"));
		}
		return roles;
	}

	public void register(String username, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "insert into SEC_User (userName, encryptedPassword, ENABLED) values (:un, :pw, 1)";
		parameter.addValue("un", username);
		parameter.addValue("pw", encodedPassword);
		jdbc.update(query, parameter);
	}

	public void assignRoles(long userid, long roleid) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)" + "values (:ri, :ui)";
		parameter.addValue("ri", userid);
		parameter.addValue("ui", roleid);
		jdbc.update(query, parameter);
	}

	public List<User> getAllUsers() {
		String query = "SELECT * FROM SEC_USER ORDER BY userName";
		return jdbc.query(query, new BeanPropertyRowMapper<>(User.class));
	}

	public User getUserById(int id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "Select * FROM SEC_User WHERE userId=:users";
		parameter.addValue("users", id);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameter,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}
	public String getUserNameByUserId(long userId) {
	    MapSqlParameterSource parameter = new MapSqlParameterSource();
	    String query = "SELECT userName FROM SEC_USER WHERE userId = :id";
	    parameter.addValue("id", userId);

	    List<String> userNames = jdbc.queryForList(query, parameter, String.class);
	    if (!userNames.isEmpty()) {
	        return userNames.get(0);
	    }

	    return null;
	}

}
