/**
 * 
 */
package fte.rascan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.DataTableResponse;
import fte.api.Page;
import fte.api.State;
import fte.rascan.entity.Shifts;
import fte.rascan.service.ShiftService;

/**
 * @author genzzz
 *
 */
@RequestMapping(value = "/api")
@RestController
public class ShiftControllerApi {
	
	@Autowired ShiftService service;
	
	@GetMapping("/shift")
	public @ResponseBody DataTableResponse<Shifts> get() {
		DataTableResponse<Shifts> dtr = new DataTableResponse<Shifts>();
		dtr.setData(service.get());
		dtr.setSuccess(true);
		return dtr;
	}
	
	@PutMapping("/shift")
	public @ResponseBody ResponseEntity<?> addOrUpdate(@Valid @RequestBody Shifts Shift, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			List<State> state = service.saveOrUpdate(Shift);
			return ResponseEntity.ok().body(state);	
		} catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getClass().getName());

			return ResponseEntity.badRequest().body("{ status: 500, message:Bad datas!}");	
		}
	}
	
	@GetMapping("/shift/{first}/{max}")
	public @ResponseBody Page<?> page(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		return service.get(first, max);
	}
}
