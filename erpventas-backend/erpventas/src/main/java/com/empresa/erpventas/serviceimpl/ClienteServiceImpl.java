package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Cliente;
import com.empresa.erpventas.repository.ClienteRepository;
import com.empresa.erpventas.service.ClienteService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private BCryptPasswordEncoder passwordEncoder; //para el hasheo del pa contraseña

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listarCliente() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);

    }

    @Transactional
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        if(cliente.getIdCli() == null && cliente.getPassword() != null) {
            String passwordHasheada = passwordEncoder.encode(cliente.getPassword());
            cliente.setPassword(passwordHasheada);
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if(clienteOpt.isPresent()){
            Cliente clienteDb = clienteOpt.get();
            clienteDb.setNombreCli(cliente.getNombreCli());
            clienteDb.setApellidoCli(cliente.getApellidoCli());
            clienteDb.setEdadCli(cliente.getEdadCli());
            clienteDb.setCiRuc(cliente.getCiRuc());
            clienteDb.setTelefonoCli(cliente.getTelefonoCli());
            clienteDb.setFotosCli(cliente.getFotosCli());
            clienteDb.setFechaNacCli(cliente.getFechaNacCli());

            clienteDb.setUsername(cliente.getUsername());
            clienteDb.setRol(cliente.getRol());
            clienteDb.setEstado(cliente.getEstado());
            clienteDb.setNumTarjeta(cliente.getNumTarjeta());
            clienteDb.setTipoTarjeta(cliente.getTipoTarjeta());

            if (cliente.getCiudad() != null) {
                clienteDb.setCiudad(cliente.getCiudad());
            }

            if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
                String nuevaPasswordHasheada = passwordEncoder.encode(cliente.getPassword());
                clienteDb.setPassword(nuevaPasswordHasheada);
            }

            return clienteRepository.save(clienteDb);

        }

        return null;
    }

    @Transactional
    @Override
    public Optional<Cliente> eliminarCliente(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()){
            clienteRepository.deleteById(id);
            return clienteOpt;
        }
        return Optional.empty();
    }







































}
