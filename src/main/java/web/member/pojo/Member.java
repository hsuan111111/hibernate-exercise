package web.member.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Core {
	private static final long serialVersionUID = 1062017833925367218L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String nickname;
	@Column(insertable = false)
	private Boolean pass;
	@Column(name = "ROLE_ID")
	private Integer roleId;
	@Column(insertable = false)
	private String creator;
	@Column(name = "CREATED_DATE", insertable = false)
	private Timestamp createdDate;
	@Column(insertable = false)
	private String updater;
	@Column(name = "LAST_UPDATED_DATE", insertable = false)
	private Timestamp lastUpdatedDate;

	
}
