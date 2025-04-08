package com.revisao.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "cadastrar")
	 // Define que o método abaixo será executado quando houver uma requisição POST para o endpoint "/cadastrar"
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO dto){
		
		//utilizado para salvar o usuario após cadastrar
		dto = usuarioService.salvarUsuario(dto);
		return ResponseEntity.ok(dto);
		
	}
	@PostMapping(value ="/login")
	 // Define que o método abaixo será executado quando houver uma requisição POST para o endpoint "/login"
	public ResponseEntity<?> logar(@RequestBody UsuarioDTO dto){
		
		//utilizado para retornar um booleano indicando se está correto ou não
		boolean teste = usuarioService.login(dto);
		
		//se for bem sucedido o codigo, exibir "sucesso"
		if(teste) {
			return ResponseEntity.ok("Sucesso!");
		}
		//se não, exibir login incorreto
		return ResponseEntity.status(401).body("Senha ou login incorretos");
	}
}
