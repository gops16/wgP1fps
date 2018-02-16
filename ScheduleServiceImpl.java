@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired SchedulesDao scheduleDao;

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#get()
	 */
	@Override
	public List<Schedules> get() {
		return scheduleDao.get();
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#get(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<Schedules> get(Integer first, Integer max) {
		return scheduleDao.get(first, max);
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#get(java.lang.Object)
	 */
	@Override
	public Optional<Schedules> get(UUID id) {
		return scheduleDao.get(id);
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public List<State> saveOrUpdate(Schedules arg0) {
		return scheduleDao.saveOrUpdate(arg0);
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#delete(java.lang.Object)
	 */
	@Override
	public List<State> delete(UUID id) {
		return null;
	}

}
