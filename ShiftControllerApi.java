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
