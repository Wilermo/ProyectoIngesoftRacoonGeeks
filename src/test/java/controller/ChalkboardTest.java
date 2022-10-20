package controller;

import back.helper.ValidacionesHelper;
import com.sun.source.tree.AssertTree;
import controller.ControladorGeneral;
import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import jdk.jfr.StackTrace;
import model.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import javax.sound.sampled.Control;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChalkboardTest {

    //Testing de Administrador

    public Administrativo administrativo;
    public Estudiante estudiante;
    public Profesor profesor;
    public Curso curso;
    public Tarea tarea;

    public void escenario(){
        administrativo = new Administrativo();
        estudiante = new Estudiante();
        profesor = new Profesor();
        curso = new Curso();
        tarea = new Tarea();
    }

    @Test
    public void testBuscarUsuario(){
        escenario();
        administrativo.setUsuario("Pepe");
        IGlobalController.controladorGeneral.insertarAdmin(administrativo);
        Assert.assertNotNull(IGlobalController.controladorGeneral.buscarAdministradores(administrativo.getUsuario()));
    }

    @Test
    public void testModificarAdministrador(){
        escenario();
        administrativo.setContrasenna("Willy");
        IGlobalController.controladorGeneral.insertarAdmin(administrativo);
        IGlobalController.controladorGeneral.modificarAdminBasico(administrativo,"Pipe");
        Assert.assertNotEquals(administrativo.getContrasenna(),"Willy");
    }

    @Test
    public void testEliminarAdministrador(){
        administrativo = new Administrativo();
        administrativo.setUsuario("Pepe");
        IGlobalController.controladorGeneral.insertarAdmin(administrativo);
        IGlobalController.controladorGeneral.eliminarAdministrador(administrativo);
        Assert.assertNull(IGlobalController.controladorGeneral.buscarAdministradores(administrativo.getUsuario()));
    }

    @Test
    public void testConsultarAdministrativo(){
        escenario();
        administrativo.setUsuario("Felipe");
        IGlobalController.controladorGeneral.insertarAdmin(administrativo);
        Assert.assertTrue(IGlobalController.controladorGeneral.consultarAdministradores());
    }

    //Testing de Estudiante

    @Test
    public void testBuscarEstudiante(){
        escenario();
        estudiante.setUsuario("Juan");
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().insertarEstudiante(estudiante);
        Assert.assertNotNull(IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().buscarEstudiante("Juan"));
    }

    @Test
    public void testModificarEstudiante(){
        escenario();
        estudiante.setContrasenna("Angela456");
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().insertarEstudiante(estudiante);
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().modificarEstudianteBasico(estudiante,"Angela123","Angela","Sistemas");
        Assert.assertNotEquals(estudiante.getContrasenna(),"Angela456");
    }

    @Test
    public void testEliminarEstudiante(){
        escenario();
        estudiante.setUsuario("Mauren");
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().insertarEstudiante(estudiante);
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().eliminarEstudiante(estudiante);
        Assert.assertNull(IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().buscarEstudiante(estudiante.getUsuario()));
    }

    @Test
    public void testConsultarEstudiante(){
        escenario();
        estudiante.setUsuario("Paula");
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().insertarEstudiante(estudiante);
        Assert.assertTrue(IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().consultarEstudiantes());
    }

    //Testing Profesores

    @Test
    public void testBuscarProfesor(){
        escenario();
        profesor.setUsuario("Sebastian");
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().insertarProfesor(profesor);
        Assert.assertNotNull(IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().buscarProfesor(profesor.getUsuario()));
    }

    @Test
    public void testModificarProfesor(){
        escenario();
        profesor.setContrasenna("Andres789");
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().insertarProfesor(profesor);
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().modificarProfesoresBasico(profesor,"Andres456");
        Assert.assertNotEquals(profesor.getContrasenna(),"Andres789");
    }

    @Test
    public void testEliminarProfesor(){
        escenario();
        profesor.setUsuario("Diego");
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().insertarProfesor(profesor);
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().eliminarProfesor(profesor);
        Assert.assertNull(IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().buscarProfesor(profesor.getUsuario()));
    }

    @Test
    public void testConsultarProfe(){
        escenario();
        profesor.setUsuario("William");
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().insertarProfesor(profesor);
        Assert.assertTrue(IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().consultarProfesores());
    }

    //Testing Cursos

    @Test
    public void testBuscarCurso(){
        escenario();
        curso.setNombreCurso("Fundamentos de Ingenieria de software");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        Assert.assertNotNull(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(curso.getIdCurso()));
    }

    @Test
    public void testModificarCurso(){
        escenario();
        curso.setNombreCurso("POO");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().modificarCurso(curso,"Fundamentos de Ingenieria de software");
        Assert.assertNotEquals(curso.getNombreCurso(),"POO");
    }

    @Test
    public void testEliminarCurso() {
        escenario();
        curso.setNombreCurso("POO");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().eliminarCurso(curso);
        Assert.assertNull(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(curso.getIdCurso()));
    }

    @Test
    public void testAgregarTarea(){
        Map<UUID, IAsignacion> testListaTareas = new HashMap<>();
        escenario();
        curso.setNombreCurso("PC");
        curso.getAsignacionesCurso().put(tarea.getIdAsignacion(),tarea);
        testListaTareas.put(tarea.getIdAsignacion(),tarea);
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);

        Assert.assertEqualsDeep(curso.getAsignacionesCurso(),testListaTareas);
    }

    @Test
    public void testConsultarCursos(){
        escenario();
        curso.setNombreCurso("PC");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        Assert.assertTrue(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().consultarCurso());
    }

    //Testing Vinculacion de Cursos.

    @Test
    public void testEstudianteEnCurso(){
        Map<String, Estudiante> testListaEstudiantes = new HashMap<>();
        escenario();

        estudiante.setUsuario("Sebastian");
        testListaEstudiantes.put(estudiante.getUsuario(),estudiante);

        curso.setNombreCurso("Fundamentos de seguridad");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        curso.getEstudiantesPertenecenCurso().put(estudiante.getUsuario(),estudiante);

        Assert.assertEqualsDeep(curso.getEstudiantesPertenecenCurso(),testListaEstudiantes);
    }

    @Test
    public void testProfesorEnCurso(){
        Map<String, Profesor> testListaProfesores = new HashMap<>();
        escenario();

        profesor.setUsuario("Mauren");
        testListaProfesores.put(profesor.getUsuario(),profesor);

        curso.setNombreCurso("Calculo Diferencial");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);
        curso.getProfesoresPertenecenCurso().put(profesor.getUsuario(),profesor);

        Assert.assertEqualsDeep(curso.getProfesoresPertenecenCurso(),testListaProfesores);
    }

    @Test
    public void testCursoEnEstudiante(){
        Map<UUID, Curso> testListaCursosEstudiante = new HashMap<>();
        escenario();

        curso.setNombreCurso("Calculo Integral");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);

        estudiante.getCursosPertenecenAEstudiante().put(curso.getIdCurso(),curso);

        testListaCursosEstudiante.put(curso.getIdCurso(),curso);

        Assert.assertEqualsDeep(estudiante.getCursosPertenecenAEstudiante(),testListaCursosEstudiante);
    }

    @Test
    public void testCursoEnProfesores(){
        Map<UUID, Curso> testListaCursosProfesor = new HashMap<>();
        escenario();

        curso.setNombreCurso("Calculo Vectorial");
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().insertarCurso(curso);

        profesor.getCursosPertenecenAProfesor().put(curso.getIdCurso(),curso);

        testListaCursosProfesor.put(curso.getIdCurso(),curso);

        Assert.assertEqualsDeep(profesor.getCursosPertenecenAProfesor(),testListaCursosProfesor);
    }

    //Testing Constructores
    @Test
    public void testConstructorAdministrativo() {
        Assert.assertNotNull(new Administrativo("Sebs","Sebs123","Sebastian","Sebas@hot.com",11223123l));
        Administrativo administrativo = new Administrativo("Sebs","Sebs123","Sebastian","Sebas@hot.com",11223123l);
        Assert.assertEquals(administrativo.getUsuario(),"Sebs");
    }

    @Test
    public void testConstructorCurso() {
        Assert.assertNotNull(new Curso("Fundamentos de seguridad"));
        Curso curso=new Curso("Fundamentos de seguridad");
        Assert.assertEquals(curso.getNombreCurso(),"Fundamentos de seguridad");
    }

    @Test
    public void testConstructorProfesor() {
        Assert.assertNotNull(new Profesor("Mauren123","Mau546","Mauren","Mauren@hot.com",5354354l));
        Profesor profesor = new Profesor("Mauren123","Mau546","Mauren","Mauren@hot.com",5354354l);
        Assert.assertEquals(profesor.getUsuario(),"Mauren123");
    }

    @Test
    public void testConstructorEstudiante() {
        Assert.assertNotNull(new Estudiante("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",456456456l));
        Estudiante estudiante = new Estudiante("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",456456456l);
        Assert.assertEquals(estudiante.getUsuario(),"Pipe345");
    }

    @Test
    public void testConstructorMonitor() {
        Map<UUID, Curso> listaCursosEstudiante = new HashMap<>();
        Assert.assertNotNull(new Monitor("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",456456456l,listaCursosEstudiante));
        Monitor monitor = new Monitor("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",456456456l,listaCursosEstudiante);
        Assert.assertEquals(monitor.getUsuario(),"Pipe345");
    }

    //Testing Fabrica
    @Test
    public void testFactoryTarea(){
        IAsignacion asignacion=Factory.construir("Tarea");
        Tarea tarea=new Tarea();
        Assert.assertEquals(asignacion.getClass(),tarea.getClass());

    }

    @Test
    public void testFactoryContenido(){
        IAsignacion asignacion=Factory.construir("Contenido");
        Contenido contenido=new Contenido();
        Assert.assertEquals(asignacion.getClass(),contenido.getClass());
    }

    @Test
    public void testFactoryError(){
        IAsignacion asignacion=Factory.construir("Estudiante");
        Assert.assertNull(asignacion);
    }

    @Test
    public void testActualizarProfe(){
        ControladorProfesor controladorProfesor=new ControladorProfesor();
        Profesor profesor = new Profesor("Mauren123","Mau546","Mauren","Mauren@hot.com",5354354l);
        controladorProfesor.insertarProfesor(profesor);
        Assert.assertEquals(profesor.getNombre(),"Mauren");
        Assert.assertEquals(profesor.getUsuario(),"Mauren123");
        Assert.assertEquals(profesor.getCorreo(),"Mauren@hot.com");
        controladorProfesor.actualizarProfesor(profesor,"Mauren2","Mauren321","Mau@yahoo.com");
        Assert.assertEquals(profesor.getNombre(),"Mauren2");
        Assert.assertEquals(profesor.getUsuario(),"Mauren321");
        Assert.assertEquals(profesor.getCorreo(),"Mau@yahoo.com");
    }

    @Test
    public void testActualizarEstudiante(){
        ControladorEstudiante controladorEstudiante=new ControladorEstudiante();
        Estudiante estudiante = new Estudiante("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",56456l);
        controladorEstudiante.insertarEstudiante(estudiante);
        Assert.assertEquals(estudiante.getNombre(),"Felipe");
        Assert.assertEquals(estudiante.getUsuario(),"Pipe345");
        Assert.assertEquals(estudiante.getCorreo(),"Pipe@hot.com");
        controladorEstudiante.actualizarEstudiante(estudiante,"Felipe2","Pipe543","Pipe@yahoo.com");
        Assert.assertEquals(estudiante.getNombre(),"Felipe2");
        Assert.assertEquals(estudiante.getUsuario(),"Pipe543");
        Assert.assertEquals(estudiante.getCorreo(),"Pipe@yahoo.com");
    }

    @Test
    public void testTipoUsuario(){
        Map<UUID, Curso> listaCursosEstudiante = new HashMap<>();
        Estudiante estudiante = new Estudiante("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",56456l);
        ControladorGeneral controladorGeneral=new ControladorGeneral();
        controladorGeneral.guardarUsuario(estudiante);
        Assert.assertEquals(controladorGeneral.comprobarTipoUsuario(estudiante.getUsuario(),estudiante.getContrasenna()),1 );
        Administrativo administrativo = new Administrativo("Sebs","Sebs123","Sebastian","Sebas@hot.com",11223123l);
        controladorGeneral.guardarUsuario(administrativo);
        Assert.assertEquals(controladorGeneral.comprobarTipoUsuario(administrativo.getUsuario(),administrativo.getContrasenna()),3 );
        Profesor profesor = new Profesor("Mauren123","Mau546","Mauren","Mauren@hot.com",5354354l);
        controladorGeneral.guardarUsuario(profesor);
        Assert.assertEquals(controladorGeneral.comprobarTipoUsuario(profesor.getUsuario(),profesor.getContrasenna()),2 );
        Monitor monitor = new Monitor("Pipe345","Pipe123","Felipe","Pipe@hot.com","Sistemas",456456456l,listaCursosEstudiante);
        controladorGeneral.guardarUsuario(monitor);
        Assert.assertEquals(controladorGeneral.comprobarTipoUsuario(monitor.getUsuario(),monitor.getContrasenna()),0 );
    }

}

