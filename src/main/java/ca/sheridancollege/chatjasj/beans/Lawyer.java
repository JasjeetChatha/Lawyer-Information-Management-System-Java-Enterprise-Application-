package ca.sheridancollege.chatjasj.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lawyer {
	private int id;
	private String name;
	private int yearsExperience;
	private double salary;
}
