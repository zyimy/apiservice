package com.probando.apiService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.probando.apiService.ApiRepository;
import com.probando.apiService.modelo.Tarea;

@RestController
@RequestMapping("/tareas")
public class ApiController {

	
	
	@Autowired
	private ApiRepository tr;
	
	@GetMapping
	List<Tarea> index(){
		
		return tr.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Tarea create(@RequestBody Tarea tarea) {
		return tr.save(tarea);
	}
	
	@GetMapping(value = {"/{id}"})
	public Optional<Tarea> listarId(@PathVariable("id")Long id) {
		Optional<Tarea> tarea = tr.findById(id);
		return tarea;
	}
	
	@PutMapping(value = "{id}")
	public Tarea update(@PathVariable Long id, @RequestBody Tarea tarea) {
		
		Tarea fromDbTarea = tr
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		fromDbTarea.setNombre(tarea.getNombre());
		fromDbTarea.setCompletado(tarea.isCompletado());
		
		return tr.save(fromDbTarea);
		
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable Long id) {
		Tarea tarea = tr
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		tr.delete(tarea);
	}

}
