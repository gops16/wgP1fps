/**
 * 
 */
package fte.rascan.service.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.State;
import fte.rascan.dao.SchedulesDao;
import fte.rascan.entity.Schedules;
import fte.rascan.service.ScheduleService;

/**
 * @author ferox
 *
 */
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
