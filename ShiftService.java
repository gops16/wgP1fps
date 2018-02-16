/**
 * 
 */
package fte.rascan.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fte.api.UniversalCRUD;
import fte.rascan.entity.Shifts;

/**
 * @author genzzz
 *
 */
@Service
public interface ShiftService extends UniversalCRUD<Shifts, UUID> {}
