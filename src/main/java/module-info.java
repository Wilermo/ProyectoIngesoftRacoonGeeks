module view {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jakarta.xml.bind;
    requires java.logging;
    requires java.desktop;

    exports view;
    opens view to javafx.fxml;
    exports model;
    opens  model to javafx.fxml;

}
/*

for(Curso cur: controladorGeneral.getControlCursos().getListaCursos().values()){
                archivo.write("<Curso>");
                archivo.write("\n");
                archivo.write(cur.getIdCurso().toString());
                archivo.write("\n");
                archivo.write(cur.getNombreCurso());
                archivo.write("\n");
                archivo.write("<MonitoresCurso>");
                archivo.write("\n");
                if(cur.getMonitoresCurso()!=null){
                    for (Monitor monitor: cur.getMonitoresCurso().values()) {
                        archivo.write(monitor.getUsuario());
                        archivo.write("\n");
                    }
                }
                archivo.write("</MonitoresCurso>");
                archivo.write("\n");
                archivo.write("<EstudiantesCurso>");
                archivo.write("\n");

                if(cur.getEstudiantesPertenecenCurso()!=null) {
                    for (Estudiante est : cur.getEstudiantesPertenecenCurso().values()) {
                        archivo.write(est.getUsuario());
                        archivo.write("\n");
                    }
                }
                archivo.write("</EstudiantesCurso>");
                archivo.write("\n");
                archivo.write("<ProfesoresCurso>");
                archivo.write("\n");
                if(cur.getProfesoresPertenecenCurso()!=null){
                    for(Profesor profesor: cur.getProfesoresPertenecenCurso().values()){
                        archivo.write(profesor.getUsuario());
                        archivo.write("\n");
                    }
                }
                archivo.write("</ProfesoresCurso>");
                archivo.write("\n");
                archivo.write("</Curso>");
                archivo.write("\n");
            }

            for(Usuario usr: controladorGeneral.getUsuarios().values()){
                if(usr instanceof Estudiante){
                    archivo.write("<Monitor>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getCarreraEstud());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getDocumentoEstud().toString());
                    archivo.write("\n");
                    archivo.write("<CursosMonitor(Estudiante)>");

                    archivo.write("\n");
                    if(((Estudiante) usr).getCursosPertenecenAEstudiante()!=null){
                        for(Curso curso: ((Estudiante) usr).getCursosPertenecenAEstudiante().values()){
                            archivo.write(curso.getIdCurso().toString());
                            archivo.write("\n");
                        }
                    }
                    archivo.write("</CursosMonitor(Estudiante)>");
                    archivo.write("\n");
                    archivo.write("<CursosMonitor>");
                    if(((Monitor)usr).getCursosMonitor()!=null){
                        for(Curso curso: ((Monitor) usr).getCursosMonitor().values()){
                            archivo.write(curso.getIdCurso().toString());
                            archivo.write("\n");
                        }
                    }
                    archivo.write("</CursosMonitor>");

                    archivo.write("</Monitor>");
                    archivo.write("\n");
                }
                else if(usr instanceof Estudiante){
                    archivo.write("<Estudiante>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getCarreraEstud());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getDocumentoEstud().toString());
                    archivo.write("\n");
                    archivo.write("<CursosEstudiante>");

                    archivo.write("\n");
                    if(((Estudiante) usr).getCursosPertenecenAEstudiante()!=null){
                        for(Curso curso: ((Estudiante) usr).getCursosPertenecenAEstudiante().values()){
                            archivo.write(curso.getIdCurso().toString());
                            archivo.write("\n");
                        }
                    }
                    archivo.write("</CursosEstudiante>");
                    archivo.write("\n");
                    archivo.write("</Estudiante>");
                    archivo.write("\n");


                }else if(usr instanceof Profesor){
                    archivo.write("<Profesor>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(String.valueOf(((Profesor) usr).getCedulaProfe()));
                    archivo.write("\n");

                    archivo.write("<CursosProfesor>");
                    archivo.write("\n");
                    if(((Profesor) usr).getCursosPertenecenAProfesor()!=null){
                        for(Curso curso: ((Profesor) usr).getCursosPertenecenAProfesor().values()) {
                            archivo.write(curso.getIdCurso().toString());
                            archivo.write("\n");
                        }
                    }

                    archivo.write("</CursosProfesor>");
                    archivo.write("\n");

                    archivo.write("</Profesor>");
                    archivo.write("\n");
                }else if(usr instanceof Administrativo){
                    archivo.write("<Administrativo>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(String.valueOf(((Administrativo) usr).getCarreraAdmin()));
                    archivo.write("\n");
                    archivo.write(String.valueOf(((Administrativo) usr).getCedulaAdmin()));
                    archivo.write("\n");

                    archivo.write("</Administrativo>");
                    archivo.write("\n");
                }
        */