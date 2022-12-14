package com.microdb.microdb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.FieldError;
import org.hibernate.exception.ConstraintViolationException;
import com.microdb.microdb.bean.db.CellPhoneDb;
import com.microdb.microdb.bean.db.UserDb;
import com.microdb.microdb.bean.input.CellPhone;
import com.microdb.microdb.bean.input.User;
import com.microdb.microdb.bean.output.Respuesta;
import com.microdb.microdb.exception.MyResourceNotFoundException;
import com.microdb.microdb.repository.CellPhoneRepository;
import com.microdb.microdb.repository.UserRepository;



@RestController// This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

	@Autowired
	private CellPhoneRepository cellPhoneRepository;

    //@RequestParam(required = false) Parametro no requerido
    @PostMapping(path = "/add") // Map ONLY POST Requests
    @ExceptionHandler(DataIntegrityViolationException.class)
    public @ResponseBody Respuesta addNewUser(@RequestParam String name, @RequestParam String email, @RequestParam String documentNumber) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        UserDb n = new UserDb();
        n.setName(name);
        n.setEmail(email);
        n.setDocumentNumber(documentNumber);
        Respuesta respuesta = new Respuesta();
        try {
            userRepository.save(n);
            respuesta.setRespuesta("Usuario Guardado");
        } catch (DataIntegrityViolationException   e) {
            respuesta.setRespuesta("Usuario no fue guardado");
            respuesta.setError(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        }

        return respuesta;

    }

    @PostMapping(value = "/add2", consumes =  {MediaType.APPLICATION_JSON_VALUE}, produces =  {MediaType.APPLICATION_JSON_VALUE})
    @ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody UserDb addNewUser2 (
		@Valid
		@RequestBody User user
	) throws MethodArgumentNotValidException {
		CellPhone cellPhone = user.getCellPhoneId();
		CellPhoneDb cellPhoneDb = new CellPhoneDb();
		if(cellPhone != null){
			cellPhoneDb.setBrand(cellPhone.getBrand());
			cellPhoneDb.setModel(cellPhone.getModel());
			cellPhoneDb.setNumber(cellPhone.getNumber());
			cellPhoneRepository.save(cellPhoneDb);
		}
		
		UserDb userDb = new UserDb();
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setDocumentNumber(user.getDocumentNumber());
		if(cellPhone != null){
			userDb.setCellPhoneId(cellPhoneDb);
		}
		return userRepository.save(userDb);
        

    }

    @GetMapping(value = "/find", consumes =  MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Optional<UserDb> getUser(@RequestBody User user) {
        Optional<UserDb> a = userRepository.findById(user.getId());
		return a;

    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserDb> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	
}