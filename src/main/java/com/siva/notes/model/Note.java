package com.siva.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "note")
public class Note extends BaseModel {
	
	@Column
	@NotNull
	@NotBlank(message = "Title is mandatory")
	private String title;
	
	@Column
	@NotNull
	@NotBlank(message = "Description is mandatory")
	private String description;
	
	

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(BaseModel baseModel) {
		super(baseModel);
		// TODO Auto-generated constructor stub
	}
	
	

	public Note(@NotNull @NotBlank(message = "Title is mandatory") String title,
			@NotNull @NotBlank(message = "Description is mandatory") String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
