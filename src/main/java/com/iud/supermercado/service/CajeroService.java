package com.iud.supermercado.service;

import com.iud.supermercado.configuration.ExecutorConfig;
import com.iud.supermercado.dto.GetVentaDto;
import com.iud.supermercado.dto.VentaDto;
import com.iud.supermercado.model.Cajero;
import com.iud.supermercado.repository.CajeroRepository;
import com.iud.supermercado.util.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Service
public class CajeroService {

    @Autowired
    private CajeroRepository cajeroRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ExecutorConfig executorConfig;

    @Autowired
    private TaskExecutor taskExecutor;


    public Cajero createCajero(Cajero cajero) {
        return cajeroRepository.save(cajero);
    }

    public List<Cajero> getAllCajeros() {
        return cajeroRepository.findAll();
    }

    public Cajero getCajeroById(int id) {
        return cajeroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cajero no encontrado con el ID: " + id));
    }

    public Cajero updateCajero(int id, Cajero cajeroDetails) {
        return cajeroRepository.findById(id).map(cajero -> {
            cajero.setNombre(cajeroDetails.getNombre());
            cajero.setApellido(cajeroDetails.getApellido());
            cajero.setId(cajeroDetails.getId());
            return cajeroRepository.save(cajero);
        }).orElseThrow(() -> new RuntimeException("Cajero no encontrado con el ID: " + id));
    }

    public void deleteCajero(int id) {
        cajeroRepository.deleteById(id);
    }

    public void getVenta(GetVentaDto venta) {
        Long time = System.currentTimeMillis();
        VentaDto ventaDto = new VentaDto();
        executorConfig.taskExecutor().execute(
                () -> {


                    venta.getProductos().forEach(producto -> {
                        ventaDto.getProductos()
                                .add(productoService.getProductoByCodigo(producto));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
                    executorConfig.taskExecutor().execute(
                            () -> {
                                ventaDto.setCliente(
                                        clienteService.getClientByDocument(
                                                venta.getDocumentoCliente()));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            });

           executorConfig.taskExecutor().execute(
                                        () -> {
                                            ventaDto.setCajero(getCajeroById(venta.getIdCajero()));
                                            try {
                                                Thread.sleep(1000);
                                                ventaDto.setTimeVenta("Tiempo de ejecucion: " + (System.currentTimeMillis() - time) + "ms");
                                                System.out.println("Tiempo de ejecucion: " + (System.currentTimeMillis() - time) + "ms");
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });

        System.out.println("Tiempo de ejecucion: " + (System.currentTimeMillis() - time) + "ms");
        /*
        VentaDto ventaDto = new VentaDto();
        Long time = System.currentTimeMillis();
        Callable<Void> tarea1 = () -> {
            venta.getProductos().forEach(producto -> {
                ventaDto.getProductos()
                        .add(productoService.getProductoByCodigo(producto));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        });
        return null;
        };
        Callable<Void> tarea2 = () -> {
            ventaDto.setCliente(
                    clienteService.getClientByDocument(
                            venta.getDocumentoCliente()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        return null;
        };
        Callable<Void> tarea3 = () -> {
            ventaDto.setCajero(getCajeroById(venta.getIdCajero()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        return null;
        };

        try {
            Future<Void> future1 = executorConfig.taskExecutor().submit(tarea1);
            Future<Void> future2 = executorConfig.taskExecutor().submit(tarea2);
            Future<Void> future3 = executorConfig.taskExecutor().submit(tarea3);

            future1.get();
            future2.get();
            future3.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ventaDto.setTimeVenta("Tiempo de ejecucion: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println("Tiempo de ejecucion: " + (System.currentTimeMillis() - time) + "ms");
        return ventaDto;

         */
    }

    public void getVenta2(GetVentaDto venta) {
        VentaDto ventaDto = new VentaDto();
        long startTime = System.currentTimeMillis();

        Venta tarea1 = new Venta(venta, productoService, clienteService, this, ventaDto);

        try {
            taskExecutor.execute(tarea1);


        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando las tareas", e);
        }

        ventaDto.setTimeVenta("Tiempo de ejecución: " + (System.currentTimeMillis() - startTime) + "ms");


    }
}

