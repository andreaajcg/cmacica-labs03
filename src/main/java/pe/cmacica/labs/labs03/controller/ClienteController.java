package pe.cmacica.labs.labs03.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    //para inyectar
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public HttpEntity<List<Cliente>> listar()
    {
        List<Cliente> list = clienteService.listar();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<Cliente> listar(@PathVariable("id") int id)
    {
        if (id == 5)
        {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteService.getCliente(id);

        return ResponseEntity.ok(cliente);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @PostMapping()
    public HttpEntity<String> guardar(@RequestBody Cliente cliente)
    {
        //if(cliente.getNombres().isEmpty())
        if(StringUtils.isBlank(cliente.getNombres()))
        {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.debug("SAVE");
        LOGGER.debug("{}", cliente.getId());
        LOGGER.debug("{}", cliente.getNombres());

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<String> guardar(@PathVariable("id") int id, @RequestBody Cliente cliente)
    {
        if(id == 0)
        {
            return ResponseEntity.badRequest().build();
        }

        LOGGER.debug("UPDATE");
        LOGGER.debug("{}", cliente.getId());
        LOGGER.debug("{}", cliente.getNombres());

        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<String> eliminar(@PathVariable("id") int id)
    {
        //if(cliente.getNombres().isEmpty())
        //if(StringUtils.isBlank(cliente.getNombres()))
        //{
        //    return ResponseEntity.badRequest().build();
        //}
        LOGGER.debug("DELETE");
        if(id == 0)
        {
            return ResponseEntity.badRequest().build();
        }
        //////
        clienteService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
