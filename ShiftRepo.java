@Repository
@Transactional
public class ShiftRepo implements ShiftDao {
	
    @Autowired SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSessionFactory() {
		return this.sessionFactory.openSession();
	}
	
	public List<Shifts> get() {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Shifts> criteria = builder.createQuery(Shifts.class);
		
		Root<Shifts> root = criteria.from(Shifts.class);
		criteria.select(root).orderBy(builder.asc(root.get("created_at")));
		List<Shifts> shiftsList = session.createQuery(criteria).getResultList();
		session.close();
		return shiftsList;
	}

	public Optional<Shifts> get(UUID args0) {
		Session session = this.getSessionFactory();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Shifts> criteria = builder.createQuery(Shifts.class);
		Root<Shifts> root = criteria.from(Shifts.class);
		criteria.select(root).where(builder.equal(root.get("id"), args0));
		
		try { 
			Shifts shifts = session.createQuery(criteria).getSingleResult();
			session.close();
			return Optional.of(shifts);
		} catch (NoResultException nre) {
			session.close();
			return Optional.empty();
		}
	}
	
	public List<State> saveOrUpdate(Shifts shifts) {
		Session session = this.getSessionFactory();
		Transaction tx = session.getTransaction();

		List<State> state = new ArrayList<State>();
		
		try {
			tx.begin();
			session.saveOrUpdate(shifts);	
			tx.commit();
			state.add(new Successful());
		} catch (ConstraintViolationException e) {
			e.getConstraintViolations().stream().forEach(o -> state.add(new Errors().builder(o)));
			tx.rollback();
		} finally {
			session.close();
		}
		return state;
	}

	@Override
	public Page<Shifts> get(Integer first, Integer max) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Shifts> criteria = builder.createQuery(Shifts.class);
		
		CriteriaQuery<Long> total = builder.createQuery(Long.class);
		total.select(builder.count(total.from(Shifts.class)));

		Root<Shifts> root = criteria.from(Shifts.class);
		criteria.select(root).orderBy(builder.asc(root.get("created_at")));
		
		Page<Shifts> page = new Page<Shifts>();
		page.setFrom(first);
		page.setTo(max);
		page.setResults(session.createQuery(criteria).setFirstResult(first).setMaxResults(max).getResultList());
		page.setTotal(session.createQuery(total).getSingleResult());
		session.close();
		return page;
	}

	@Override
	public List<State> delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
}
