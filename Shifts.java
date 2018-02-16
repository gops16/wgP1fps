@Entity
@Table(name="SHIFTS")
public class Shifts {
	
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator( name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@Column(name="ID", updatable = false, nullable = false)
	private UUID id;
	
	@ManyToOne
	@JsonIgnoreProperties("shifts")
	@JoinColumn(name="HOME_ID", insertable=false, updatable=false)
	private Homes homes;
	
	@NotNull
	@Column(name="HOME_ID", nullable=false)
	private UUID home_id;
	
	@ManyToOne
	@JsonIgnoreProperties("schedules")
	@JoinColumn(name="SCHEDULE_ID", insertable=false, updatable=false)
	private Schedules schedules;
	
	@NotNull
	@Column(name="SCHEDULE_ID", nullable=false)
	private UUID schedule_id;
	
	@NotNull
	@Size(min=1, max=32)
	@Column(name="NAME", columnDefinition="varchar(32)", nullable=false)
	private String name;
	
	@NotNull
	@Size(min=1, max=5)
	@Column(name="SHIFT_MARK", columnDefinition="varchar(5)",  nullable=false)
	private String shift_mark;

	@Column(name="NOTE", columnDefinition="varchar(250)", nullable=true)
	private String note;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_AT", nullable = false, updatable=false)
	private Date created_at;

	@NotNull
	@Digits(fraction = 0, integer = 99999)
	@Column(name="CREATED_BY",updatable = false)
	private Integer created_by;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_AT")
	private Date updated_at;
	
	@NotNull
	@Digits(fraction = 0, integer = 99999)
	@Column(name="UPDATED_BY", nullable = false)
	private Integer updated_by;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Homes getHomes() {
		return homes;
	}

	public void setHomes(Homes homes) {
		this.homes = homes;
	}

	public UUID getHome_id() {
		return home_id;
	}

	public void setHome_id(UUID home_id) {
		this.home_id = home_id;
	}
	public Schedules getSchedules() {
		return schedules;
	}

	public void setSchedules(Schedules schedules) {
		this.schedules = schedules;
	}

	public UUID getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(UUID schedule_id) {
		this.schedule_id = schedule_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShift_mark() {
		return shift_mark;
	}

	public void setShift_mark(String shift_mark) {
		this.shift_mark = shift_mark;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

}
