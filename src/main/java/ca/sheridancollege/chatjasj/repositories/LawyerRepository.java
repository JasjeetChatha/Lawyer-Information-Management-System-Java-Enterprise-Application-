package ca.sheridancollege.chatjasj.repositories;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.chatjasj.beans.Lawyer;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LawyerRepository {
	private NamedParameterJdbcTemplate jdbc;

	public void addLawyer(Lawyer lw) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "INSERT INTO Lawyer ( name, yearsExperience, salary) " + "VALUES (:na, :ye, :sa)";

		parameter.addValue("na", lw.getName());
		parameter.addValue("ye", lw.getYearsExperience());
		parameter.addValue("sa", lw.getSalary());

		jdbc.update(query, parameter);
	}

	public ArrayList<Lawyer> getLawyer() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "Select * FROM Lawyer";
		ArrayList<Lawyer> lawyers = (ArrayList<Lawyer>) jdbc.query(query, parameter,
				new BeanPropertyRowMapper<Lawyer>(Lawyer.class));
		return lawyers;
	}

	public Lawyer getLawyerById(int id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "Select * FROM Lawyer WHERE id=:lawyer";
		parameter.addValue("lawyer", id);
		ArrayList<Lawyer> lawyers = (ArrayList<Lawyer>) jdbc.query(query, parameter,
				new BeanPropertyRowMapper<Lawyer>(Lawyer.class));
		if (lawyers.size() > 0)
			return lawyers.get(0);
		else
			return null;
	}

	

	public void deleteLawyer(Lawyer lw) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "DELETE Lawyer WHERE id = :lawyer";
		parameter.addValue("lawyer", lw.getId());
		jdbc.update(query, parameter);
	}

}
