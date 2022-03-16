package ar.edu.unju.edm.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.internal.build.AllowSysOut;


import ar.edu.unju.edm.dao.IClienteDAO;
import ar.edu.unju.edm.dao.IMesaDAO;
import ar.edu.unju.edm.dao.IMozoDAO;
import ar.edu.unju.edm.dao.IReservaDAO;
import ar.edu.unju.edm.dao.ISalonDAO;
import ar.edu.unju.edm.dao.imp.ClienteDAOImp;
import ar.edu.unju.edm.dao.imp.MesaDAOImp;
import ar.edu.unju.edm.dao.imp.MozoDAOImp;
import ar.edu.unju.edm.dao.imp.ReservaDAOImp;
import ar.edu.unju.edm.dao.imp.SalonDAOImp;
import ar.edu.unju.edm.dominio.Cliente;
import ar.edu.unju.edm.dominio.ClienteParticular;
import ar.edu.unju.edm.dominio.ClienteTurista;
import ar.edu.unju.edm.dominio.Mesa;
import ar.edu.unju.edm.dominio.Mozo;
import ar.edu.unju.edm.dominio.Reserva;
import ar.edu.unju.edm.dominio.Salon;
import ar.edu.unju.edm.util.DoubleUtil;
import ar.edu.unju.edm.util.EmailUtil;
import ar.edu.unju.edm.util.HoraUtil;
import ar.edu.unju.edm.util.IntUtil;
import ar.edu.unju.edm.util.LongUtil;

public class Main {

	public static EntityManagerFactory emf;
	public static EntityManager manager;
	
	public static void main(String[] args) {
		
		IClienteDAO clienteDAO = new ClienteDAOImp();
		IReservaDAO reservaDAO = new ReservaDAOImp();
		IMozoDAO mozoDAO = new MozoDAOImp();
		IMesaDAO mesaDAO = new MesaDAOImp();
		ISalonDAO salonDAO = new SalonDAOImp();
		Scanner sc = new Scanner(System.in);
		int opc = 0;
		
		do {
			System.out.println("\n-----MENU PRINCIPAL-----\n");
			System.out.println("1 - Alta de Mozo");
			System.out.println("2 - Listado de Mozos");
			System.out.println("3 - Consultar disponibilidad de mesas segun salon");
			System.out.println("4 - Consultar mesas ocupadas");
			System.out.println("5 - Realizar una reserva");
			System.out.println("6 - Anotar cuenta");
			System.out.println("7 - Consultar los datos del cliente");
			System.out.println("8 - Eliminar Reserva");
			System.out.println("9 - Listar todas las reservas");
			System.out.println("10 - Salir");
			System.out.println("Elija una opción:");
			try {
				opc = IntUtil.pedirInt();
				
			}catch(Exception e) {
				System.out.println("Por favor ingrese un numero entero \n");
			}
			
			switch(opc) {
				case 1: {
					if(mozoDAO.cantMozos() == false) {
						Mozo mozo = new Mozo();
						mozo.setEstadoMozo(false);
						mozo.setNroReservas(0);
						System.out.println("Ingrese nombre:");
						mozo.setNombre(sc.next());
						System.out.println("Ingrese apellido:");
						mozo.setApellido(sc.next());;
						mozoDAO.guardarMozo(mozo);
					}
					else
						System.out.println("No se pueden contratar mas mozos\n");
					break;
				}
				case 2: {
					System.out.println("==== Lista de todos los mozos ======");
					mozoDAO.listarMozos().stream().forEach(System.out::println);
					break;
				}
				case 3: {
					int nro=0;
					boolean bandera;
					do {
						bandera = true;
						System.out.println("Nro de salon:\n Nro. 1 \n Nro. 2");
						try {
							nro = IntUtil.pedirInt();
						}catch(Exception e) {
							System.out.println("Por favor ingrese un numero entero \n");
							bandera = false;
						}
						finally{
							if (nro < 1 || nro > 2) {
								System.out.println("Por favor ingrese un numero valido \n");
								bandera = false;
							}
						}
					} while (!bandera);
					System.out.println("==== Lista de mesas Salon " + nro + " ======");
					mesaDAO.consultarSalon(nro).stream().forEach(System.out::println);
					if(mesaDAO.consultarSalon(nro).stream().count() == 0)
						System.out.println("No hay mesas libres en el salon " + nro);
					break;
				}
				case 4: {
					System.out.println("==== Lista de mesas ocupadas ======");
					mesaDAO.cosultarMesaOcup().stream().forEach(System.out::println);
					if(mesaDAO.cosultarMesaOcup().stream().count() == 0)
						System.out.println("No hay mesas ocupadas");
					break;
				}
				case 5: {
					String ans;
					System.out.println("El cliente ya ha realizado alguna reserva? s/n");
					ans = sc.next();
					switch(ans) {
						case "s": {
							long id=0;
							boolean bandera;
							do {
								bandera = true;
								System.out.println("Ingrese su dni/cuit: ");
								try {
									id = LongUtil.pedirLong();
								}catch(Exception e) {
									System.out.println("Por favor ingrese un dni valido \n");
									bandera = false;
								}
							} while (!bandera);
							
							if(clienteDAO.consultarDatos(id)!=null){
								Reserva reserva = new Reserva();
								
								int comensales=0;
								do {
									bandera = true;
									System.out.println("Ingrese la cantidad de comensales:");
									try {
										comensales = IntUtil.pedirInt();
									}catch(Exception e) {
										System.out.println("Por favor ingrese un numero entero \n");
										bandera = false;
									}
									finally{
										if (comensales < 1 || comensales > 8) {
											System.out.println("La cantidad de personas no puede superar 8\n");
											bandera = false;
										}	
									}
									
								} while (!bandera);
								if (mesaDAO.cosultarCant(comensales) == false) {
									System.out.println("No hay mesas disponibles\n");
									break;
								}
								reserva.setNroPersonas(comensales);
								
								String hora;
								LocalTime horaNueva = null;
								do {
									bandera = true;
									System.out.println("Ingrese hora de reserva hh:mm");
									try {
										horaNueva = HoraUtil.convertirStringLocalDate();
									} catch (Exception e) {
										System.out.println("Por favor ingrese una hora valida \n");
										bandera = false;
									}
									finally{
										if(horaNueva != null) {
											//System.out.println(horaNueva);
											if (horaNueva.isBefore(LocalTime.now())) {
												System.out.println("Hora incorrecta");
												bandera = false;
											}
										}
									}
								} while (!bandera);
								reserva.setHora(horaNueva.minusHours(3));
								
								LocalDate fecha = LocalDate.now();
								reserva.setFecha(fecha);
								
								Mozo mozo = mozoDAO.asignarMozo();
								mozoDAO.ocuparMozo(mozo);
								reserva.setMozo(mozo);
								
								Salon salon = salonDAO.asignarSalon(mesaDAO.asignarNroSalon(comensales));
								reserva.setSalon(salon);
								
								reserva.setCliente(clienteDAO.consultarDatos(id));
								
								reservaDAO.guardarReserva(reserva);
								
								mesaDAO.asignarMesas(comensales, mesaDAO.asignarNroSalon(comensales), reserva);
							}
							else
								System.out.println("No se encontro el cliente.");
							break;
						}
						case "n": {
							int nro=0;
							long id=0;
							boolean bandera;
							do {
								bandera = true;
								System.out.println("Tipo de cliente?\n 1 - Particular\n 2 - Turista");
								try {
									nro = IntUtil.pedirInt();
								}catch(Exception e) {
									System.out.println("Por favor ingrese un numero entero \n");
									bandera = false;
								}
								finally{
									if (nro < 1 || nro > 2) {
										System.out.println("Por favor ingrese un numero valido \n");
										bandera = false;
									}
								}
							} while (!bandera);
							
							if(nro == 1) {
								System.out.println("Nombre:");
								String nombre = sc.next();
								String email;
								do {
									bandera = true;
									System.out.println("Email:");
									email = sc.next();
									if(EmailUtil.valEmail(email) == false) {
										bandera=false;
										System.out.println("Ingrese un email valido");
									}
								} while (!bandera);
								long telefono=0;
								do {
									bandera = true;
									System.out.println("Telefono:");
									try {
										telefono = LongUtil.pedirLong();
									}catch(Exception e) {
										System.out.println("Por favor ingrese un numero de telefono valido \n");
										bandera = false;
									}
								} while (!bandera);
								long dni=0;
								do {
									bandera = true;
									System.out.println("DNI:");
									try {
										dni = LongUtil.pedirLong();
									}catch(Exception e) {
										System.out.println("Por favor ingrese un dni valido \n");
										bandera = false;
									}
								} while (!bandera);
								Cliente clienteP = new ClienteParticular(email, nombre, telefono, dni);
								clienteDAO.guardarCliente(clienteP);
								id = dni;
							}
							else {
								System.out.println("Nombre:");
								String nombre = sc.next();
								String email;
								do {
									bandera = true;
									System.out.println("Email:");
									email = sc.next();
									if(EmailUtil.valEmail(email) == false) {
										bandera=false;
										System.out.println("Ingrese un email valido");
									}
								} while (!bandera);
								
								long telefono=0;
								do {
									bandera = true;
									System.out.println("Telefono:");
									try {
										telefono = LongUtil.pedirLong();
									}catch(Exception e) {
										System.out.println("Por favor ingrese un numero de telefono valido \n");
										bandera = false;
									}
								} while (!bandera);
								
								long cuit=0;
								do {
									bandera = true;
									System.out.println("CUIT:");
									try {
										cuit = LongUtil.pedirLong();
									}catch(Exception e) {
										System.out.println("Por favor ingrese un cuit valido \n");
										bandera = false;
									}
								} while (!bandera);
								Cliente clienteT = new ClienteTurista(email, nombre, telefono, cuit);
								clienteDAO.guardarCliente(clienteT);
								id = cuit;
							}
							
							Reserva reserva = new Reserva();
							
							int comensales=0;
							do {
								bandera = true;
								System.out.println("Ingrese la cantidad de comensales:");
								try {
									comensales = IntUtil.pedirInt();
								}catch(Exception e) {
									System.out.println("Por favor ingrese un numero entero \n");
									bandera = false;
								}
								finally{
									if (comensales < 1 || comensales > 8) {
										System.out.println("La cantidad de personas no puede superar 8\n");
										bandera = false;
									}	
								}
								
							} while (!bandera);
							
							if (mesaDAO.cosultarCant(comensales) == false) {
								System.out.println("No hay mesas disponibles\n");
								break;
							}
							reserva.setNroPersonas(comensales);
							
							String hora;
							LocalTime horaNueva = null;
							do {
								bandera = true;
								System.out.println("Ingrese hora de reserva hh:mm");
								try {
									horaNueva = HoraUtil.convertirStringLocalDate();
								} catch (Exception e) {
									System.out.println("Por favor ingrese una hora valida \n");
									bandera = false;
								}
								finally{
									if (horaNueva.isBefore(LocalTime.now())) {
										System.out.println("Hora incorrecta");
										bandera = false;
									}	
								}
							} while (!bandera);
							reserva.setHora(horaNueva);
							
							LocalDate fecha = LocalDate.now();
							reserva.setFecha(fecha);
							
							Mozo mozo = mozoDAO.asignarMozo();
							mozoDAO.ocuparMozo(mozo);
							reserva.setMozo(mozo);
							
							Salon salon = salonDAO.asignarSalon(mesaDAO.asignarNroSalon(comensales));
							reserva.setSalon(salon);
							
							reserva.setCliente(clienteDAO.consultarDatos(id));
							
							reservaDAO.guardarReserva(reserva);
							
							mesaDAO.asignarMesas(comensales, mesaDAO.asignarNroSalon(comensales), reserva);
							
							break;
						}
						default: {
							System.out.println("Opcion incorrecta, intente de nuevo.");
							break;
						}
					}
					
					break;
				}
				case 6: {
					System.out.println("==== Reservas ======");
					reservaDAO.listarReservasSinCobrar().stream().forEach(System.out::println);
					if(reservaDAO.listarReservasSinCobrar().size() == 0) {
						System.out.println("No hay reservas");
						break;
					}
					
					boolean bandera;
					int nroReserva=0;
					do {
						bandera = true;
						System.out.println("Nro de la reserva: ");
						try {
							nroReserva = IntUtil.pedirInt();
						}catch(Exception e) {
							System.out.println("Por favor ingrese un numero entero \n");
							bandera = false;
						}
					} while (!bandera);
					Reserva reserva = reservaDAO.buscarReserva(nroReserva);
					if(reserva != null) {
						reserva.setEstadoReserva(true);
						do {
							bandera = true;
							System.out.println("Total: ");
							try {
								reserva.setTotal(DoubleUtil.pedirDouble());
							}catch(Exception e) {
								System.out.println("Por favor ingrese un monto correcto \n");
								bandera = false;
							}
						} while (!bandera);
						reserva.getMozo().setEstadoMozo(false);
						mozoDAO.desocuparMozo(reserva.getMozo());
						mesaDAO.desocuparMesas(reserva.getMesas());
						reservaDAO.pedirCuenta(reserva);
					}
					else
						System.out.println("No se encontro la reserva");
					break;
				}
				case 7: {
					long id=0;
					boolean bandera;
					do {
						bandera = true;
						System.out.println("Ingrese su dni/cuit: ");
						try {
							id = LongUtil.pedirLong();
						}catch(Exception e) {
							System.out.println("Por favor ingrese un dni valido \n");
							bandera = false;
						}
					} while (!bandera);
					
					if(clienteDAO.consultarDatos(id)!=null){
						System.out.println(clienteDAO.consultarDatos(id));
					}
					else
						System.out.println("No se encontro el cliente");
					break;
				}
				case 8: {
					System.out.println("==== Reservas ======");
					reservaDAO.listarReservas().stream().forEach(System.out::println);
					if(reservaDAO.listarReservas().size() == 0) {
						System.out.println("No hay reservas");
						break;
					}
					boolean bandera;
					int nroReserva=0;
					do {
						bandera = true;
						System.out.println("Nro de la reserva: ");
						try {
							nroReserva = IntUtil.pedirInt();
						}catch(Exception e) {
							System.out.println("Por favor ingrese un numero entero \n");
							bandera = false;
						}
					} while (!bandera);
					Reserva reserva = reservaDAO.buscarReserva(nroReserva);
					if(reserva != null) {
						reserva.getMozo().setEstadoMozo(false);
						mozoDAO.desocuparMozo(reserva.getMozo());
						mesaDAO.desocuparMesas(reserva.getMesas());
						reservaDAO.eliminarRerserva(reserva);
					}
					else
						System.out.println("No se encontro la reserva");
					break;
				}
				case 9: {
					System.out.println("==== Reservas ======");
					reservaDAO.listarReservas().stream().forEach(System.out::println);
					if(reservaDAO.listarReservas().size() == 0)
						System.out.println("No hay reservas");
					break;
				}
				default: {
					System.out.println("Opcion incorrecta.");
					break;
				}
			}
			
		}while(opc != 10);
		
	}

}
